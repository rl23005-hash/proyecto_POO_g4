package org.example.service;

import org.example.dto.CourseRequestDTO;
import org.example.dto.CourseResponseDTO;
import org.example.dto.StudentResponseDTO;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.Instructor;
import org.example.repository.CourseRepository;
import org.example.repository.EnrollmentRepository;
import org.example.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository; // Inyección necesaria

    // Listar todos los cursos
    public List<CourseResponseDTO> listarCursos() {
        return courseRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Obtener un curso por su ID
    public CourseResponseDTO obtenerCursoPorId(Long id) {
        Course curso = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
        return convertirADTO(curso);
    }

    // Crear un nuevo curso validando el instructor
    public CourseResponseDTO crearCurso(CourseRequestDTO requestDTO) {
        Instructor instructor = instructorRepository.findById(requestDTO.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con ID: " + requestDTO.getInstructorId()));

        Course curso = new Course();
        curso.setTitulo(requestDTO.getTitulo());
        curso.setDescripcion(requestDTO.getDescripcion());
        curso.setInstructor(instructor);

        Course cursoGuardado = courseRepository.save(curso);
        return convertirADTO(cursoGuardado);
    }

    // Actualizar un curso existente
    public CourseResponseDTO actualizarCurso(Long id, CourseRequestDTO requestDTO) {
        Course curso = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));

        Instructor instructor = instructorRepository.findById(requestDTO.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con ID: " + requestDTO.getInstructorId()));

        curso.setTitulo(requestDTO.getTitulo());
        curso.setDescripcion(requestDTO.getDescripcion());
        curso.setInstructor(instructor);

        Course cursoActualizado = courseRepository.save(curso);
        return convertirADTO(cursoActualizado);
    }

    // Eliminar un curso por ID
    public void eliminarCurso(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado con ID: " + id);
        }
        courseRepository.deleteById(id);
    }

    // Listar estudiantes inscritos en un curso específico
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> listarEstudiantesPorCurso(Long courseId) {
        List<Enrollment> inscripciones = enrollmentRepository.findByCourseId(courseId);
        return inscripciones.stream()
                .map(e -> StudentResponseDTO.builder()
                        .id(e.getStudent().getId())
                        .nombre(e.getStudent().getNombre())
                        .email(e.getStudent().getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    // Método auxiliar para convertir Entidad a Response DTO
    private CourseResponseDTO convertirADTO(Course curso) {
        return CourseResponseDTO.builder()
                .id(curso.getId())
                .titulo(curso.getTitulo())
                .descripcion(curso.getDescripcion())
                .nombreInstructor(curso.getInstructor() != null ? curso.getInstructor().getNombre() : "Sin Instructor")
                .build();
    }
}