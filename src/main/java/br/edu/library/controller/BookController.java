package br.edu.library.controller;

import br.edu.library.model.Book;
import br.edu.library.repository.BookRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book", description = "Gerenciamento de Livros")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Book> createLoan(@RequestBody Book book) {
        try {
            var bookSave = bookRepository.save(new Book(null, book.getTitle()));
            return ResponseEntity.ok(bookSave);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
