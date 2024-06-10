package it.epicode.libreria.categoria;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorie")
@Data // crea automaticamente i getter e setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, unique = true)
    private String descrizione;
}
