package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.EnrollmentRequestDTO;
import org.example.entity.Enrollment;
import org.example.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Enrollment> inscribirEstudiante(@Valid @RequestBody EnrollmentRequestDTO requestDTO) {
        Enrollment nuevaInscripcion = enrollmentService.inscribirEstudiante(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInscripcion);
    }
}