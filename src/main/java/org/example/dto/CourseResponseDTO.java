package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String nombreInstructor;
}