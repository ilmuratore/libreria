package it.epicode.libreria.saga;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SagaRepository extends JpaRepository<Saga, Long > {
}
