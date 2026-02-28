package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoDocumento;
import com.mantenimiento.AutoAlDiaBackend.repository.DocumentoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.interfaz.DocumentoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService implements DocumentoServiceInterface {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public Documento crear(Documento documento) {
        return documentoRepository.save(documento);
    }

    @Override
    public Optional<Documento> obtenerPorId(Long id) {
        return documentoRepository.findById(id);
    }

    @Override
    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }

    @Override
    public Documento actualizar(Long id, Documento documento) {
        if (!documentoRepository.existsById(id)) {
            throw new RuntimeException("Documento no encontrado con ID: " + id);
        }
        documento.setId(id);
        return documentoRepository.save(documento);
    }

    @Override
    public void eliminar(Long id) {
        if (!documentoRepository.existsById(id)) {
            throw new RuntimeException("Documento no encontrado con ID: " + id);
        }
        documentoRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return documentoRepository.existsById(id);
    }

    // Métodos personalizados
    @Override
    public List<Documento> obtenerPorVehiculoId(Long vehiculoId) {
        //return documentoRepository.findByVehiculoId(vehiculoId);
        return null;
    }

    @Override
    public List<Documento> obtenerPorVehiculoIdYTipo(Long vehiculoId, TipoDocumento tipo) {
        //return documentoRepository.findByVehiculoIdAndTipo(vehiculoId, tipo);
        return null;
    }

    @Override
    public List<Documento> obtenerDocumentosVencidos(Long vehiculoId) {
        //return documentoRepository.findDocumentosVencidos(vehiculoId, LocalDate.now());
        return null;
    }

    @Override
    public List<Documento> obtenerDocumentosPorVencer(Long vehiculoId, int diasAnticipacion) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.plusDays(diasAnticipacion);
        //return documentoRepository.findDocumentosPorVencer(vehiculoId, fechaActual, fechaLimite);
        return null;
    }

    @Override
    public List<Documento> obtenerDocumentosVencidosPorUsuario(Long usuarioId) {
        //return documentoRepository.findDocumentosVencidosPorUsuario(usuarioId, LocalDate.now());
        return null;
    }


}
