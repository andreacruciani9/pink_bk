package it.pink.pink.Controller;

import it.pink.pink.Entity.Fattura;
import it.pink.pink.Exceptions.HandlerException;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.RequestDTO.FatturaDTO;
import it.pink.pink.Service.FatturaService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class FatturaController {
    @Autowired
   private FatturaService fatturaService;
    @GetMapping("/fattura")
    public Page<Fattura>getAllFattura(Pageable pageable){return fatturaService.getAllFacture(pageable);}

    @GetMapping("/fattura/{id}")
    public Fattura getFatturaById(@PathVariable UUID id) throws NotFoundException {
     return    fatturaService.getFactureForId(id);
    }
    @PostMapping("/crea/fattura/{id}")
    public Fattura creaFattura(@PathVariable UUID id, @RequestBody FatturaDTO fatturaDTO, BindingResult bindingResult) throws NotFoundException {
        HandlerException.notFoundException(bindingResult);

        return  fatturaService.saveFacture(id,fatturaDTO);
    }
    @PutMapping("/modifica/fattura/{id}")
    public Fattura modificaFattura(@PathVariable UUID id,@RequestBody FatturaDTO fatturaDTO, BindingResult bindingResult) throws NotFoundException, BadRequestException {
        HandlerException.notFoundException(bindingResult);
        return fatturaService.updateFacture(id, fatturaDTO);
    }
    @DeleteMapping("/elimina/fattura/{id}")
    public void deleteFattura(@PathVariable UUID id) throws NotFoundException {
        fatturaService.deleteFacture(id);
    }

    @GetMapping("/cerca/utente/{userName}")
    public ResponseEntity<List<Fattura>> findByUtenteUserName(@PathVariable String userName) {
        List<Fattura> fatture = fatturaService.findByUtenteUserName(userName);
        if (!fatture.isEmpty()) {
            return ResponseEntity.ok(fatture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
