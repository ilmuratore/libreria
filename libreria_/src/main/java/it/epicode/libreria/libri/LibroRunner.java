package it.epicode.libreria.libri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(10)
public class LibroRunner implements ApplicationRunner {

    @Autowired
    private LibroService libroService;

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (libroRepository.count() == 0) {
            List<Request> libri = Arrays.asList(
                    new Request("Il signore degli anelli", Arrays.asList(1L, 2L), 1L, 1L, 1L),
                    new Request("Harry Potter e la pietra filosofale", Arrays.asList(3L, 4L), 2L, 2L, 2L),
                    new Request("1984", Arrays.asList(5L, 6L), 3L, 3L, 3L),
                    new Request("Il nome della rosa", Arrays.asList(7L, 8L), 4L, 4L, 4L),
                    new Request("Il vecchio e il mare", Arrays.asList(9L, 10L), 1L, 1L, 1L),
                    new Request("Orgoglio e pregiudizio", Arrays.asList(11L, 12L), 2L, 2L, 2L),
                    new Request("Cime tempestose", Arrays.asList(13L, 14L), 3L, 3L, 3L),
                    new Request("L'ombra del vento", Arrays.asList(15L, 16L), 4L, 4L, 4L),
                    new Request("Guerra e pace", Arrays.asList(17L, 18L), 1L, 1L, 1L),
                    new Request("La ragazza di Bube", Arrays.asList(19L, 20L), 2L, 2L, 2L),
                    new Request("Il codice da Vinci", Arrays.asList(1L, 2L), 3L, 3L, 3L),
                    new Request("Don Chisciotte della Mancia", Arrays.asList(3L, 4L), 4L, 4L, 4L),
                    new Request("Il leone, la strega e l'armadio", Arrays.asList(5L, 6L), 1L, 1L, 1L),
                    new Request("Il ritratto di Dorian Gray", Arrays.asList(7L, 8L), 2L, 2L, 2L),
                    new Request("Il piccolo principe", Arrays.asList(9L, 10L), 3L, 3L, 3L),
                    new Request("Le cronache di Narnia", Arrays.asList(11L, 12L), 4L, 4L, 4L),
                    new Request("Il giovane Holden", Arrays.asList(13L, 14L), 1L, 1L, 1L),
                    new Request("Il padrino", Arrays.asList(15L, 16L), 2L, 2L, 2L),
                    new Request("Lo hobbit", Arrays.asList(17L, 18L), 3L, 3L, 3L),
                    new Request("Il nome della rosa", Arrays.asList(19L, 20L), 4L, 4L, 4L)
            );

            libri.forEach(libroService::create);
            System.out.println("--- Libri inseriti ---");
        }
    }
}
