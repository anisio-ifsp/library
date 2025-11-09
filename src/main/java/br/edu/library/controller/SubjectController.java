package br.edu.library.controller;

import br.edu.library.model.Subject;
import br.edu.library.repository.SubjectRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
@Tag(name = "Subject", description = "Gerenciamento de Disciplinas")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectRepository subjectRepository;

    @PostMapping
    public ResponseEntity<Subject> create(@RequestBody Subject sub) {
        try {
            var subSave = subjectRepository.save(sub);
            return ResponseEntity.ok(subSave);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
