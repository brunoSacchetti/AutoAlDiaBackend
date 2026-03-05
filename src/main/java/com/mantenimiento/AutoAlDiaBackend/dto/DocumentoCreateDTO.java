package com.mantenimiento.AutoAlDiaBackend.dto;

import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentoCreateDTO {

    private Long vehiculoId;
    private TipoDocumento tipoDocumento;
    private LocalDate fecha_expiro;
    private String url_foto;
    private String notas;
}
