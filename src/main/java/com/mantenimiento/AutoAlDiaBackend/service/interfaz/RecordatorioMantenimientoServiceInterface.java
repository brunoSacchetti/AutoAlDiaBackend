package com.mantenimiento.AutoAlDiaBackend.service.interfaz;

import com.mantenimiento.AutoAlDiaBackend.model.RecordatorioMantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.EstadoRecordatorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecordatorioMantenimientoServiceInterface {
    // CRUD Básico
    RecordatorioMantenimiento crear(RecordatorioMantenimiento recordatorio);
    Optional<RecordatorioMantenimiento> obtenerPorId(Long id);
    List<RecordatorioMantenimiento> obtenerTodos();
    RecordatorioMantenimiento actualizar(Long id, RecordatorioMantenimiento recordatorio);
    void eliminar(Long id);
    boolean existe(Long id);

    // Métodos personalizados
    List<RecordatorioMantenimiento> obtenerPorVehiculoId(Long vehiculoId);
    List<RecordatorioMantenimiento> obtenerPorVehiculoIdYEstado(Long vehiculoId, EstadoRecordatorio estado);
    List<RecordatorioMantenimiento> verificarRecordatoriosPorKm(Long vehiculoId, Integer kmActual);
    List<RecordatorioMantenimiento> verificarRecordatoriosPorFecha(Long vehiculoId, LocalDate fechaActual);
    List<RecordatorioMantenimiento> obtenerRecordatoriosActivosPorUsuario(Long usuarioId);
}
