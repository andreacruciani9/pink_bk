package it.pink.pink.Service;

import it.pink.pink.Entity.Carello;
import it.pink.pink.Entity.Prodotto;
import it.pink.pink.Entity.Utente;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.Repository.CarellloRepository;
import it.pink.pink.Repository.ProdottoRepository;
import it.pink.pink.RequestDTO.CarelloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class CarelloService {
@Autowired
    private CarellloRepository carellloRepository;
@Autowired
    private AuthService authService;
@Autowired
    private ProdottiService prodottiService;
@Autowired
private ProdottoRepository prodottoRepository;

public Page<Carello>getAllCarello(Pageable pageable){
  return   carellloRepository.findAll(pageable);
}

public  Carello getCarelloById(UUID id) throws NotFoundException {
    Carello carell = carellloRepository.findById(id).orElseThrow(()-> new NotFoundException("id carello : " + id + " inesistente"));
    return carell;
}
public Carello saveCarello(UUID id,CarelloDTO carelloDTO) throws NotFoundException {
    Utente utente=authService.getUtenteById(id);

    Carello carello=new Carello(carelloDTO.utente());


   carello.setUtente(utente);
    return  carellloRepository.save(carello);
}
//public Carello addProdotti(UUID id, CarelloDTO carelloDTO) throws NotFoundException {
    //Prodotto prodotto=prodottoRepository.getReferenceById(id);
    //Carello carello=getCarelloById(id);
    //carello.addProdotti(prodotto);
    //carello.setProdotti(carelloDTO.prodotti());
  //return   carellloRepository.save(carello)
  //  ;

//}
public Carello addProdotti(UUID id, CarelloDTO carelloDTO) throws NotFoundException {


    Carello carello = getCarelloById(id);
    for (int i = 0; i < carelloDTO.prodotti().size(); i++) {
        Prodotto prodotto = carelloDTO.prodotti().get(i);
        prodotto=prodottoRepository.findById(prodotto.getId()).get();
    carello.addProdotti(prodotto); // Aggiungi il prodotto al carrello
  }
    return carellloRepository.save(carello);
}
  //public Carello addProdotti(UUID id, List<UUID> listaIdProdotti) throws NotFoundException {
   //   Carello carello = getCarelloById(id);
    //  for (UUID prodottoId : listaIdProdotti) {
    //      Prodotto prodotto = prodottoRepository.getReferenceById(prodottoId);
      //    carello.addProdotti(prodotto);
      //}
      //return carellloRepository.save(carello);
  //}

    public Carello updateCarello(UUID id,CarelloDTO carelloDTO) throws NotFoundException {
    Utente utente=authService.getUtenteById(id);

    Carello carello=getCarelloById(id);
  //  carello.setProdotti(carelloDTO.prodotti());
    return carellloRepository.save(carello);
}

public  void deleteCarello(UUID id) throws NotFoundException {
    Carello carello=getCarelloById(id);
    carellloRepository.delete(carello);
}

}
