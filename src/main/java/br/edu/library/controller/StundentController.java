package br.edu.library.controller;

import br.edu.library.model.Student;
import br.edu.library.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@Tag(name = "Student", description = "Gerenciamento de Estudantes")
@RequiredArgsConstructor
public class StundentController {
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        try {
            var studentSave = userRepository.save(student);
            return ResponseEntity.ok(studentSave);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
