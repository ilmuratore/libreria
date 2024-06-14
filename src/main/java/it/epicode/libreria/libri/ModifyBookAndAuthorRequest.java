package it.epicode.libreria.libri;

import lombok.Data;

@Data
public class ModifyBookAndAuthorRequest {

    private String titolo;
    private String nomeAutore;

}
