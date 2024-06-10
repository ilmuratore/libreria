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

    // GET ALL
    public List<Autore> findAll(){
        // Questo metodo restituisce tutti gli autori presenti nel database.
        return autoreRepository.findAll();
    }

    // GET per ID
    public AutoreResponse findById(Long id){
        // Questo metodo cerca un autore nel database utilizzando l'ID.
        // Se l'autore non esiste, viene generata un'eccezione.
        if(!autoreRepository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        // Se l'autore esiste, viene recuperato e le sue proprietà vengono copiate in un oggetto AutoreResponse.
        Autore autore = autoreRepository.findById(id).get();
        AutoreResponse autoreResponse = new AutoreResponse();
        BeanUtils.copyProperties(autore, autoreResponse);
        return autoreResponse;
    }

    // POST
    public AutoreResponse create(AutoreRequest autoreRequest){
        // Questo metodo crea un nuovo autore.
        // Le proprietà dell'autore vengono copiate da un oggetto AutoreRequest.
        Autore autore = new Autore();
        BeanUtils.copyProperties(autoreRequest, autore);
        // L'autore viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto AutoreResponse.
        AutoreResponse autoreResponse = new AutoreResponse();
        BeanUtils.copyProperties(autore, autoreResponse);
        autoreRepository.save(autore);
        return autoreResponse;
    }

    // PUT
    public AutoreResponse modify(Long id, AutoreRequest autoreRequest){
        // Questo metodo modifica un autore esistente.
        // Prima verifica se l'autore esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!autoreRepository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        // Se l'autore esiste, le sue proprietà vengono modificate con quelle presenti nell'oggetto AutoreRequest.
        Autore autore = autoreRepository.findById(id).get();
        BeanUtils.copyProperties(autoreRequest, autore);
        // L'autore modificato viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto AutoreResponse.
        autoreRepository.save(autore);
        AutoreResponse autoreResponse = new AutoreResponse();
        BeanUtils.copyProperties(autore, autoreResponse);
        return autoreResponse;
    }

    //DELETE
    public String delete(Long id){
        // Questo metodo elimina un autore dal database.
        // Prima verifica se l'autore esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!autoreRepository.existsById(id)){
            throw  new EntityNotFoundException("Autore non trovato");
        }
        // Se l'autore esiste, viene eliminato dal database.
        autoreRepository.deleteById(id);
        return "Autore eliminato";
    }
}
