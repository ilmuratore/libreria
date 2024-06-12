package it.epicode.libreria.persone;


import lombok.Data;

@Data
public class Request {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String cap;
    private String citta;
    private String codiceFiscale;
}
