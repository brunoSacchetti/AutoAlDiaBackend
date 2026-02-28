package com.mantenimiento.AutoAlDiaBackend.service.interfaz;

import com.mantenimiento.AutoAlDiaBackend.model.Mantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MantenimientoServiceInterface {

    // CRUD Básico
    Mantenimiento crear(Mantenimiento registro);
    Optional<Mantenimiento> obtenerPorId(Long id);
    List<Mantenimiento> obtenerTodos();
    Mantenimiento actualizar(Long id, Mantenimiento registro);
    void eliminar(Long id);
    boolean existe(Long id);

    // Métodos personalizados
    List<Mantenimiento> obtenerPorVehiculoId(Long vehiculoId);
    List<Mantenimiento> obtenerPorVehiculoIdOrdenadoPorFecha(Long vehiculoId);
    List<Mantenimiento> obtenerPorVehiculoIdYTipoServicio(Long vehiculoId, TipoServicio tipoServicio);
    List<Mantenimiento> obtenerEntreFechas(Long vehiculoId, LocalDate fechaInicio, LocalDate fechaFin);
    BigDecimal calcularCostoTotalPorVehiculo(Long vehiculoId);
    BigDecimal calcularCostoEnRango(Long vehiculoId, LocalDate fechaInicio, LocalDate fechaFin);
    Mantenimiento obtenerUltimoMantenimientoPorTipo(Long vehiculoId, TipoServicio tipoServicio);
    long contarPorVehiculoId(Long vehiculoId);

}
