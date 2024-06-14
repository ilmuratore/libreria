package it.epicode.libreria.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(3)
public class CategoriaRunner implements ApplicationRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (categoriaRepository.count() == 0) {
            List<Request> categorie = Arrays.asList(
                    new Request("Fiction"),
                    new Request("Non-Fiction"),
                    new Request("Science"),
                    new Request("History"),
                    new Request("Biography"),
                    new Request("Fantasy"),
                    new Request("Mystery"),
                    new Request("Romance"),
                    new Request("Horror"),
                    new Request("Self-Help")
            );

            categorie.forEach(request -> categoriaService.create(request));
            System.out.println("--- Categorie inserite ---");
        } else {
            System.out.println("--- Categorie già inserite ---");
        }
    }
}
