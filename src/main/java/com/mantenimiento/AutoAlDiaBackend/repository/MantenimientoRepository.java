package com.mantenimiento.AutoAlDiaBackend.repository;

import com.mantenimiento.AutoAlDiaBackend.model.Mantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {



    // Buscar todos los registros de un vehículo
    List<Mantenimiento> findByVehiculoId(Long vehiculoId);


    // Buscar registros de un vehículo ordenados por fecha descendente (más reciente primero)
    @Query("SELECT m FROM Mantenimiento m WHERE m.vehiculo.id = :vehiculoId ORDER BY m.fecha_mantenimiento DESC")
    List<Mantenimiento> findByVehiculoIdOrderByFecha_mantenimientoDesc(@Param("vehiculoId") Long vehiculoId);

    // Buscar por vehículo y tipo de servicio
    List<Mantenimiento> findByVehiculoIdAndTipoServicio(Long vehiculoId, TipoServicio tipoServicio);

    // Buscar registros entre fechas
    @Query("SELECT m FROM Mantenimiento m WHERE m.vehiculo.id = :vehiculoId AND m.fecha_mantenimiento BETWEEN :fechaInicio AND :fechaFin")
    List<Mantenimiento> findByVehiculoIdAndFecha_mantenimientoBetween(@Param("vehiculoId") Long vehiculoId, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    // Calcular costo total por vehículo (consulta JPQL personalizada)
    @Query("SELECT COALESCE(SUM(m.costo), 0) FROM Mantenimiento m WHERE m.vehiculo.id = :vehiculoId")
    BigDecimal calcularCostoTotalPorVehiculo(@Param("vehiculoId") Long vehiculoId);

    // Calcular costo total por vehículo en un rango de fechas
    @Query("SELECT COALESCE(SUM(m.costo),0) FROM Mantenimiento m WHERE m.vehiculo.id = :vehiculoId AND m.fecha_mantenimiento BETWEEN :fechaInicio AND :fechaFin")
    BigDecimal calcularCostoTotalEnRango(@Param("vehiculoId") Long vehiculoId, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    // Obtener último mantenimiento de un tipo específico
    @Query("SELECT m FROM Mantenimiento m WHERE m.vehiculo.id = :vehiculoId AND m.tipoServicio = :tipoServicio ORDER BY m.fecha_mantenimiento DESC")
    List<Mantenimiento> findFirstByVehiculoIdAndTipoServicioOrderByFecha_mantenimientoDesc(@Param("vehiculoId") Long vehiculoId, @Param("tipoServicio") TipoServicio tipoServicio);

    // Contar mantenimientos por vehículo
    long countByVehiculoId(Long vehiculoId);

}