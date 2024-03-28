package it.pink.pink.Controller;

import it.pink.pink.Entity.Carello;
import it.pink.pink.Entity.Ordini;
import it.pink.pink.Exceptions.HandlerException;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.RequestDTO.CarelloDTO;
import it.pink.pink.RequestDTO.OrdiniDTO;
import it.pink.pink.Service.OrdiniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
public class OrdineController {
    @Autowired
    private OrdiniService ordiniService;

    @GetMapping("/ordini/{id}")
    public Ordini getOrdiniById(@PathVariable UUID id){
        return ordiniService.getOrdiniForId(id);
    }
    @PostMapping("crea/ordine/{id}")
    public Ordini creaOrdine(@PathVariable UUID id, @RequestBody OrdiniDTO ordiniDTO, BindingResult bindingResult) throws NotFoundException {
        HandlerException.notFoundException(bindingResult);
        return ordiniService.creaOrdini(id, ordiniDTO);
    }
    @PatchMapping("aggungi/ordini/{id}")
    public Ordini aggiungiOrdini(@PathVariable UUID id, @RequestBody OrdiniDTO ordiniDTO) throws NotFoundException {

        return ordiniService.addOrdini(id, ordiniDTO); // Utilizza il metodo addProdotti per aggiungere il prodotto al carrello
    }
    @PatchMapping ("modifica/ordine/{id}")
    public Ordini modificaOrdine(@PathVariable UUID id,@RequestBody OrdiniDTO ordiniDTO,BindingResult bindingResult) throws NotFoundException {
        HandlerException.notFoundException(bindingResult);
        return ordiniService.updateOrdini(id, ordiniDTO);
    }

    @DeleteMapping("cancella/ordine/{id}")
    public void cancelllaOrdine(@PathVariable UUID id){
        ordiniService.deleteOrdine(id);
    }
}
