package it.epicode.libreria.case_editrici;


import it.epicode.libreria.libri.Libro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "case_editrici")
public class CasaEditrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, unique = true)
    private String nome;
    private String indirizzo;
    private String citta;
    @Column(length = 5)
    private String cap;

    @OneToMany(mappedBy = "casaEditrice")
    @ToString.Exclude
    private List<Libro> libro;


}
