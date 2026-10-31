package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.StudentRequestDTO;
import org.example.dto.StudentResponseDTO;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> listarEstudiantes() {
        return ResponseEntity.ok(studentService.listarEstudiantes());
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> crearEstudiante(@Valid @RequestBody StudentRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.crearEstudiante(requestDTO));
    }
    // GET: Listar cursos en los que está inscrito un estudiante
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<org.example.dto.CourseResponseDTO>> listarCursosDeEstudiante(@PathVariable Long id) {
        List<org.example.dto.CourseResponseDTO> cursos = studentService.listarCursosPorEstudiante(id);
        return ResponseEntity.ok(cursos);
    }
}
