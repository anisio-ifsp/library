package br.edu.library.service.strategy;

import br.edu.library.model.Book;
import br.edu.library.model.Loan;
import br.edu.library.model.Technician;

import java.time.LocalDate;

public class TechnicianLoanStrategy extends BaseStrategy<Technician> {
    public TechnicianLoanStrategy(Technician entity) {
        super(entity);
    }

    @Override
    public Loan getLoan(Book book) {
        var loan = new Loan();
        loan.setBookId(book.getId());
        loan.setDueDate(LocalDate.now().plusDays(1));
        loan.setLoanDate(LocalDate.now());
        loan.setUserId(getEntity().getId());
        return loan;
    }

    @Override
    public LocalDate getLoanDueDate(Book book) {
        return null;
    }
}
