package it.pink.pink.Controller;

import it.pink.pink.Entity.Carello;
import it.pink.pink.Entity.Utente;
import it.pink.pink.Exceptions.HandlerException;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.RequestDTO.CarelloDTO;
import it.pink.pink.Service.AuthService;
import it.pink.pink.Service.CarelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@RestController

public class CarelloController {

    @Autowired
    private CarelloService carelloService;

    @Autowired
    private AuthService authController;

    @GetMapping("/carello")
    public Page<Carello> getAllCarello(Pageable pageable){
        return carelloService.getAllCarello(pageable);
    }
    @GetMapping("/carello/{id}")
    public Carello getCarelloById(@PathVariable UUID id ) throws NotFoundException {
        return carelloService.getCarelloById(id);
    }
    @PostMapping("/crea/carrello/{id}")
    public Carello creaCarrello(@PathVariable UUID id, @RequestBody CarelloDTO carelloDTO, BindingResult bindingResult) throws NotFoundException {
        HandlerException.notFoundException(bindingResult);
      System.out.print("crea carrello");
        return carelloService.saveCarello(id, carelloDTO);
    }
    //@PatchMapping("/carrello/aggiungi/{id}")
    //public Carello aggiungi(@PathVariable UUID id,CarelloDTO carelloDTO,BindingResult bindingResult) throws NotFoundException {
      //  HandlerException.notFoundException(bindingResult);
        //return carelloService.saveCarello(id,carelloDTO);
    //}
    @PatchMapping("/carrello/aggiungi/{id}")
    public Carello aggiungi(@PathVariable UUID id, @RequestBody CarelloDTO carelloDTO) throws NotFoundException {

        return carelloService.addProdotti(id, carelloDTO); // Utilizza il metodo addProdotti per aggiungere il prodotto al carrello
    }
   // @PatchMapping("/carrello/{id}/aggiungi-prodotto")
    //7public ResponseEntity<Carello> aggiungiProdottoAlCarello(@PathVariable UUID id, @RequestBody CarelloDTO carelloDTO) {
       // try {
         //   Carello carello = carelloService.addProdotti(id, List<UUID>carelloDTO.prodotti());
           // return ResponseEntity.ok(carello);
        //} catch (NotFoundException e) {
          //  return ResponseEntity.notFound().build();
        //}
    //}

    @PutMapping("/modifica/{id}")
    public   Carello updateCarello(@PathVariable UUID id,@RequestBody CarelloDTO carelloDTO) throws NotFoundException {
        return carelloService.updateCarello(id,carelloDTO);
    }
    @DeleteMapping("/elimina/{id}")
    public void eliminaCarello(@PathVariable UUID id) throws NotFoundException {
        carelloService.deleteCarello(id);
    }
}
