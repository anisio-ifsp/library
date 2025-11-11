package br.edu.library.controller;

import br.edu.library.model.Book;
import br.edu.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class BookControllerTest {

    private BookRepository bookRepository;
    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookController = new BookController(bookRepository);
    }

    @Test
    void testCreateLoanSuccess() {
        Book inputBook = new Book(null, "Dom Casmurro");
        Book savedBook = new Book(1L, "Dom Casmurro");

        Mockito.when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        ResponseEntity<Book> response = bookController.createLoan(inputBook);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Dom Casmurro", response.getBody().getTitle());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testCreateLoanIllegalArgument() {
        Book inputBook = new Book(null, null);

        Mockito.when(bookRepository.save(any(Book.class)))
                .thenThrow(new IllegalArgumentException("Título inválido"));

        ResponseEntity<Book> response = bookController.createLoan(inputBook);

        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testCreateLoanUnexpectedException() {
        Book inputBook = new Book(null, "Memórias Póstumas");

        Mockito.when(bookRepository.save(any(Book.class)))
                .thenThrow(new RuntimeException("Erro inesperado"));

        ResponseEntity<Book> response = bookController.createLoan(inputBook);

        assertEquals(500, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
