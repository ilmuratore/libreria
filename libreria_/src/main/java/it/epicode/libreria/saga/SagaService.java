package it.epicode.libreria.saga;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaService {

    @Autowired
    private SagaRepository repository;

    // GET ALL
    public List<Saga> findAll(){
        // Questo metodo restituisce tutti gli autori presenti nel database.
        return repository.findAll();
    }

    // GET per ID
    public Response findById(Long id){
        // Questo metodo cerca un entity nel database utilizzando l'ID.
        // Se l'entity non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Saga non trovato");
        }
        // Se l'entity esiste, viene recuperato e le sue proprietà vengono copiate in un oggetto SagaResponse.
        Saga entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    public Response create(Request request){
        // Questo metodo crea un nuovo entity.
        // Le proprietà dell'entity vengono copiate da un oggetto SagaRequest.
        Saga entity = new Saga();
        BeanUtils.copyProperties(request, entity);
        // L'entity viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto SagaResponse.
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
            throw new EntityNotFoundException("Saga non trovato");
        }
        // Se l'entity esiste, le sue proprietà vengono modificate con quelle presenti nell'oggetto SagaRequest.
        Saga entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        // L'entity modificato viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto SagaResponse.
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


    //DELETE
    public String delete(Long id){
        // Questo metodo elimina un Saga dal database.
        // Prima verifica se l'Saga esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Saga non trovato");
        }
        // Se l'Saga esiste, viene eliminato dal database.
        repository.deleteById(id);
        return "Saga eliminato";
    }
}
