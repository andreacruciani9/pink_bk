package it.pink.pink.Service;


import it.pink.pink.Entity.Indirizzo;
import it.pink.pink.Entity.Recensioni;
import it.pink.pink.Entity.Utente;
import it.pink.pink.Exceptions.BadRequestException;
import it.pink.pink.Exceptions.InternalServerErrorException;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.Exceptions.UnauthorizedException;
import it.pink.pink.Repository.IndirizzoRepository;
import it.pink.pink.Repository.UtenteRepository;
import it.pink.pink.RequestDTO.IndirizzoDTO;
import it.pink.pink.RequestDTO.UtenteDTO;
import it.pink.pink.ResponseDTO.AccessTokenRes;
import it.pink.pink.security.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UtenteRepository utenteRp;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private JwtTools jwtTools;
    @Autowired
    IndirizzoRepository indirizzoRepository;

    public Utente register(UtenteDTO utenteDTO) throws BadRequestException, InternalServerErrorException {
        Utente u = new Utente(utenteDTO.username(), utenteDTO.email(), encoder.encode(utenteDTO.password()),
                utenteDTO.nome(), utenteDTO.cognome());
        try {
            return utenteRp.save(u);
        } catch (DataIntegrityViolationException e) {
            if (utenteRp.getAllEmails().contains(u.getEmail()))
                throw new BadRequestException("email già esistente, impossibile creare");
            if (utenteRp.getAllUsernames().contains(u.getUsername()))
                throw new BadRequestException("username già esistente, impossibile creare");
            throw new InternalServerErrorException("Errore di violazione dell'* integrità dei dati: " + e.getMessage());

        }
    }

    public Optional<Utente> findByUserId(UUID userId) {
        return utenteRp.findById(userId);
    }

    public AccessTokenRes login(String username, String password) throws UnauthorizedException {
        Utente u = utenteRp.findByUsername(username).orElseThrow(
                () -> new UnauthorizedException("Email/password errati")
        );
        if (!encoder.matches(password, u.getPassword()))
            throw new UnauthorizedException("Email/password errati");
        return new AccessTokenRes(jwtTools.createToken(u),u.getId());
    }

    public Page<Utente> getAllUtente(Pageable pageable) {
        return utenteRp.findAll(pageable);
    }

    public Utente getUtenteById(UUID id) throws NotFoundException {
        Utente utente = utenteRp.findById(id).orElseThrow(() -> new NotFoundException("utente con id = " + id + "not found"));
        return utente;
    }

//public Utente saveUtente(UtenteDTO utenteDTO){
    //      Utente u=new Utente(utenteDTO.username(),utenteDTO.email(),utenteDTO.password(),utenteDTO.nome(),utenteDTO.cognome());
    // Indirizzo indirizzoregistato=new Indirizzo(utenteDTO.indirizzoregistrato().via(),utenteDTO.indirizzoregistrato().civico(),utenteDTO.indirizzoregistrato().localita(),indirizzoRepository.findUtenteByIndirizzo(u)));
    //   return utenteRp.save


    public void delteteUtente(UUID id) {
        Utente utente=utenteRp.getReferenceById(id);
        utenteRp.delete(utente);

    }

    public Utente getUtenteByRecensioni(List<Recensioni> recensioni) {
        return utenteRp.findByRecensioni(recensioni);
    }

    //public Utente getUtenteByOrdineId(UUID idOrdine) {
      //  return utenteRp.findByOrdiniId(idOrdine);
    }



