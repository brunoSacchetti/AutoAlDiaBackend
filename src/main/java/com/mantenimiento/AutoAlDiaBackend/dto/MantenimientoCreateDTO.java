package com.mantenimiento.AutoAlDiaBackend.dto;

import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoServicio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MantenimientoCreateDTO {

    private Long vehiculoId;
    private TipoServicio tipoServicio;
    private String descripcion;
    private LocalDate fecha_mantenimiento;
    private Integer km_al_servicio;
    private BigDecimal costo;
    private String lugar;
    private String url_foto_recibo;
}
