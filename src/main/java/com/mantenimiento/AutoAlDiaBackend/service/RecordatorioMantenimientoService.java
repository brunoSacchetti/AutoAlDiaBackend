package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.dto.RecordatorioMantenimientoCreateDTO;
import com.mantenimiento.AutoAlDiaBackend.dto.RecordatorioMantenimientoResponseDTO;
import com.mantenimiento.AutoAlDiaBackend.model.RecordatorioMantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.Vehiculo;
import com.mantenimiento.AutoAlDiaBackend.model.enums.EstadoRecordatorio;
import com.mantenimiento.AutoAlDiaBackend.repository.RecordatorioMantenimientoRepository;
import com.mantenimiento.AutoAlDiaBackend.repository.VehiculoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.interfaz.RecordatorioMantenimientoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordatorioMantenimientoService implements RecordatorioMantenimientoServiceInterface {

    @Autowired
    private RecordatorioMantenimientoRepository recordatorioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

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

    // Métodos DTO
    public RecordatorioMantenimientoResponseDTO crearDesdeDTO(RecordatorioMantenimientoCreateDTO dto) {
        Vehiculo vehiculo = vehiculoRepository.findById(dto.getVehiculoId())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + dto.getVehiculoId()));

        RecordatorioMantenimiento recordatorio = new RecordatorioMantenimiento();
        recordatorio.setVehiculo(vehiculo);
        recordatorio.setTipoRecordatorio(dto.getTipoRecordatorio());
        recordatorio.setTipoServicio(dto.getTipoServicio());
        recordatorio.setRecordatorio_km(dto.getRecordatorio_km());
        recordatorio.setRecordatorio_fecha(dto.getRecordatorio_fecha());
        recordatorio.setEstado(dto.getEstado());

        RecordatorioMantenimiento recordatorioGuardado = recordatorioRepository.save(recordatorio);
        return convertirAResponseDTO(recordatorioGuardado);
    }

    public RecordatorioMantenimientoResponseDTO convertirAResponseDTO(RecordatorioMantenimiento recordatorio) {
        RecordatorioMantenimientoResponseDTO dto = new RecordatorioMantenimientoResponseDTO();
        dto.setId(recordatorio.getId());
        dto.setVehiculoId(recordatorio.getVehiculo().getId());
        dto.setTipoRecordatorio(recordatorio.getTipoRecordatorio());
        dto.setTipoServicio(recordatorio.getTipoServicio());
        dto.setRecordatorio_km(recordatorio.getRecordatorio_km());
        dto.setRecordatorio_fecha(recordatorio.getRecordatorio_fecha());
        dto.setEstado(recordatorio.getEstado());
        dto.setCreatedAt(recordatorio.getCreatedAt());
        dto.setUpdatedAt(recordatorio.getUpdatedAt());
        return dto;
    }

    public List<RecordatorioMantenimientoResponseDTO> convertirListaAResponseDTO(List<RecordatorioMantenimiento> recordatorios) {
        return recordatorios.stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }
}
