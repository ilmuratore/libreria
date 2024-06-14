package it.epicode.libreria.libri;

import it.epicode.libreria.autori.Autore;
import it.epicode.libreria.autori.AutoreRepository;
import it.epicode.libreria.case_editrici.CasaEditrice;
import it.epicode.libreria.case_editrici.CasaEditriceRepository;
import it.epicode.libreria.categoria.Categoria;
import it.epicode.libreria.categoria.CategoriaRepository;
import it.epicode.libreria.saga.Saga;
import it.epicode.libreria.saga.SagaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // elimina la moltiplicazione delle autowired su piu dipendenze usando le variabili final
public class LibroService {

    private final LibroRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final CasaEditriceRepository casaEditriceRepository;
    private final AutoreRepository autoreRepository;
    private final SagaRepository sagaRepository;
    // private final  + // nuova dependecyInjection

    //POST
    @Transactional
    public Response create(Request request){
        if (!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore not found");
        }
        if (!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa Editrice not found");
        }
        if (!sagaRepository.existsById(request.getIdSaga())){
            throw  new EntityNotFoundException("Saga not found");
        }
        Libro entity = new Libro();
        BeanUtils.copyProperties(request, entity);
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Categoria> categorie = categoriaRepository.findAllById(request.getIdCategorie());
        Saga saga = sagaRepository.findById(request.getIdSaga()).get();
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setCategorie(categorie);
        entity.setSaga(saga);
        Response response = new Response();
        repository.save(entity);
        BeanUtils.copyProperties(entity, response);
        return response;

    }

    //PUT
    public Response modify(Long id, Request request){
        if(!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore not found");
        }
        if(!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw  new EntityNotFoundException("Casa editrice not found");
        }
        if(!sagaRepository.existsById(request.getIdSaga())){
            throw new EntityNotFoundException("Saga not found");
        }
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        Libro entity = repository.findById(id).get();
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Categoria> categorie = categoriaRepository.findAllById(request.getIdCategorie());
        Saga saga = sagaRepository.findById(request.getIdSaga()).get();
        BeanUtils.copyProperties(request, entity);
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setCategorie(categorie);
        entity.setSaga(saga);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // PUT con oggetto Optional solo per Esempio
    public Response modify1(Long id, Request request){
        Optional<Autore> autore = autoreRepository.findById(request.getIdAutore());
        if(autore.isEmpty()){
            throw new EntityNotFoundException("Autore not found");
        }
        Optional<CasaEditrice> casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice());
        if(casaEditrice.isEmpty()){
            throw new EntityNotFoundException("Casa editrice not found");
        }
        Optional<Libro> libro = repository.findById(id);
        if(libro.isEmpty()){
            throw new EntityNotFoundException("Libro not found");
        }
        List<Categoria> categoria = categoriaRepository.findAllById(request.getIdCategorie());
        libro.get().setAutore(autore.get());
        libro.get().setCasaEditrice(casaEditrice.get());
        libro.get().setCategorie(categoria);
        BeanUtils.copyProperties(request, libro);
        repository.save(libro.get());
        Response response = new Response();
        BeanUtils.copyProperties(libro, response);
        return response;
    }

    //GET (da commentare la findById)

    public List<LibroResponsePrj> findAll(){
        return repository.findAllBy();
    }


    @Transactional // annotazione che serve a mantenere la connessione attiva in caso di relazioni many nelle classi per eseguire le operazioni in un unica connessione in caso di errori nelle tabelle
    public CompleteResponse findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        Libro entity = repository.findById(id).get();
        CompleteResponse completeResponse = new CompleteResponse();
        BeanUtils.copyProperties(entity, completeResponse);
        completeResponse.setCategorie(entity.getCategorie());
        it.epicode.libreria.autori.Response autoreResponse = new it.epicode.libreria.autori.Response();
        BeanUtils.copyProperties(entity.getAutore(), autoreResponse);
        it.epicode.libreria.case_editrici.LightResponse casaEditriceResponse = new it.epicode.libreria.case_editrici.LightResponse();
        BeanUtils.copyProperties(entity.getCasaEditrice(), casaEditriceResponse);
        it.epicode.libreria.saga.Response sagaResponse = new it.epicode.libreria.saga.Response();
        BeanUtils.copyProperties(entity.getSaga(), sagaResponse);
        completeResponse.setAutore(autoreResponse);
        completeResponse.setCasaEditrice(casaEditriceResponse);
        completeResponse.setSaga(sagaResponse);
        return completeResponse;

    }

    //GET con query nel Repository per recupero con attributi specifici classe/Dto
    public List<Libro> findByAutore(Autore autore){
        return repository.findByAutore(autore);
    }

    public List<Libro> findByCasaEditrice(CasaEditrice casaEditrice){
        return repository.findByCasaEditrice(casaEditrice);
    }

    //DELETE
    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        repository.deleteById(id);
        return "Libro eliminato";
    }


    // esempio di creazione di entity in entity
    @Transactional
    public Response createBookAndAuthor(Request bookRequest, it.epicode.libreria.autori.CompleteRequest authorRequest){
        Autore autore;
        if(!autoreRepository.existsById(authorRequest.getId())){
            autore = new Autore();
            autore.setNome(authorRequest.getNome());
            autore.setCognome(authorRequest.getCognome());
            autoreRepository.save(autore);
        } else {
            autore = autoreRepository.findById(authorRequest.getId()).get();
        }
        if(!casaEditriceRepository.existsById(bookRequest.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa editrice not found");
        }
        CasaEditrice casaEditrice = casaEditriceRepository.findById(bookRequest.getIdCasaEditrice()).get();
        if(!sagaRepository.existsById(bookRequest.getIdSaga())){
            throw new EntityNotFoundException("Saga not found");
        }
        Saga saga = sagaRepository.findById(bookRequest.getIdSaga()).get();
        Libro entity = new Libro();
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setSaga(saga);
        entity.setCategorie(categoriaRepository.findAllById(bookRequest.getIdCategorie()));
        BeanUtils.copyProperties(bookRequest, entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


    // create *ATTENZIONE* funziona solo se presente proprieta' CASCADE alla tabella in relazione dell'entity

    public Response creaLibriEautori(CreateBookAndAuthorRequest request){
        Autore autore = new Autore();
        BeanUtils.copyProperties(request, autore);
        Libro entity = new Libro();
        if(!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa editrice not found");
        }
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        if(!sagaRepository.existsById(request.getIdSaga())){
            throw new EntityNotFoundException("Saga not found");
        }
        Saga saga = sagaRepository.findById(request.getIdSaga()).get();
        BeanUtils.copyProperties(request, entity);
        entity.setCategorie(categoriaRepository.findAllById(request.getIdCategorie()));
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setSaga(saga);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(response, entity);
        return response;
    }



    // ESEMPIO *

    public Response bookAndAuthorModify(Long id, ModifyBookAndAuthorRequest request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        Libro entity = repository.findById(id).get(); // grazie al MERGE bookAndAuthorModify
        entity.setTitolo(request.getTitolo());
        entity.getAutore().setNome(request.getNomeAutore()); // grazie alla relazione e' possibile richiamare direttamente il set per modificare il nome avendo gia' richiamato l'entity
        repository.save(entity); // grazie alla proprieta' MERGE di CASCADE l'autore viene aggiornato in automatico
        Response response = new Response();
        BeanUtils.copyProperties(response, entity);
        return response;


    }
}
