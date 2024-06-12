package it.epicode.libreria.prestiti;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {
    private Long id;
    private int numeroLibriPrestati;
    private List<String> titoliLibriPrestati = new ArrayList<>();
}
