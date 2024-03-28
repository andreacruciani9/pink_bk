package it.pink.pink.Controller;

import it.pink.pink.Entity.Recensioni;
import it.pink.pink.Exceptions.HandlerException;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.RequestDTO.RecensioniDTO;
import it.pink.pink.Service.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
public class RecensioneController {
    @Autowired
    private RecensioneService recensioneService;

    @GetMapping("/recensioni")
    public Page<Recensioni>getAllRecensioni(Pageable pageable){return recensioneService.getAllRecensioni(pageable);}

    @GetMapping("/recensioni/{id}")
    public Recensioni getRecensioniById(UUID id) throws NotFoundException {return recensioneService.getRecensioniForId(id);}

    @PostMapping("/crea/recensioni/{id}")
    public Recensioni getRecensioni(@PathVariable UUID id, @RequestBody RecensioniDTO recensioniDTO, BindingResult bindingResult) throws NotFoundException {
        HandlerException.notFoundException(bindingResult);
    return recensioneService.saveRecensioni(id,recensioniDTO);
    }
    @PutMapping("/modifica/recensioni/{id}")
    public  Recensioni updateRecensioni(@PathVariable UUID id,@RequestBody RecensioniDTO recensioniDTO,BindingResult bindingResult) throws NotFoundException {
        HandlerException.notFoundException(bindingResult);
        return recensioneService.UpdateRecensioni(id,recensioniDTO);
    }
    @DeleteMapping("/delete/recensioni")
    public void delteRecensioni(@PathVariable UUID id) throws NotFoundException {
        recensioneService.DeleteRecensione(id);
    }
}
