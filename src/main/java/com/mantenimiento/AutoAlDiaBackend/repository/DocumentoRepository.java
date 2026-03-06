package com.mantenimiento.AutoAlDiaBackend.repository;

import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    // Buscar todos los documentos de un vehículo
    List<Documento> findByVehiculoId(Long vehiculoId);

    // Buscar documentos de un vehículo por tipo
    List<Documento> findByVehiculoIdAndTipoDocumento(Long vehiculoId, TipoDocumento tipoDocumento);

    // Buscar documentos vencidos de un vehículo
    @Query("SELECT d FROM Documento d WHERE d.vehiculo.id = :vehiculoId AND d.fecha_expiro < :fechaActual")
    List<Documento> findDocumentosVencidos(@Param("vehiculoId") Long vehiculoId, @Param("fechaActual") LocalDate fechaActual);

    // Buscar documentos por vencer en un vehículo
    @Query("SELECT d FROM Documento d WHERE d.vehiculo.id = :vehiculoId AND d.fecha_expiro BETWEEN :fechaActual AND :fechaLimite")
    List<Documento> findDocumentosPorVencer(@Param("vehiculoId") Long vehiculoId, @Param("fechaActual") LocalDate fechaActual, @Param("fechaLimite") LocalDate fechaLimite);

    // Buscar documentos vencidos de un usuario
    @Query("SELECT d FROM Documento d WHERE d.vehiculo.usuario.id = :usuarioId AND d.fecha_expiro < :fechaActual")
    List<Documento> findDocumentosVencidosPorUsuario(@Param("usuarioId") Long usuarioId, @Param("fechaActual") LocalDate fechaActual);
}