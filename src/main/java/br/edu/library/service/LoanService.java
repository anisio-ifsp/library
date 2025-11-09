package br.edu.library.service;

import br.edu.library.model.Loan;
import br.edu.library.model.dto.LoanDTO;
import br.edu.library.repository.BookRepository;
import br.edu.library.repository.LoanRepository;
import br.edu.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public Loan doLoan(LoanDTO dto) {
        var bookOpt = bookRepository.findById(dto.getBookId());
        var userOpt = userRepository.findById(dto.getUserId());
        if (bookOpt.isEmpty()) {
            throw new IllegalArgumentException("Book not found with id: " + dto.getBookId());
        }
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found with id: " + dto.getUserId());
        }

        var user = userOpt.get().getLoanStrategy();
        System.out.println("Instancia de USER: " + user.getClass().getSimpleName());
        var strategy = userOpt.get().getLoanStrategy();
        var loan = strategy.getLoan(bookOpt.get());

        loanRepository.save(loan);

        return loan;
    }

}
