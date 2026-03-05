package com.mantenimiento.AutoAlDiaBackend.dto;

import com.mantenimiento.AutoAlDiaBackend.model.enums.EstadoRecordatorio;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoRecordatorio;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoServicio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecordatorioMantenimientoCreateDTO {

    private Long vehiculoId;
    private TipoRecordatorio tipoRecordatorio;
    private TipoServicio tipoServicio;
    private Integer recordatorio_km;
    private LocalDate recordatorio_fecha;
    private EstadoRecordatorio estado;
}
