package it.epicode.libreria.persone;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PersonaService {

    @Autowired
    private PersonaRepository repository;

    // GET ALL
    public List<PersonaResponsePrj> findAll(){
        // Questo metodo restituisce tutti gli autori presenti nel database utilizzando una repository Projection che restituisce un Dto
        return repository.findAllBy();
    }

    // GET per ID
    public Response findById(Long id){
        // Questo metodo cerca un entity nel database utilizzando l'ID.
        // Se l'entity non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Persona non trovato");
        }
        // Se l'entity esiste, viene recuperato e le sue proprietà vengono copiate in un oggetto PersonaResponse.
        Persona entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }




    // POST
    @Transactional
    public Response create(@Valid Request request){
        if(repository.existsByCodiceFiscaleAndNomeAndCognome(request.getCodiceFiscale(), request.getNome(), request.getCognome())){
            throw new EntityExistsException("La persona esiste gia' ");
        }
        Persona entity = new Persona();
        BeanUtils.copyProperties(request, entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public Response modify(Long id, @Valid Request request){
        // Questo metodo modifica un entity esistente.
        // Prima verifica se l'entity esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Persona non trovato");
        }
        // Se l'entity esiste, le sue proprietà vengono modificate con quelle presenti nell'oggetto PersonaRequest.
        Persona entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        // L'entity modificato viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto PersonaResponse.
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


    //DELETE
    public String delete(Long id){
        // Questo metodo elimina un Persona dal database.
        // Prima verifica se l'Persona esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Persona non trovato");
        }
        // Se l'Persona esiste, viene eliminato dal database.
        repository.deleteById(id);
        return "Persona eliminato";
    }
}
