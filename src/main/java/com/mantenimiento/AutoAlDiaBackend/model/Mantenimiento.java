package com.mantenimiento.AutoAlDiaBackend.model;

import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoServicio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "mantenimiento")
public class Mantenimiento extends BaseEntity{


    // Relacion 1 a muchos con VEHICULO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servicio", nullable = false, length = 50)
    private TipoServicio tipoServicio;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha_mantenimiento;

    @Column(name = "km_al_servicio", nullable = false)
    private Integer km_al_servicio;

    @Column(precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(length = 200)
    private String lugar;

    @Column(name = "url_foto_recibo", length = 500)
    private String url_foto_recibo;

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_mantenimiento() {
        return fecha_mantenimiento;
    }

    public void setFecha_mantenimiento(LocalDate fecha_mantenimiento) {
        this.fecha_mantenimiento = fecha_mantenimiento;
    }

    public Integer getKm_al_servicio() {
        return km_al_servicio;
    }

    public void setKm_al_servicio(Integer km_al_servicio) {
        this.km_al_servicio = km_al_servicio;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getUrl_foto_recibo() {
        return url_foto_recibo;
    }

    public void setUrl_foto_recibo(String url_foto_recibo) {
        this.url_foto_recibo = url_foto_recibo;
    }
}
