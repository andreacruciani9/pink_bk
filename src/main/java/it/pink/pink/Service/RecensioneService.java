package it.pink.pink.Service;

import it.pink.pink.Entity.Prodotto;
import it.pink.pink.Entity.Recensioni;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.Repository.ProdottoRepository;
import it.pink.pink.Repository.RecensioneRepository;
import it.pink.pink.RequestDTO.RecensioniDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
@Service
public class RecensioneService {
    @Autowired
    private ProdottiService prodottiService;
    @Autowired
    private RecensioneRepository recensioneRepository;
    @Autowired
    private AuthService authService;

    public Page<Recensioni> getAllRecensioni(Pageable pageable){
       return recensioneRepository.findAll(pageable);
    }
    public  Recensioni getRecensioniForId(UUID id) throws NotFoundException {
        Recensioni recensioni=recensioneRepository.findById(id).orElseThrow(()->new NotFoundException("Recensione co id: " + id + "non trovata"));
   return  recensioni;
    }

    public  Recensioni  saveRecensioni(UUID id,RecensioniDTO recensioniDTO) throws NotFoundException {
        Prodotto prodotto=prodottiService.getProdotti(id);
        Recensioni recensioni= new Recensioni(recensioniDTO.commenti(),recensioniDTO.data(),recensioniDTO.valutazione(),prodotto);
        return recensioneRepository.save(recensioni);
    }

    public  Recensioni UpdateRecensioni (UUID id ,RecensioniDTO recensioniDTO) throws NotFoundException {
        prodottiService.getProdotti(id);
        Recensioni recensioni=getRecensioniForId(id);
        recensioni.setCommenti(recensioniDTO.commenti());
        recensioni.setValutazione(recensioniDTO.valutazione());
        return  recensioneRepository.save(recensioni);
    };

    public  void  DeleteRecensione(UUID id) throws NotFoundException {
        prodottiService.getProdotti(id);
        Recensioni recensioni=getRecensioniForId(id);
        recensioneRepository.delete(recensioni);


    }


}
