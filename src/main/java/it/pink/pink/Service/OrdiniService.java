package it.pink.pink.Service;

import it.pink.pink.Entity.Carello;
import it.pink.pink.Entity.Ordini;
import it.pink.pink.Entity.Prodotto;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.Repository.CarellloRepository;
import it.pink.pink.Repository.OrdineRepository;
import it.pink.pink.Repository.ProdottoRepository;
import it.pink.pink.RequestDTO.OrdiniDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Logger;
@Service
public class OrdiniService {
    @Autowired
    private AuthService authService;
    @Autowired
    private OrdineRepository ordineRepository;
@Autowired
private CarelloService carelloService;
    @Autowired
   private ProdottoRepository prodottoRepository;


public Page<Ordini> getAllOrdini(Pageable pageable){
   return ordineRepository.findAll(pageable);
}
public  Ordini getOrdiniForId(UUID id){
    Ordini ordini=ordineRepository.findById(id).orElseThrow(()->new IllegalStateException("Not found"));
     return ordini;
}
public  Ordini creaOrdini(UUID id,OrdiniDTO ordiniDTO) throws NotFoundException {
    Carello carello= carelloService.getCarelloById(id);
    Ordini ordini=new Ordini(ordiniDTO.dataOrdine(),ordiniDTO.TotaleCosto(),ordiniDTO.statoOrdine(),ordiniDTO.prodottiList(),ordiniDTO.carrello());
   ordini.setCarello(carello);
    return ordineRepository.save(ordini);}

    public Ordini addOrdini(UUID id,OrdiniDTO ordiniDTO){
    Ordini ordini=getOrdiniForId(id);
    for (int i = 0; i < ordiniDTO.prodottiList().size(); i++) {
        Prodotto prodotto = ordiniDTO.prodottiList().get(i);
        prodotto = prodottoRepository.findById(prodotto.getId()).get();
        ordini.addProdotti(prodotto); // Aggiungi il prodotto al carrello
    }
    ordini.setIndirizzo(ordiniDTO.indirizzo());
    return ordineRepository.save(ordini);
}



public Ordini updateOrdini(UUID id,OrdiniDTO ordiniDTO) throws NotFoundException{

    Ordini ordini=getOrdiniForId(id);
    ordini.setIndirizzo(ordiniDTO.indirizzo());
    ordini.setProdottiList(ordiniDTO.prodottiList());
    return ordini;
}
public void deleteOrdine(UUID id ){
    Ordini ordini=getOrdiniForId(id);
    ordineRepository.delete(ordini);
  //  logger.info("Ord");
}


}
