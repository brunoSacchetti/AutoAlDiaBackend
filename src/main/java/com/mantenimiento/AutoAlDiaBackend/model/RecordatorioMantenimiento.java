package com.mantenimiento.AutoAlDiaBackend.model;

import com.mantenimiento.AutoAlDiaBackend.model.enums.EstadoRecordatorio;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoRecordatorio;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoServicio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "recordatorio_mantenimiento")
public class RecordatorioMantenimiento extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_recordatorio", nullable = false, length = 20)
    private TipoRecordatorio tipoRecordatorio;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servicio", nullable = false, length = 50)
    private TipoServicio tipoServicio;

    @Column(name = "recordatorio_km")
    private Integer recordatorio_km;

    @Column(name = "recordatorio_fecha")
    private LocalDate recordatorio_fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoRecordatorio estado;

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TipoRecordatorio getTipoRecordatorio() {
        return tipoRecordatorio;
    }

    public void setTipoRecordatorio(TipoRecordatorio tipoRecordatorio) {
        this.tipoRecordatorio = tipoRecordatorio;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Integer getRecordatorio_km() {
        return recordatorio_km;
    }

    public void setRecordatorio_km(Integer recordatorio_km) {
        this.recordatorio_km = recordatorio_km;
    }

    public LocalDate getRecordatorio_fecha() {
        return recordatorio_fecha;
    }

    public void setRecordatorio_fecha(LocalDate recordatorio_fecha) {
        this.recordatorio_fecha = recordatorio_fecha;
    }

    public EstadoRecordatorio getEstado() {
        return estado;
    }

    public void setEstado(EstadoRecordatorio estado) {
        this.estado = estado;
    }
}
