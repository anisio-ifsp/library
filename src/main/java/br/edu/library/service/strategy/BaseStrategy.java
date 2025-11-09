package br.edu.library.service.strategy;

import br.edu.library.model.Book;
import br.edu.library.model.Loan;
import br.edu.library.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public abstract class BaseStrategy<T extends User> implements LoanStrategy{
    private int bookMaxQuantity;

    private T entity;
    public BaseStrategy(T entity) {
        this.entity = entity;
    }
}
