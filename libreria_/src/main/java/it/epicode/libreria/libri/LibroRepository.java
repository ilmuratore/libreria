package it.epicode.libreria.libri;

import it.epicode.libreria.autori.Autore;
import it.epicode.libreria.case_editrici.CasaEditrice;
import it.epicode.libreria.saga.Saga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    public Libro findByTitolo(String titolo);
    public List<Libro> findByAutore(Autore autore);
    public List<Libro> findByCasaEditrice(CasaEditrice casaEditrice);

    public List<Libro> findBySaga(Saga saga);

    public boolean existsByTitolo(String titolo);


    public List<LibroResponsePrj> findAllBy();

}
