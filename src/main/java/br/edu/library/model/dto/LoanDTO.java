package br.edu.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoanDTO {
    private Long userId;
    private Long bookId;

}
