package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseRequestDTO {

    @NotBlank(message = "El título del curso es obligatorio")
    private String titulo;

    private String descripcion;

    @NotNull(message = "El ID del instructor es obligatorio")
    private Long instructorId;
}
