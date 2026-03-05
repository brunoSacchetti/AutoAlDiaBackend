package com.mantenimiento.AutoAlDiaBackend.dto;

import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentoResponseDTO {

    private Long id;
    private Long vehiculoId;
    private TipoDocumento tipoDocumento;
    private LocalDate fecha_expiro;
    private String url_foto;
    private String notas;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
