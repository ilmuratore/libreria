package it.epicode.libreria.autori;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated // permette le operazioni di validazione nel service
public class AutoreService {

    @Autowired
    private AutoreRepository repository;

    // GET ALL
    public List<AutoreResponsePrj> findAll(){
        // Questo metodo restituisce tutti gli autori presenti nel database.
        return repository.findAllBy();
    }

    // GET per ID
    public Response findById(Long id){
        // Questo metodo cerca un entity nel database utilizzando l'ID.
        // Se l'entity non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        // Se l'entity esiste, viene recuperato e le sue proprietà vengono copiate in un oggetto AutoreResponse.
        Autore entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST * Aggiunta Validazione *
    @Transactional
    public Response create(@Valid Request request){ // annotazione @Valid controlla validazione dto's
        // Questo metodo crea un nuovo entity.
        // Le proprietà dell'entity vengono copiate da un oggetto AutoreRequest.
        Autore entity = new Autore();
        BeanUtils.copyProperties(request, entity);
        // L'entity viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto AutoreResponse.
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public Response modify(Long id, Request request){
        // Questo metodo modifica un entity esistente.
        // Prima verifica se l'entity esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        // Se l'entity esiste, le sue proprietà vengono modificate con quelle presenti nell'oggetto AutoreRequest.
        Autore entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        // L'entity modificato viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto AutoreResponse.
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    //DELETE
    public String delete(Long id){
        // Questo metodo elimina un autore dal database.
        // Prima verifica se l'autore esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Autore non trovato");
        }
        // Se l'autore esiste, viene eliminato dal database.
        repository.deleteById(id);
        return "Autore eliminato";
    }
}
