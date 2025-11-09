package br.edu.library.service.strategy;

import br.edu.library.model.Book;
import br.edu.library.model.Loan;

import java.time.LocalDate;

public interface LoanStrategy{
    public Loan getLoan(Book book);
    public LocalDate getLoanDueDate(Book book);
}
