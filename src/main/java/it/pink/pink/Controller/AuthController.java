package it.pink.pink.Controller;

import it.pink.pink.Entity.Recensioni;
import it.pink.pink.Entity.Utente;
import it.pink.pink.Exceptions.*;
import it.pink.pink.RequestDTO.LoginDTO;
import it.pink.pink.RequestDTO.UtenteDTO;
import it.pink.pink.ResponseDTO.AccessTokenRes;
import it.pink.pink.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AuthController {

    @Autowired
    private AuthService authSvc;

    //@CrossOrigin(origins = "http://localhost:8080")
    //@PostMapping("/auth/register")
    //public Utente register(@Validated @RequestBody UtenteDTO utenteDTO, BindingResult validation) throws BadRequestException, InternalServerErrorException {
        //System.out.println(utenteDTO);
        //HandlerException.exception(validation);
        //return authSvc.register(utenteDTO);
        //}

    //@PostMapping("/auth/login")
    //public AccessTokenRes login(@Validated @RequestBody LoginDTO loginDTO, BindingResult validation) throws BadRequestException, UnauthorizedException {
        //HandlerException.exception(validation);
      //  return authSvc.login(loginDTO.username(), loginDTO.password());
    //}

    //@GetMapping("/utenti/by-recensioni")
    //public Utente getUtenteByRecensioni(@RequestParam List<Recensioni> recensioni) {
      //  return authSvc.getUtenteByRecensioni(recensioni);

    //}
  //@GetMapping("/utente/{id}")
    //public Utente getUtentiById(@PathVariable UUID id) throws NotFoundException {
  //     return authSvc.getUtenteById(id);
//    }


    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@Validated @RequestBody UtenteDTO utenteDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            // Se ci sono errori di convalida, restituisci una risposta BadRequest
            return ResponseEntity.badRequest().body("Errore di convalida");
        } else {
            try {
                Utente utente = authSvc.register(utenteDTO);
                return ResponseEntity.ok(utente);
            } catch (BadRequestException | InternalServerErrorException e) {
                // Se si verifica un'eccezione durante la registrazione, restituisci una risposta con stato 500
                return ResponseEntity.status(500).body("Errore durante la registrazione: " + e.getMessage());
            }
        }
    }

    @PostMapping("/auth/login")
    public AccessTokenRes login(@Validated @RequestBody LoginDTO loginDTO, BindingResult validation) throws BadRequestException, UnauthorizedException {
        HandlerException.exception(validation);
        return authSvc.login(loginDTO.username(), loginDTO.password());
    }

    @GetMapping("/utenti/by-recensioni")
    public Utente getUtenteByRecensioni(@RequestParam List<Recensioni> recensioni) {
        return authSvc.getUtenteByRecensioni(recensioni);
    }

    @GetMapping("/utente/{id}")
    public Utente getUtentiById(@PathVariable UUID id) throws NotFoundException {
        return authSvc.getUtenteById(id);
    }

}