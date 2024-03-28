package it.pink.pink.Controller;

import com.cloudinary.Cloudinary;
import it.pink.pink.Entity.Prodotto;
import it.pink.pink.Exceptions.BadRequestException;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.RequestDTO.ProdottoDTO;
import it.pink.pink.Service.ProdottiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
@RestController
public class ProdottoController {
    @Autowired
    private ProdottiService prodottiService;

    @Autowired
    private Cloudinary cloudinary;
    @GetMapping("/prodotto")
    public Page<Prodotto> getAllProdotti(Pageable pageable){return  prodottiService.getAllProdotti(pageable);}

    @GetMapping("/prodotto/{id}")

    public  Prodotto getProdottiById(@PathVariable UUID id)throws NotFoundException {
        return prodottiService.getProdotti(id);
    }

@PostMapping("/prodotto/save")
    public Prodotto saveProdotti(@RequestBody @Validated ProdottoDTO prodottoDTO, BindingResult bindingResult) throws BadRequestException, NotFoundException {
        if(bindingResult.hasErrors()){
            throw  new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return prodottiService.saveProdotti(prodottoDTO);
}

@PutMapping("/prodotto/update/{id}")
    public Prodotto updateProdotti(@PathVariable UUID id,@RequestBody @Validated ProdottoDTO prodottoDTO,BindingResult bindingResult) throws BadRequestException, NotFoundException {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());


        }
        return  prodottiService.updateProdotti(id,prodottoDTO);
}

@DeleteMapping("/prodotto/delete/{id}")
    public void deleteProdotti(@PathVariable UUID id ) throws NotFoundException {

        prodottiService.deleteProdotti(id);

}



//@PatchMapping("/upload/{id}/")
   // public Prodotto uploadProdotti(@PathVariable UUID id, @RequestParam("upload")MultipartFile file)throws IOException,NotFoundException{
    //return prodottiService.uploadUtl(id,(String) cloudinary.uploader().upload(file.getBytes(),new HashMap()).get("url"));
//}
@PatchMapping("/upload/{id}")
public Prodotto uploadProdotti(@PathVariable UUID id, @RequestParam("upload") MultipartFile file) throws IOException, NotFoundException {
    return prodottiService.uploadUtl(id,     (String) cloudinary.uploader().upload(file.getBytes(), new HashMap<>()).get("url"));

}

    @GetMapping("/cerca-by-nome/name")
    private List<Prodotto> getAllprodottiByname(@RequestParam String nome) throws NotFoundException {
        return  prodottiService.getProdottiperNome(nome);
}



}



