package it.pink.pink.Service;

import it.pink.pink.Entity.Sconto;
import it.pink.pink.Exceptions.NotFoundException;
import it.pink.pink.Repository.ProdottoRepository;
import it.pink.pink.Repository.ScontoRepository;
import it.pink.pink.RequestDTO.ScontoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ScontoService {
    @Autowired
    private ScontoRepository scontoRepository;
    @Autowired
    private AuthService authService;

    @Autowired
    private ProdottoRepository prodottoRepository;

    public Page<Sconto> getAllSconto(Pageable pageable) {
        return scontoRepository.findAll(pageable);

    }

    public Sconto getScontoForId(String id) throws NotFoundException {
        Sconto sconto=scontoRepository.getReferenceById(id);
        return sconto;
    }

    public Sconto saveSconto(ScontoDTO scontoDTO, UUID id) throws NotFoundException{

        Sconto sconto=new Sconto(scontoDTO.code(),scontoDTO.percentuale(),scontoDTO.importoSconto());
        System.out.println(sconto);
        sconto.apllicaSconto(prodottoRepository.getReferenceById(id));
        System.out.println(sconto);
        return scontoRepository.save(sconto);
    }

    public Sconto updateSconto(String code,ScontoDTO scontoDTO)throws  NotFoundException{
        Sconto sconto=getScontoForId(code);
        // Cliente cliente=clienteService.getById(scontoDTO.clienteId());
        sconto.setCode(scontoDTO.code());
        sconto.setPercentuale(scontoDTO.percentuale());
        sconto.setImportoSconto(scontoDTO.importoSconto());
        return scontoRepository.save(sconto);
    }
    public  void  deleteSconto(String code,UUID id) throws NotFoundException {
        Sconto sconto=getScontoForId(code);

        sconto.detraisconto(prodottoRepository.getReferenceById(id));

        scontoRepository.delete(sconto);
}}
