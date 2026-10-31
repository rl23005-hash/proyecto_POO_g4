package org.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDTO {

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long studentId;

    @NotNull(message = "El ID del curso es obligatorio")
    private Long courseId;
}
