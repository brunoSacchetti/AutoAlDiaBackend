package com.mantenimiento.AutoAlDiaBackend.service.interfaz;

import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoDocumento;

import java.util.List;
import java.util.Optional;

public interface DocumentoServiceInterface {
    // CRUD Básico
    Documento crear(Documento documento);
    Optional<Documento> obtenerPorId(Long id);
    List<Documento> obtenerTodos();
    Documento actualizar(Long id, Documento documento);
    void eliminar(Long id);
    boolean existe(Long id);

    // Métodos personalizados
    List<Documento> obtenerPorVehiculoId(Long vehiculoId);
    List<Documento> obtenerPorVehiculoIdYTipo(Long vehiculoId, TipoDocumento tipo);
    List<Documento> obtenerDocumentosVencidos(Long vehiculoId);
    List<Documento> obtenerDocumentosPorVencer(Long vehiculoId, int diasAnticipacion);
    List<Documento> obtenerDocumentosVencidosPorUsuario(Long usuarioId);



}
