package it.epicode.libreria.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(4)
public class SagaRunner implements ApplicationRunner {

    @Autowired
    private SagaRepository sagaRepository;

    @Autowired
    private SagaService sagaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (sagaRepository.count() == 0) {
            List<Request> saghe = Arrays.asList(
                    new Request("Harry Potter", 7),
                    new Request("Il Signore degli Anelli", 3),
                    new Request("Cronache del Ghiaccio e del Fuoco", 5),
                    new Request("Millennium", 6)
            );

            saghe.forEach(request -> sagaService.create(request));
            System.out.println("--- Saghe inserite ---");
        } else {
            System.out.println("--- Saghe già inserite ---");
        }
    }
}
