package it.epicode.libreria.libri;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libri")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;

    @PostMapping
    public ResponseEntity<Response> createLibro(@Valid @RequestBody Request request) {
        Response response = libroService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> modifyLibro(@PathVariable Long id, @Valid @RequestBody Request request) {
        Response response = libroService.modify(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompleteResponse> findLibroById(@PathVariable Long id) {
        CompleteResponse response = libroService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LibroResponsePrj>> findAllLibri() {
        List<LibroResponsePrj> response = libroService.findAll();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable Long id) {
        String message = libroService.delete(id);
        return ResponseEntity.ok(message);
    }


}
