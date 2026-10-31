package org.example.repository;

import org.example.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Buscar inscripciones por ID de curso
    List<Enrollment> findByCourseId(Long courseId);

    // Buscar inscripciones por ID de estudiante
    List<Enrollment> findByStudentId(Long studentId);
}