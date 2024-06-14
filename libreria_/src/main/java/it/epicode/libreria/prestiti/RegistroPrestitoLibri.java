package it.epicode.libreria.prestiti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.libreria.libri.Libro;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "libri_prestati")
public class RegistroPrestitoLibri {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"autore", "casaEditrice", "categorie", "saga"})
    private Libro libro;
    // Libro      | RegistroPrestitoLibri  | Many
    // RegistroPrestitoLibri | Libro       | One

    private boolean isRestituito = false;

    @ManyToOne
    private RegistroPrestito registroPrestito;
    // RegistroPrestito | RegistroPrestitoLibri | Many
    // RegistroPrestitoLibri | RegistroPrestiti | One

}
