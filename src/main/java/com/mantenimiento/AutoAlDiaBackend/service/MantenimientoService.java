package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.model.Mantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoServicio;
import com.mantenimiento.AutoAlDiaBackend.repository.MantenimientoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.interfaz.MantenimientoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MantenimientoService implements MantenimientoServiceInterface {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

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
        //return mantenimientoRepository.findByVehiculoId(vehiculoId);
        return null;
    }

    @Override
    public List<Mantenimiento> obtenerPorVehiculoIdOrdenadoPorFecha(Long vehiculoId) {
        //return mantenimientoRepository.findByVehiculoIdOrderByFechaDesc(vehiculoId);
        return null;
    }

    @Override
    public List<Mantenimiento> obtenerPorVehiculoIdYTipoServicio(Long vehiculoId, TipoServicio tipoServicio) {
        //return mantenimientoRepository.findByVehiculoIdAndTipoServicio(vehiculoId, tipoServicio);
        return null;
    }

    @Override
    public List<Mantenimiento> obtenerEntreFechas(Long vehiculoId, LocalDate fechaInicio, LocalDate fechaFin) {
        //return mantenimientoRepository.findByVehiculoIdAndFechaBetween(vehiculoId, fechaInicio, fechaFin);
        return null;
    }

    @Override
    public BigDecimal calcularCostoTotalPorVehiculo(Long vehiculoId) {
        //return mantenimientoRepository.calcularCostoTotalPorVehiculo(vehiculoId);
        return null;
    }

    @Override
    public BigDecimal calcularCostoEnRango(Long vehiculoId, LocalDate fechaInicio, LocalDate fechaFin) {
        //return mantenimientoRepository.calcularCostoTotalEnRango(vehiculoId, fechaInicio, fechaFin);
        return null;
    }

    @Override
    public Mantenimiento obtenerUltimoMantenimientoPorTipo(Long vehiculoId, TipoServicio tipoServicio) {
        //return mantenimientoRepository.findFirstByVehiculoIdAndTipoServicioOrderByFechaDesc(vehiculoId, tipoServicio);
        return null;
    }

    @Override
    public long contarPorVehiculoId(Long vehiculoId) {
        //return mantenimientoRepository.countByVehiculoId(vehiculoId);
        return 1;
    }


}
