package org.example.service;

import org.example.dto.EnrollmentRequestDTO;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.Student;
import org.example.repository.CourseRepository;
import org.example.repository.EnrollmentRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Enrollment inscribirEstudiante(EnrollmentRequestDTO requestDTO) {
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .fechaInscripcion(LocalDate.now())
                .build();

        return enrollmentRepository.save(enrollment);
    }
}