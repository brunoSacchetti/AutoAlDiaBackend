package com.mantenimiento.AutoAlDiaBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehiculoCreateDTO {

    private Long usuarioId;
    private String marca;
    private String modelo;
    private Integer anio;
    private String patente;
    private Integer km_actual;
    private String url_foto;
}
