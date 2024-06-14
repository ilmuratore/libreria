package it.epicode.libreria.prestiti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.libreria.persone.Persona;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "prestiti")
public class RegistroPrestito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int numeroPrestito;

    private LocalDate dataPrestito;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("prestiti")
    private Persona persona;
    // Persona | RegistroPrestito | Many
    // RegistroPrestito | Persona | One

    // il cascade type Remove fa si che cancellando un prestito a cascata vengono cancellate anche le righe dei libri prestati
    @OneToMany(mappedBy = "registroPrestito", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JsonIgnoreProperties("registroPrestito")
    private List<RegistroPrestitoLibri> registroPrestitoLibriList = new ArrayList<>();
    // RegistroPrestitoLibri | RegistroPrestito | One
    // RegistroPrestito      | RegistroPrestitoLibri | Many
}
