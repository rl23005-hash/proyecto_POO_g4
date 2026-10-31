package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponseDTO {
    private Long id;
    private String nombre;
    private String email;
}
