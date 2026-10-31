package org.example.service;

import org.example.dto.CourseResponseDTO;
import org.example.dto.StudentRequestDTO;
import org.example.dto.StudentResponseDTO;
import org.example.entity.Enrollment;
import org.example.entity.Student;
import org.example.repository.EnrollmentRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository; // Inyección necesaria

    public List<StudentResponseDTO> listarEstudiantes() {
        return studentRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO crearEstudiante(StudentRequestDTO requestDTO) {
        Student student = Student.builder()
                .nombre(requestDTO.getNombre())
                .email(requestDTO.getEmail())
                .build();
        Student guardado = studentRepository.save(student);
        return convertirADTO(guardado);
    }

    @Transactional(readOnly = true)
    public List<CourseResponseDTO> listarCursosPorEstudiante(Long studentId) {
        List<Enrollment> inscripciones = enrollmentRepository.findByStudentId(studentId);
        return inscripciones.stream()
                .map(e -> CourseResponseDTO.builder()
                        .id(e.getCourse().getId())
                        .titulo(e.getCourse().getTitulo())
                        .descripcion(e.getCourse().getDescripcion())
                        .nombreInstructor(e.getCourse().getInstructor() != null ? e.getCourse().getInstructor().getNombre() : "Sin Instructor")
                        .build())
                .collect(Collectors.toList());
    }

    private StudentResponseDTO convertirADTO(Student student) {
        return StudentResponseDTO.builder()
                .id(student.getId())
                .nombre(student.getNombre())
                .email(student.getEmail())
                .build();
    }
}