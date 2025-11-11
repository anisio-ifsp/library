package br.edu.library.service;

import br.edu.library.model.Book;
import br.edu.library.model.Loan;
import br.edu.library.model.User;
import br.edu.library.model.dto.LoanDTO;
import br.edu.library.repository.BookRepository;
import br.edu.library.repository.LoanRepository;
import br.edu.library.repository.UserRepository;
import br.edu.library.service.strategy.LoanStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    private UserRepository userRepository;
    private BookRepository bookRepository;
    private LoanRepository loanRepository;
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        bookRepository = mock(BookRepository.class);
        loanRepository = mock(LoanRepository.class);
        loanService = new LoanService(userRepository, bookRepository, loanRepository);
    }

    @Test
    void testDoLoanSuccess() {
        LoanDTO dto = new LoanDTO(1L, 101L);

        Book book = new Book();
        book.setId(101L);

        User user = mock(User.class);
        LoanStrategy strategy = mock(LoanStrategy.class);
        Loan loan = new Loan(10L, LocalDate.now(), LocalDate.now().plusDays(7), 101L, 1L);

        when(bookRepository.findById(101L)).thenReturn(Optional.of(book));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(user.getLoanStrategy()).thenReturn(strategy);
        when(strategy.getLoan(book)).thenReturn(loan);

        Loan result = loanService.doLoan(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals(101L, result.getBookId());
        assertEquals(1L, result.getUserId());
        verify(loanRepository).save(loan);
    }

    @Test
    void testDoLoanBookNotFound() {
        LoanDTO dto = new LoanDTO(1L, 999L);

        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> loanService.doLoan(dto));
        assertEquals("Book not found with id: 999", exception.getMessage());
    }

    @Test
    void testDoLoanUserNotFound() {
        LoanDTO dto = new LoanDTO(2L, 101L);

        Book book = new Book();
        book.setId(101L);

        when(bookRepository.findById(101L)).thenReturn(Optional.of(book));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> loanService.doLoan(dto));
        assertEquals("User not found with id: 2", exception.getMessage());
    }
}