package it.epicode.libreria.categoria;


import it.epicode.libreria.libri.Libro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "categorie")
@Data // crea automaticamente i getter e setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, unique = true)
    private String descrizione;

    @OneToMany(mappedBy = "categorie")
    @ToString.Exclude
    private List<Libro> libro;

}
