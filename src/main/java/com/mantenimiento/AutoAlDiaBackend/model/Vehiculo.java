package com.mantenimiento.AutoAlDiaBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vehiculo")
public class Vehiculo extends BaseEntity{

    // Relacion muchos a 1 con USUARIO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false)
    private Integer anio;

    @Column(name = "patente", unique = true, length = 20)
    private String patente;

    @Column(name = "km_actual", nullable = false)
    private Integer km_actual;

    @Column(name = "url_foto", length = 500)
    private String url_foto;

    // Relacion 1 a mychos con MANTENIMIENTO
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Mantenimiento> mantenimiento = new ArrayList<>();

    // Relacion 1 a mychos con RECORDATORIO MANTENIMIENTO
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RecordatorioMantenimiento> recordatorioMantenimiento = new ArrayList<>();

    // Relacion 1 a mychos con DOCUMENTO
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Documento> documento = new ArrayList<>();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Integer getKm_actual() {
        return km_actual;
    }

    public void setKm_actual(Integer km_actual) {
        this.km_actual = km_actual;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public List<Mantenimiento> getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(List<Mantenimiento> mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public List<RecordatorioMantenimiento> getRecordatorioMantenimiento() {
        return recordatorioMantenimiento;
    }

    public void setRecordatorioMantenimiento(List<RecordatorioMantenimiento> recordatorioMantenimiento) {
        this.recordatorioMantenimiento = recordatorioMantenimiento;
    }

    public List<Documento> getDocumento() {
        return documento;
    }

    public void setDocumento(List<Documento> documento) {
        this.documento = documento;
    }
}
