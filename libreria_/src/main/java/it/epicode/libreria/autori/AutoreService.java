package it.epicode.libreria.autori;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    public List<Autore> findAll(){
        return autoreRepository.findAll();
    }

    public AutoreResponse findById(Long id){
        if(!autoreRepository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        Autore autore = autoreRepository.findById(id).get();
        AutoreResponse autoreResponse = new AutoreResponse();

        BeanUtils.copyProperties(autore, autoreResponse); // sostiuisce il mapper con dipendenza di springboot
        return autoreResponse;
    }

    public AutoreResponse create(AutoreRequest autoreRequest){
        Autore autore = new Autore();

        BeanUtils.copyProperties(autoreRequest, autore);
        AutoreResponse autoreResponse = new AutoreResponse();

        BeanUtils.copyProperties(autore, autoreResponse);
        return autoreResponse;

    }
}
