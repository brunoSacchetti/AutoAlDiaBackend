package com.mantenimiento.AutoAlDiaBackend.controller;

import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documento")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @PostMapping("/crearDocumento")
    public ResponseEntity<Documento> crear(@RequestBody Documento documento) {
        Documento creado = documentoService.crear(documento);
        System.out.println("Documento creado con éxito");
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/obtenerDocumento/{id}")
    public ResponseEntity<Documento> obtenerPorId(@PathVariable Long id) {
        return documentoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/obtenerAllDocumento")
    public ResponseEntity<List<Documento>> obtenerTodos() {
        return ResponseEntity.ok(documentoService.obtenerTodos());
    }

    @PutMapping("/actualizarDocumento/{id}")
    public ResponseEntity<Documento> actualizar(@PathVariable Long id, @RequestBody Documento documento) {
        try {
            Documento actualizado = documentoService.actualizar(id, documento);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarDocumento/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            documentoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(documentoService.existe(id));
    }

    // Endpoints Específicos
    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<List<Documento>> obtenerPorVehiculo(@PathVariable Long vehiculoId) {
        return ResponseEntity.ok(documentoService.obtenerPorVehiculoId(vehiculoId));
    }

    @GetMapping("/vehiculo/{vehiculoId}/tipo/{tipo}")
    public ResponseEntity<List<Documento>> obtenerPorTipo(
            @PathVariable Long vehiculoId,
            @PathVariable TipoDocumento tipo) {
        return ResponseEntity.ok(documentoService.obtenerPorVehiculoIdYTipo(vehiculoId, tipo));
    }

    @GetMapping("/vehiculo/{vehiculoId}/vencidos")
    public ResponseEntity<List<Documento>> obtenerVencidos(@PathVariable Long vehiculoId) {
        return ResponseEntity.ok(documentoService.obtenerDocumentosVencidos(vehiculoId));
    }

    @GetMapping("/vehiculo/{vehiculoId}/por-vencer")
    public ResponseEntity<List<Documento>> obtenerPorVencer(
            @PathVariable Long vehiculoId,
            @RequestParam(defaultValue = "30") int dias) {
        return ResponseEntity.ok(documentoService.obtenerDocumentosPorVencer(vehiculoId, dias));
    }

    @GetMapping("/usuario/{usuarioId}/vencidos")
    public ResponseEntity<List<Documento>> obtenerVencidosPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(documentoService.obtenerDocumentosVencidosPorUsuario(usuarioId));
    }
    */
}
