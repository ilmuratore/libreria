package it.epicode.libreria.saga;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Request {

    private String name;
    private int numeroVolumi;

}
