package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.dto.MantenimientoCreateDTO;
import com.mantenimiento.AutoAlDiaBackend.dto.MantenimientoResponseDTO;
import com.mantenimiento.AutoAlDiaBackend.model.Mantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.Vehiculo;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoServicio;
import com.mantenimiento.AutoAlDiaBackend.repository.MantenimientoRepository;
import com.mantenimiento.AutoAlDiaBackend.repository.VehiculoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.interfaz.MantenimientoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MantenimientoService implements MantenimientoServiceInterface {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public Mantenimiento crear(Mantenimiento registro) {
        return mantenimientoRepository.save(registro);
    }

    @Override
    public Optional<Mantenimiento> obtenerPorId(Long id) {
        return mantenimientoRepository.findById(id);
    }

    @Override
    public List<Mantenimiento> obtenerTodos() {
        return mantenimientoRepository.findAll();
    }

    @Override
    public Mantenimiento actualizar(Long id, Mantenimiento registro) {
        if (!mantenimientoRepository.existsById(id)) {
            throw new RuntimeException("Registro no encontrado con ID: " + id);
        }
        registro.setId(id);
        return mantenimientoRepository.save(registro);
    }

    @Override
    public void eliminar(Long id) {
        if (!mantenimientoRepository.existsById(id)) {
            throw new RuntimeException("Registro no encontrado con ID: " + id);
        }
        mantenimientoRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return mantenimientoRepository.existsById(id);
    }

    // Métodos personalizados

    @Override
    public List<Mantenimiento> obtenerPorVehiculoId(Long vehiculoId) {
        return mantenimientoRepository.findByVehiculoId(vehiculoId);
    }

    @Override
    public List<Mantenimiento> obtenerPorVehiculoIdOrdenadoPorFecha(Long vehiculoId) {
        return mantenimientoRepository.findByVehiculoIdOrderByFecha_mantenimientoDesc(vehiculoId);
    }

    @Override
    public List<Mantenimiento> obtenerPorVehiculoIdYTipoServicio(Long vehiculoId, TipoServicio tipoServicio) {
        return mantenimientoRepository.findByVehiculoIdAndTipoServicio(vehiculoId, tipoServicio);
    }

    @Override
    public List<Mantenimiento> obtenerEntreFechas(Long vehiculoId, LocalDate fechaInicio, LocalDate fechaFin) {
        return mantenimientoRepository.findByVehiculoIdAndFecha_mantenimientoBetween(vehiculoId, fechaInicio, fechaFin);
    }

    @Override
    public BigDecimal calcularCostoTotalPorVehiculo(Long vehiculoId) {
        return mantenimientoRepository.calcularCostoTotalPorVehiculo(vehiculoId);
    }

    @Override
    public BigDecimal calcularCostoEnRango(Long vehiculoId, LocalDate fechaInicio, LocalDate fechaFin) {
        return mantenimientoRepository.calcularCostoTotalEnRango(vehiculoId, fechaInicio, fechaFin);
    }

    @Override
    public Mantenimiento obtenerUltimoMantenimientoPorTipo(Long vehiculoId, TipoServicio tipoServicio) {
        List<Mantenimiento> resultados = mantenimientoRepository.findFirstByVehiculoIdAndTipoServicioOrderByFecha_mantenimientoDesc(vehiculoId, tipoServicio);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public long contarPorVehiculoId(Long vehiculoId) {
        return mantenimientoRepository.countByVehiculoId(vehiculoId);
    }

    // Métodos DTO
    public MantenimientoResponseDTO crearDesdeDTO(MantenimientoCreateDTO dto) {
        Vehiculo vehiculo = vehiculoRepository.findById(dto.getVehiculoId())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + dto.getVehiculoId()));

        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setVehiculo(vehiculo);
        mantenimiento.setTipoServicio(dto.getTipoServicio());
        mantenimiento.setDescripcion(dto.getDescripcion());
        mantenimiento.setFecha_mantenimiento(dto.getFecha_mantenimiento());
        mantenimiento.setKm_al_servicio(dto.getKm_al_servicio());
        mantenimiento.setCosto(dto.getCosto());
        mantenimiento.setLugar(dto.getLugar());
        mantenimiento.setUrl_foto_recibo(dto.getUrl_foto_recibo());

        Mantenimiento mantenimientoGuardado = mantenimientoRepository.save(mantenimiento);
        return convertirAResponseDTO(mantenimientoGuardado);
    }

    public MantenimientoResponseDTO convertirAResponseDTO(Mantenimiento mantenimiento) {
        MantenimientoResponseDTO dto = new MantenimientoResponseDTO();
        dto.setId(mantenimiento.getId());
        dto.setVehiculoId(mantenimiento.getVehiculo().getId());
        dto.setTipoServicio(mantenimiento.getTipoServicio());
        dto.setDescripcion(mantenimiento.getDescripcion());
        dto.setFecha_mantenimiento(mantenimiento.getFecha_mantenimiento());
        dto.setKm_al_servicio(mantenimiento.getKm_al_servicio());
        dto.setCosto(mantenimiento.getCosto());
        dto.setLugar(mantenimiento.getLugar());
        dto.setUrl_foto_recibo(mantenimiento.getUrl_foto_recibo());
        dto.setCreatedAt(mantenimiento.getCreatedAt());
        dto.setUpdatedAt(mantenimiento.getUpdatedAt());
        return dto;
    }

    public List<MantenimientoResponseDTO> convertirListaAResponseDTO(List<Mantenimiento> mantenimientos) {
        return mantenimientos.stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

}