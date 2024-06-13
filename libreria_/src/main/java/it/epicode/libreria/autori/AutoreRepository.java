package it.epicode.libreria.autori;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoreRepository extends JpaRepository<Autore, Long> {

    public List<AutoreResponsePrj> findAllAutoreResponsePrj();

    public List<Autore> findAllByNome(String nome);


}
