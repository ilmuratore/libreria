package it.epicode.libreria.case_editrici;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasaEditriceService {

    @Autowired
    private CasaEditriceRepository repository;

    // GET ALL
    public List<CasaEditrice> findAll(){
        // Questo metodo restituisce tutti gli autori presenti nel database.
        return repository.findAll();
    }

    // GET per ID
    public Response findById(Long id){
        // Questo metodo cerca un entity nel database utilizzando l'ID.
        // Se l'entity non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("CasaEditrice non trovato");
        }
        // Se l'entity esiste, viene recuperato e le sue proprietà vengono copiate in un oggetto CasaEditriceResponse.
        CasaEditrice entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    @Transactional
    public Response create(Request request){
        // Questo metodo crea un nuovo entity.
        // Le proprietà dell'entity vengono copiate da un oggetto CasaEditriceRequest.
        CasaEditrice entity = new CasaEditrice();
        BeanUtils.copyProperties(request, entity);
        // L'entity viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto CasaEditriceResponse.
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
            throw new EntityNotFoundException("CasaEditrice non trovato");
        }
        // Se l'entity esiste, le sue proprietà vengono modificate con quelle presenti nell'oggetto CasaEditriceRequest.
        CasaEditrice entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        // L'entity modificato viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto CasaEditriceResponse.
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    //PATCH
    public Response modifyName(Long id, ChangeNameRequest request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("CasaEditrice non trovata");
        }
        CasaEditrice entity = repository.findById(id).get();
        // entity.setNome(request.getNome()); alternativa alla beanutils
        BeanUtils.copyProperties(request, entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;

    }

    //DELETE
    public String delete(Long id){
        // Questo metodo elimina un CasaEditrice dal database.
        // Prima verifica se l'CasaEditrice esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("CasaEditrice non trovato");
        }
        // Se l'CasaEditrice esiste, viene eliminato dal database.
        repository.deleteById(id);
        return "CasaEditrice eliminato";
    }
}
