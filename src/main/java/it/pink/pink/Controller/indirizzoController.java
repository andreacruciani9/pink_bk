package it.pink.pink.Controller;

import it.pink.pink.Entity.Indirizzo;
import it.pink.pink.Exceptions.HandlerException;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.RequestDTO.IndirizzoDTO;
import it.pink.pink.Service.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
public class indirizzoController {
    @Autowired
    private IndirizzoService indirizzoService;

    //@GetMapping("/indirizzo")
    //public Page<Indirizzo>getAllIndirizzos(Pageable pageable) {
    //  return indirizzoService.
    //}
    @GetMapping("/indirizzo/{id}")
    public Indirizzo getInirizzoForId(@PathVariable UUID id) throws NotFoundException {
        return indirizzoService.getIndirizzoForId(id);
    }

    @PreAuthorize("hasAuthority('CLIENTE')")
    @PostMapping("/crea/indirizzo/{id}")
    public  Indirizzo creaIndirizzo(@PathVariable UUID id, @RequestBody IndirizzoDTO indirizzoDTO, BindingResult bindingResult) throws NotFoundException {
        HandlerException.notFoundException(bindingResult);
        return indirizzoService.saveIndirizzo(id, indirizzoDTO);
    }

    @PutMapping("/modifica/indirizzo/{id}")
    public  Indirizzo updateIndirizzo(@PathVariable UUID id,@RequestBody IndirizzoDTO indirizzoDTO,BindingResult bindingResult ) throws NotFoundException {
        HandlerException.notFoundException(bindingResult);
        return indirizzoService.updateIndirizzo(id,indirizzoDTO);
    }
    @DeleteMapping("/elimina/indirizzo/{id}")
    public  void  deleteIndirizzo(@PathVariable UUID id) throws NotFoundException {

            indirizzoService.deleteAddress(id);



    }
    }


