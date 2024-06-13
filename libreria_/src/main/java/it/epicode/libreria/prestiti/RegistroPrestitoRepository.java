package it.epicode.libreria.prestiti;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroPrestitoRepository extends JpaRepository<RegistroPrestito, Long> {

    public List<PrestitoResponsePrj> findAllBy();
}
