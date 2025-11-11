package br.edu.library.controller;

import br.edu.library.model.Loan;
import br.edu.library.model.dto.LoanDTO;
import br.edu.library.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanControllerTest {

    private LoanService loanService;
    private LoanController loanController;

    @BeforeEach
    void setUp() {
        loanService = mock(LoanService.class);
        loanController = new LoanController(loanService);
    }

    @Test
    void testCreateLoanSuccess() {
        LoanDTO dto = new LoanDTO(1L, 101L); // usuário 1, livro 101
        Loan expectedLoan = new Loan();
        expectedLoan.setId(10L);
        expectedLoan.setUserId(1L);
        expectedLoan.setBookId(101L);

        when(loanService.doLoan(dto)).thenReturn(expectedLoan);

        ResponseEntity<Loan> response = loanController.createLoan(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(10L, response.getBody().getId());
        assertEquals(1L, response.getBody().getUserId());
        assertEquals(101L, response.getBody().getBookId());
        verify(loanService, times(1)).doLoan(dto);
    }

    @Test
    void testCreateLoanWithInvalidArguments() {
        LoanDTO dto = new LoanDTO(null, null); // dados inválidos

        when(loanService.doLoan(dto)).thenThrow(new IllegalArgumentException("Dados inválidos"));

        ResponseEntity<Loan> response = loanController.createLoan(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertNull(response.getBody());
        verify(loanService, times(1)).doLoan(dto);
    }

    @Test
    void testCreateLoanWithUnexpectedError() {
        LoanDTO dto = new LoanDTO(2L, 202L); // dados válidos

        when(loanService.doLoan(dto)).thenThrow(new RuntimeException("Erro inesperado"));

        ResponseEntity<Loan> response = loanController.createLoan(dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        assertNull(response.getBody());
        verify(loanService, times(1)).doLoan(dto);
    }
}