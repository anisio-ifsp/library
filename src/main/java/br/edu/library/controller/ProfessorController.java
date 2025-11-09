package br.edu.library.controller;

import br.edu.library.model.Professor;
import br.edu.library.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/professor")
@Tag(name = "Professor", description = "Gerenciamento de Professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        try {
            var professorSave = userRepository.save(professor);
            return ResponseEntity.ok(professorSave);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }


}
