package it.epicode.libreria.autori;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Request {

    @NotEmpty(message = "Il nome non puo' essere vuoto")
    private String nome;

    @NotEmpty(message = "Il cognome non puo' essere vuoto")
    private String cognome;

}

// AutoreRequest
// Questo oggetto viene utilizzato quando si desidera creare o modificare un autore.
// Le proprieta' di questo oggetto vengono copiate in un oggetto Autore, che viene successivamente salvato nel DB.
// AutoreRequest rappresenta il DTO dei dati in entrata che l'utente invia all'applicazione per creare o modificare un autore


// Se si utilizzano i Dto come in questo caso le validazioni devono essere applicate ad essi e non all'entita'