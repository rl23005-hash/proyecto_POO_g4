package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.CourseRequestDTO;
import org.example.dto.CourseResponseDTO;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // GET: Listar todos los cursos
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> listarCursos() {
        List<CourseResponseDTO> cursos = courseService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    // GET: Obtener un curso por su ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> obtenerCursoPorId(@PathVariable Long id) {
        CourseResponseDTO curso = courseService.obtenerCursoPorId(id);
        return ResponseEntity.ok(curso);
    }

    // POST: Crear un nuevo curso
    @PostMapping
    public ResponseEntity<CourseResponseDTO> crearCurso(@Valid @RequestBody CourseRequestDTO requestDTO) {
        CourseResponseDTO nuevoCurso = courseService.crearCurso(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
    }

    // PUT: Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> actualizarCurso(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO requestDTO) {
        CourseResponseDTO cursoActualizado = courseService.actualizarCurso(id, requestDTO);
        return ResponseEntity.ok(cursoActualizado);
    }

    // DELETE: Eliminar un curso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        courseService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
    // GET: Listar estudiantes inscritos en un curso específico
    @GetMapping("/{id}/students")
    public ResponseEntity<List<org.example.dto.StudentResponseDTO>> listarEstudiantesDeCurso(@PathVariable Long id) {
        List<org.example.dto.StudentResponseDTO> estudiantes = courseService.listarEstudiantesPorCurso(id);
        return ResponseEntity.ok(estudiantes);
    }

}
