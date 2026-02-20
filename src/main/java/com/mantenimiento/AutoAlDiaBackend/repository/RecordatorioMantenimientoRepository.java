package com.mantenimiento.AutoAlDiaBackend.repository;

import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.model.RecordatorioMantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.EstadoRecordatorio;
import com.mantenimiento.AutoAlDiaBackend.repository.Base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordatorioMantenimientoRepository extends BaseRepository<RecordatorioMantenimiento, Long> {
    /*
    // Buscar todos los recordatorios de un vehiculo
    List<RecordatorioMantenimiento> findByVehiculoId(Long vehiculoId);

    // Buscar recordatorios pendientes de un vehículo filtrados por estado
    List<RecordatorioMantenimiento> findByVehiculoIdAndEstado(Long vehiculoId, EstadoRecordatorio estadoRecordatorio);

    // Buscar recordatorios que deben activarse por kilometraje
    @Query("SELECT r FROM RecordatorioMantenimiento r WHERE r.vehiculo.id = :vehiculoId AND r.estado = 'PENDIENTE' AND (r.tipoRecordatorio = 'BASADO_EN_KM' OR r.tipoRecordatorio = 'AMBOS') AND r.recordatorio_km <= :km_actual")
    List<RecordatorioMantenimiento> findRecordatoriosPorKm(
            @Param("vehiculoId") Long vehiculoId,
            @Param("km_actual") Integer kmActual
    );

    // Buscar recordatorios que deben activarse por fecha
    @Query("SELECT r FROM RecordatorioMantenimiento r WHERE r.vehiculo.id = :vehiculoId AND r.estado = 'PENDIENTE' AND (r.tipoRecordatorio = 'BASADO_EN_FECHA' OR r.tipoRecordatorio = 'AMBOS') AND r.recordatorio_fecha <= :fechaActual")
    List<RecordatorioMantenimiento> findRecordatoriosPorFecha(
            @Param("vehiculoId") Long vehiculoId,
            @Param("fechaActual") LocalDate fechaActual
    );

    // Buscar todos los recordatorios activos (pendientes) de un usuario
    @Query("SELECT r FROM RecordatorioMantenimiento r WHERE r.vehiculo.usuario.id = :usuarioId AND r.estado = 'PENDIENTE'")
    List<RecordatorioMantenimiento> findRecordatoriosActivosPorUsuario(@Param("usuarioId") Long usuarioId); */





}
