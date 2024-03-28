package it.pink.pink.Service;

import it.pink.pink.Entity.Prodotto;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.Repository.ProdottoRepository;
import it.pink.pink.RequestDTO.ProdottoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProdottiService {
    @Autowired
    private AuthService authService;

    @Autowired
    private ProdottoRepository prodottoRepository;

    public Page<Prodotto> getAllProdotti (Pageable pageable){
        return prodottoRepository.findAll(pageable);
    }

    public Prodotto getProdotti(UUID id)throws NotFoundException {
        Prodotto prodotti=prodottoRepository.findById(id).orElseThrow(()->new NotFoundException("Prodotti " + id + " not found"));
        return prodotti;
    }

    public Prodotto saveProdotti(ProdottoDTO prodottoDTO)throws NotFoundException{
        Prodotto prodotti=new Prodotto();
        prodotti.setName(prodottoDTO.name());
        prodotti.setDescrizione(prodottoDTO.descrizione());
        prodotti.setPrezzo(prodottoDTO.prezzo());
        prodotti.setQuantità(prodottoDTO.quantita());
        prodotti.setSconto(prodottoDTO.sconto());
        //  prodotti.setUrl(prodottoDTO.url());
        prodotti.setTipiCategorie(prodottoDTO.tipiCategorie());
        return prodottoRepository.save(prodotti);
    }
    public Prodotto updateProdotti(UUID id,ProdottoDTO prodottoDTO) throws NotFoundException {
        Prodotto prodotti=getProdotti(id);
        prodotti.setName(prodottoDTO.name());
        prodotti.setPrezzo(prodottoDTO.prezzo());
        prodotti.setDescrizione(prodottoDTO.descrizione());
        prodotti.setQuantità(prodottoDTO.quantita());
        prodotti.setTipiCategorie(prodottoDTO.tipiCategorie());
        return prodottoRepository.save(prodotti);
    }
    public Prodotto uploadUtl(UUID id,String url)throws NotFoundException{
      Optional<Prodotto>  prodotti=prodottoRepository.findById(id);
        if(prodotti.isPresent()){
            Prodotto prodotto= prodotti.get();
            prodotto.getImmagini(url);
            return  prodottoRepository.save(prodotto);
        }else {throw  new IllegalArgumentException("prodotto con id " + id+ "non trovato");
        }



    }
    public void deleteProdotti(UUID id) throws NotFoundException {
        Prodotto prodotto=getProdotti(id);
        prodottoRepository.delete(prodotto);
    }
    public List< Prodotto> getProdottiperNome(String nome) throws NotFoundException{
    List<Prodotto> prodotti =   prodottoRepository.findByName(nome);
        if (prodotti.isEmpty()) {
            throw new NotFoundException("Nessun prodotto trovato con il nome: " + nome);
        }
        return prodotti;
    }


}
