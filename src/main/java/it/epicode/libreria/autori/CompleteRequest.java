package it.epicode.libreria.autori;

import lombok.Data;

@Data
public class CompleteRequest {
    private Long id;
    private String nome;
    private String cognome;

}

// aggiunto id per poter effettuare operazioni avanzate