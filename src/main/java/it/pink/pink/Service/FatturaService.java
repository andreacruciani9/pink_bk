package it.pink.pink.Service;

import it.pink.pink.Entity.Fattura;
import it.pink.pink.Entity.Ordini;
import it.pink.pink.Entity.Utente;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.Repository.FatturaRepository;
import it.pink.pink.RequestDTO.FatturaDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    AuthService authService;
    @Autowired
    OrdiniService ordiniService;

    public Page<Fattura> getAllFacture(Pageable pageable) {
        return fatturaRepository.findAll(pageable);

        };


    public Fattura getFactureForId(UUID id) throws NotFoundException {
        Fattura fattura = fatturaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("fatttura nr. " + id + " non trovata")
        );
        fattura.setUtente(fattura.getUtente());
        return fattura;
    }

    public Fattura saveFacture(UUID id ,FatturaDTO fatturaDTO) throws NotFoundException {
        Ordini ordini=ordiniService.getOrdiniForId(id);
        Fattura fattura = new Fattura(fatturaDTO.data(), fatturaDTO.importo(),fatturaDTO.numero(),fatturaDTO.ordini());
        System.out.println(fattura);
        fattura.setUtente(fatturaDTO.utente());
        fattura.setOrdini(ordini);
        fattura.gettotale(ordini);
        return fatturaRepository.save(fattura);
    }

    public Fattura updateFacture(UUID id, FatturaDTO fatturaDTO) throws NotFoundException, BadRequestException {
        Fattura fattura = getFactureForId(id);
        Utente utente = authService.getUtenteById(id);
        fattura.setUtente(utente);
        fattura.setData(fatturaDTO.data());
        fattura.setImporto(fatturaDTO.importo());


        return fatturaRepository.save(fattura);
    }

    public void deleteFacture(UUID id) throws NotFoundException {
        Fattura fattura = getFactureForId(id);
        fatturaRepository.delete(fattura);

    }
    public List<Fattura> findByUtenteUserName(String username) {
        return fatturaRepository.findByUtenteUsername(username);
    }
}
