package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.model.RecordatorioMantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.EstadoRecordatorio;
import com.mantenimiento.AutoAlDiaBackend.repository.RecordatorioMantenimientoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.interfaz.RecordatorioMantenimientoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RecordatorioMantenimientoService implements RecordatorioMantenimientoServiceInterface {

    @Autowired
    private RecordatorioMantenimientoRepository recordatorioRepository;

    @Override
    public RecordatorioMantenimiento crear(RecordatorioMantenimiento recordatorio) {
        return recordatorioRepository.save(recordatorio);
    }

    @Override
    public Optional<RecordatorioMantenimiento> obtenerPorId(Long id) {
        return recordatorioRepository.findById(id);
    }

    @Override
    public List<RecordatorioMantenimiento> obtenerTodos() {
        return recordatorioRepository.findAll();
    }

    @Override
    public RecordatorioMantenimiento actualizar(Long id, RecordatorioMantenimiento recordatorio) {
        if (!recordatorioRepository.existsById(id)) {
            throw new RuntimeException("Recordatorio no encontrado con ID: " + id);
        }
        recordatorio.setId(id);
        return recordatorioRepository.save(recordatorio);
    }

    @Override
    public void eliminar(Long id) {
        if (!recordatorioRepository.existsById(id)) {
            throw new RuntimeException("Recordatorio no encontrado con ID: " + id);
        }
        recordatorioRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return recordatorioRepository.existsById(id);
    }

    // Métodos personalizados
    @Override
    public List<RecordatorioMantenimiento> obtenerPorVehiculoId(Long vehiculoId) {
        //return recordatorioRepository.findByVehiculoId(vehiculoId);
        return null;
    }

    @Override
    public List<RecordatorioMantenimiento> obtenerPorVehiculoIdYEstado(Long vehiculoId, EstadoRecordatorio estado) {
        //return recordatorioRepository.findByVehiculoIdAndEstado(vehiculoId, estado);
        return null;
    }

    @Override
    public List<RecordatorioMantenimiento> verificarRecordatoriosPorKm(Long vehiculoId, Integer kmActual) {
        //return recordatorioRepository.findRecordatoriosPorKm(vehiculoId, kmActual);
        return null;
    }

    @Override
    public List<RecordatorioMantenimiento> verificarRecordatoriosPorFecha(Long vehiculoId, LocalDate fechaActual) {
        //return recordatorioRepository.findRecordatoriosPorFecha(vehiculoId, fechaActual);
        return null;
    }

    @Override
    public List<RecordatorioMantenimiento> obtenerRecordatoriosActivosPorUsuario(Long usuarioId) {
        //return recordatorioRepository.findRecordatoriosActivosPorUsuario(usuarioId);
        return null;
    }
}
