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
        Response response = new Response();
        repository.save(entity);
        BeanUtils.copyProperties(entity, response);
        return response;

    }
}
