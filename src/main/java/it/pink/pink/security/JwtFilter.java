package it.pink.pink.security;


import com.fasterxml.jackson.databind.ObjectMapper;

import it.pink.pink.Entity.Utente;
import it.pink.pink.Exceptions.ErrorResponse;
import it.pink.pink.Exceptions.UnauthorizedException;
import it.pink.pink.Service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private AuthService authSvc;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorization = req.getHeader("Authorization");
            if (authorization == null)
                throw new UnauthorizedException("Access token assente");
            else if (!authorization.startsWith("Bearer "))
                throw new UnauthorizedException("Access token non valido");

            String token = authorization.split(" ")[1];

            jwtTools.validateToken(token);

            UUID userId = jwtTools.extractUserIdFromToken(token);

            Utente u = authSvc.findByUserId(userId)
            .orElseThrow(
                    () -> new UnauthorizedException("Access token non valido")
            );


            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(req, res);
        } catch (UnauthorizedException e) {
            ObjectMapper mapper = new ObjectMapper();
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter().write(mapper.writeValueAsString(
                    new ErrorResponse(HttpStatus.UNAUTHORIZED,
                            "Unauthorized", e.getMessage()
                    )));
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
        return new AntPathMatcher().match("/auth/**", req.getServletPath())
                || new AntPathMatcher().match("/prodotti/save", req.getServletPath())
                || new AntPathMatcher().match("/comuni/**", req.getServletPath());
    }


}
