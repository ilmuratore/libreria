package it.epicode.libreria.autori;

import lombok.Data;

@Data
public class AutoreRequest {
    private String nome;
    private String cognome;

}

// AutoreRequest
// Questo oggetto viene utilizzato quando si desidera creare o modificare un autore.
// Le proprieta' di questo oggetto vengono copiate in un oggetto Autore, che viene successivamente salvato nel DB.
// AutoreRequest rappresenta il DTO dei dati in entrata che l'utente invia all'applicazione per creare o modificare un autore
