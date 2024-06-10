package it.epicode.libreria.categoria;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    // GET ALL
    public List<Categoria> findAll(){
        // Questo metodo restituisce tutti gli autori presenti nel database.
        return repository.findAll();
    }

    // GET per ID
    public Response findById(Long id){
        // Questo metodo cerca un entity nel database utilizzando l'ID.
        // Se l'entity non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Categoria non trovato");
        }
        // Se l'entity esiste, viene recuperato e le sue proprietà vengono copiate in un oggetto CategoriaResponse.
        Categoria entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    public Response create(Request request){
        // Questo metodo crea un nuovo entity.
        // Le proprietà dell'entity vengono copiate da un oggetto CategoriaRequest.
        Categoria entity = new Categoria();
        BeanUtils.copyProperties(request, entity);
        // L'entity viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto CategoriaResponse.
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
            throw new EntityNotFoundException("Categoria non trovato");
        }
        // Se l'entity esiste, le sue proprietà vengono modificate con quelle presenti nell'oggetto CategoriaRequest.
        Categoria entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        // L'entity modificato viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto CategoriaResponse.
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


    //DELETE
    public String delete(Long id){
        // Questo metodo elimina un Categoria dal database.
        // Prima verifica se l'Categoria esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Categoria non trovato");
        }
        // Se l'Categoria esiste, viene eliminato dal database.
        repository.deleteById(id);
        return "Categoria eliminato";
    }
}
