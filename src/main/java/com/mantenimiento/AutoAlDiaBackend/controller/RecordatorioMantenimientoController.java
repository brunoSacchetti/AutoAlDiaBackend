package com.mantenimiento.AutoAlDiaBackend.controller;

import com.mantenimiento.AutoAlDiaBackend.model.RecordatorioMantenimiento;
import com.mantenimiento.AutoAlDiaBackend.service.RecordatorioMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recordatorio")
public class RecordatorioMantenimientoController{

    @Autowired
    private RecordatorioMantenimientoService recordatorioService;

    @PostMapping("/crear")
    public ResponseEntity<RecordatorioMantenimiento> crear(@RequestBody RecordatorioMantenimiento recordatorio) {
        RecordatorioMantenimiento creado = recordatorioService.crear(recordatorio);
        System.out.println("Recordatorio creado con éxito");
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<RecordatorioMantenimiento> obtenerPorId(@PathVariable Long id) {
        return recordatorioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<RecordatorioMantenimiento>> obtenerTodos() {
        return ResponseEntity.ok(recordatorioService.obtenerTodos());
    }

    @PutMapping("/actualizaR/{id}")
    public ResponseEntity<RecordatorioMantenimiento> actualizar(@PathVariable Long id, @RequestBody RecordatorioMantenimiento recordatorio) {
        try {
            RecordatorioMantenimiento actualizado = recordatorioService.actualizar(id, recordatorio);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            recordatorioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(recordatorioService.existe(id));
    }

    // Endpoints Específicos
    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<List<RecordatorioMantenimiento>> obtenerPorVehiculo(@PathVariable Long vehiculoId) {
        return ResponseEntity.ok(recordatorioService.obtenerPorVehiculoId(vehiculoId));
    }

    @GetMapping("/vehiculo/{vehiculoId}/estado/{estado}")
    public ResponseEntity<List<RecordatorioMantenimiento>> obtenerPorEstado(
            @PathVariable Long vehiculoId,
            @PathVariable EstadoRecordatorio estado) {
        return ResponseEntity.ok(recordatorioService.obtenerPorVehiculoIdYEstado(vehiculoId, estado));
    }

    @GetMapping("/vehiculo/{vehiculoId}/verificar-km/{kmActual}")
    public ResponseEntity<List<RecordatorioMantenimiento>> verificarPorKm(
            @PathVariable Long vehiculoId,
            @PathVariable Integer kmActual) {
        return ResponseEntity.ok(recordatorioService.verificarRecordatoriosPorKm(vehiculoId, kmActual));
    }

    @GetMapping("/vehiculo/{vehiculoId}/verificar-fecha")
    public ResponseEntity<List<RecordatorioMantenimiento>> verificarPorFecha(
            @PathVariable Long vehiculoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        LocalDate fechaVerificar = fecha != null ? fecha : LocalDate.now();
        return ResponseEntity.ok(recordatorioService.verificarRecordatoriosPorFecha(vehiculoId, fechaVerificar));
    }

    @GetMapping("/usuario/{usuarioId}/activos")
    public ResponseEntity<List<RecordatorioMantenimiento>> obtenerActivosPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(recordatorioService.obtenerRecordatoriosActivosPorUsuario(usuarioId));
    }
    */
}
