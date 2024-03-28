package it.pink.pink.Service;

import it.pink.pink.Entity.Indirizzo;
import it.pink.pink.Entity.Utente;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.Repository.IndirizzoRepository;
import it.pink.pink.Repository.UtenteRepository;
import it.pink.pink.RequestDTO.IndirizzoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IndirizzoService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private AuthService authService;

    public Indirizzo getIndirizzoForId(UUID id) throws NotFoundException {
        Indirizzo indirizzo = indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException("Indirizzo not found"));
        indirizzo.setUtendeId(indirizzo.getUtente().getId());
        return indirizzo;
    }

    public Indirizzo saveIndirizzo(UUID id, IndirizzoDTO indirizzoDTO) throws NotFoundException {
        Utente utente = authService.getUtenteById(id);
        Indirizzo indirizzo = new Indirizzo(indirizzoDTO.via(), indirizzoDTO.civico(), indirizzoDTO.localita(), indirizzoDTO.cap(), utente);
        return indirizzoRepository.save(indirizzo);

    }

    public Indirizzo updateIndirizzo(UUID id, IndirizzoDTO indirizzoDTO) throws NotFoundException {
        Indirizzo indirizzo = getIndirizzoForId(id);
        Utente utente = authService.getUtenteById(id);
        indirizzo.setCap(indirizzoDTO.cap());
        indirizzo.setVia(indirizzoDTO.via());
        indirizzo.setCivico(indirizzoDTO.civico());

        return indirizzoRepository.save(indirizzo);
    }

    public void deleteAddress(UUID id) throws NotFoundException {
        Indirizzo indirizzo = getIndirizzoForId(id);
        indirizzoRepository.delete(indirizzo);
    }
}