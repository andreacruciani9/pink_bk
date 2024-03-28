package it.pink.pink.Controller;

import it.pink.pink.Entity.Prodotto;
import it.pink.pink.Entity.Sconto;
import it.pink.pink.Exceptions.BadRequestException;
import it.pink.pink.Exceptions.HandlerException;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.RequestDTO.ScontoDTO;
import it.pink.pink.Service.ProdottiService;
import it.pink.pink.Service.ScontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ScontoController {
    @Autowired
    private ProdottiService prodottiService;

    @Autowired
    private ScontoService scontoService;

    @GetMapping("/")
    public Page<Sconto> getAllSconto(Pageable pageable){
        return scontoService.getAllSconto(pageable);
    }

    @GetMapping("/{id}")
    public Sconto getScontoById(@PathVariable String id) throws NotFoundException {
        return scontoService.getScontoForId(id);
    }
    @PostMapping("/sconto/{id}")

    public Sconto saveSconti(@PathVariable UUID id, @RequestBody  ScontoDTO scontoDTO, BindingResult bindingResult) throws BadRequestException, NotFoundException {
        HandlerException.notFoundException(bindingResult);

        return scontoService.saveSconto(scontoDTO,id);

    }
    @PutMapping("/modifica/sconto/{id}")
    public Sconto updateSconto(@PathVariable UUID id,@RequestBody ScontoDTO scontoDTO,BindingResult bindingResult) throws NotFoundException {
        HandlerException.notFoundException(bindingResult);
        return  scontoService.updateSconto(scontoDTO.code(), scontoDTO);
    }

    @DeleteMapping("/sconto/delete/{id}")
    @PreAuthorize("hasAutority('AMMINISTRATORE')")

    public  void  deleteSconto(@PathVariable String code,@PathVariable UUID id) throws NotFoundException {
        scontoService.deleteSconto(code,id);;
    }
}
