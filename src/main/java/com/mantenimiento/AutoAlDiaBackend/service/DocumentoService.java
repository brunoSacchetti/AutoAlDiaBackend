package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.dto.DocumentoCreateDTO;
import com.mantenimiento.AutoAlDiaBackend.dto.DocumentoResponseDTO;
import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.model.Vehiculo;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoDocumento;
import com.mantenimiento.AutoAlDiaBackend.repository.DocumentoRepository;
import com.mantenimiento.AutoAlDiaBackend.repository.VehiculoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.interfaz.DocumentoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentoService implements DocumentoServiceInterface {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

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
        return documentoRepository.findByVehiculoId(vehiculoId);
    }

    @Override
    public List<Documento> obtenerPorVehiculoIdYTipo(Long vehiculoId, TipoDocumento tipo) {
        return documentoRepository.findByVehiculoIdAndTipoDocumento(vehiculoId, tipo);
    }

    @Override
    public List<Documento> obtenerDocumentosVencidos(Long vehiculoId) {
        return documentoRepository.findDocumentosVencidos(vehiculoId, LocalDate.now());
    }

    @Override
    public List<Documento> obtenerDocumentosPorVencer(Long vehiculoId, int diasAnticipacion) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.plusDays(diasAnticipacion);
        return documentoRepository.findDocumentosPorVencer(vehiculoId, fechaActual, fechaLimite);
    }

    @Override
    public List<Documento> obtenerDocumentosVencidosPorUsuario(Long usuarioId) {
        return documentoRepository.findDocumentosVencidosPorUsuario(usuarioId, LocalDate.now());
    }

    // Métodos DTO
    public DocumentoResponseDTO crearDesdeDTO(DocumentoCreateDTO dto) {
        Vehiculo vehiculo = vehiculoRepository.findById(dto.getVehiculoId())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + dto.getVehiculoId()));

        Documento documento = new Documento();
        documento.setVehiculo(vehiculo);
        documento.setTipoDocumento(dto.getTipoDocumento());
        documento.setFecha_expiro(dto.getFecha_expiro());
        documento.setUrl_foto(dto.getUrl_foto());
        documento.setNotas(dto.getNotas());

        Documento documentoGuardado = documentoRepository.save(documento);
        return convertirAResponseDTO(documentoGuardado);
    }

    public DocumentoResponseDTO convertirAResponseDTO(Documento documento) {
        DocumentoResponseDTO dto = new DocumentoResponseDTO();
        dto.setId(documento.getId());
        dto.setVehiculoId(documento.getVehiculo().getId());
        dto.setTipoDocumento(documento.getTipoDocumento());
        dto.setFecha_expiro(documento.getFecha_expiro());
        dto.setUrl_foto(documento.getUrl_foto());
        dto.setNotas(documento.getNotas());
        dto.setCreatedAt(documento.getCreatedAt());
        dto.setUpdatedAt(documento.getUpdatedAt());
        return dto;
    }

    public List<DocumentoResponseDTO> convertirListaAResponseDTO(List<Documento> documentos) {
        return documentos.stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

}