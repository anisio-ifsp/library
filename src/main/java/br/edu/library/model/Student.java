package br.edu.library.model;

import br.edu.library.service.strategy.LoanStrategy;
import br.edu.library.service.strategy.StudentyLoanStrategy;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;

@Entity
@DiscriminatorValue("discente")
@Getter@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {
    private String studentNumber;

    @Transient
    @Override
    public LoanStrategy getLoanStrategy() {
        return new StudentyLoanStrategy(this);
    }
}
