package br.edu.library.model;

import br.edu.library.service.strategy.LoanStrategy;
import br.edu.library.service.strategy.TechnicianLoanStrategy;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("tecnico")

public class Technician extends User {

    @Transient
    @Override
    public LoanStrategy getLoanStrategy() {
        return new TechnicianLoanStrategy(this);
    }
}
