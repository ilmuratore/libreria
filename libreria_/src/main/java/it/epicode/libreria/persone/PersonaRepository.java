package it.epicode.libreria.persone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    public Persona findByCodiceFiscaleAndNomeAndCognome(String codiceFiscale, String nome, String cognome);
    public boolean existsByCodiceFiscaleAndNomeAndCognome(String codiceFiscale, String nome, String cognome);
    
}
