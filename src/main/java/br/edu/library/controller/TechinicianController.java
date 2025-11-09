package br.edu.library.controller;

import br.edu.library.model.Technician;
import br.edu.library.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/techinician")
@Tag(name = "Techinician", description = "Gerenciamento de Técnico")
@RequiredArgsConstructor
public class TechinicianController {
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Technician> create(@RequestBody Technician tec) {
        try {
            var studentSave = userRepository.save(tec);
            return ResponseEntity.ok(studentSave);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
