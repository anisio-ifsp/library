package br.edu.library.model;

import br.edu.library.service.strategy.LoanStrategy;
import br.edu.library.service.strategy.ProfessorLoanStrategy;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("docente")
@Getter
@Setter
@NoArgsConstructor
public class Professor extends User {
    @Transient
    @Override
    public LoanStrategy getLoanStrategy() {
        return new ProfessorLoanStrategy(this);
    }
}
