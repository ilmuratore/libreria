package it.epicode.libreria.persone;

import lombok.Data;

@Data
public class Response {
    private Long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;
}
