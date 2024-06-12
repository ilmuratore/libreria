package it.epicode.libreria.libri;

import it.epicode.libreria.autori.Autore;
import it.epicode.libreria.autori.AutoreRepository;
import it.epicode.libreria.case_editrici.CasaEditrice;
import it.epicode.libreria.case_editrici.CasaEditriceRepository;
import it.epicode.libreria.categoria.Categoria;
import it.epicode.libreria.categoria.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    LibroRepository repository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    CasaEditriceRepository casaEditriceRepository;
    @Autowired
    AutoreRepository autoreRepository;

    //POST
    public Response create(Request request){
        if (!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore not found");
        }
        if (!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa Editrice not found");
        }
        Libro entity = new Libro();
        BeanUtils.copyProperties(request, entity);
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Categoria> categorie = categoriaRepository.findAllById(request.getIdCategorie());
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setCategorie(categorie);
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
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        Libro entity = repository.findById(id).get();
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Categoria> categorie = categoriaRepository.findAllById(request.getIdCategorie());
        BeanUtils.copyProperties(request, entity);
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setCategorie(categorie);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    // PUT con oggetto Optional
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

    //GET
    public CompleteResponse FindById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        Libro entity = repository.findById(id).get();
        CompleteResponse completeResponse = new CompleteResponse();
        BeanUtils.copyProperties(entity, completeResponse);
        completeResponse.setCategorie(entity.getCategorie());
        it.epicode.libreria.autori.Response autoreResponse = new it.epicode.libreria.autori.Response();
        BeanUtils.copyProperties(entity.getAutore(), autoreResponse);
        it.epicode.libreria.case_editrici.Response casaEditriceResponse = new it.epicode.libreria.case_editrici.Response();
        BeanUtils.copyProperties(entity.getCasaEditrice(), casaEditriceResponse);
        completeResponse.setAutore(autoreResponse);
        completeResponse.setCasaEditrice(casaEditriceResponse);
        return completeResponse;

    }
}
