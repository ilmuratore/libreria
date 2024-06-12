package it.epicode.libreria.libri;

import lombok.Data;

import java.util.List;

@Data
public class Request {

    private String titolo;
    private List<Long> idCategorie;
    private Long idAutore;
    private Long idCasaEditrice;


}
