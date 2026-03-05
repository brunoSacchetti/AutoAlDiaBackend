package com.mantenimiento.AutoAlDiaBackend.controller;

import com.mantenimiento.AutoAlDiaBackend.dto.MantenimientoCreateDTO;
import com.mantenimiento.AutoAlDiaBackend.dto.MantenimientoResponseDTO;
import com.mantenimiento.AutoAlDiaBackend.model.Mantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.RecordatorioMantenimiento;
import com.mantenimiento.AutoAlDiaBackend.service.MantenimientoService;
import com.mantenimiento.AutoAlDiaBackend.service.RecordatorioMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimiento")
public class MantenimientoController{
    @Autowired
    private MantenimientoService mantenimientoService;

    @PostMapping("/crearMantenimiento")
    public ResponseEntity<MantenimientoResponseDTO> crear(@RequestBody MantenimientoCreateDTO registroDTO) {
        MantenimientoResponseDTO creado = mantenimientoService.crearDesdeDTO(registroDTO);
        System.out.println("Registro de mantenimiento creado con éxito");
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/obtenerMantenimiento/{id}")
    public ResponseEntity<MantenimientoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return mantenimientoService.obtenerPorId(id)
                .map(m -> ResponseEntity.ok(mantenimientoService.convertirAResponseDTO(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/obtenerAllMantenimientos")
    public ResponseEntity<List<MantenimientoResponseDTO>> obtenerTodos() {
        List<Mantenimiento> mantenimientos = mantenimientoService.obtenerTodos();
        return ResponseEntity.ok(mantenimientoService.convertirListaAResponseDTO(mantenimientos));
    }

    @PutMapping("/actualizarMantenimiento/{id}")
    public ResponseEntity<MantenimientoResponseDTO> actualizar(@PathVariable Long id, @RequestBody Mantenimiento registro) {
        try {
            Mantenimiento actualizado = mantenimientoService.actualizar(id, registro);
            return ResponseEntity.ok(mantenimientoService.convertirAResponseDTO(actualizado));
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarMantenimiento/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            mantenimientoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(registroService.existe(id));
    }

    // Endpoints Específicos
    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<List<RegistroMantenimiento>> obtenerPorVehiculo(@PathVariable Long vehiculoId) {
        return ResponseEntity.ok(registroService.obtenerPorVehiculoId(vehiculoId));
    }

    @GetMapping("/vehiculo/{vehiculoId}/ordenado")
    public ResponseEntity<List<RegistroMantenimiento>> obtenerPorVehiculoOrdenado(@PathVariable Long vehiculoId) {
        return ResponseEntity.ok(registroService.obtenerPorVehiculoIdOrdenadoPorFecha(vehiculoId));
    }

    @GetMapping("/vehiculo/{vehiculoId}/tipo/{tipoServicio}")
    public ResponseEntity<List<RegistroMantenimiento>> obtenerPorTipoServicio(
            @PathVariable Long vehiculoId,
            @PathVariable TipoServicio tipoServicio) {
        return ResponseEntity.ok(registroService.obtenerPorVehiculoIdYTipoServicio(vehiculoId, tipoServicio));
    }

    @GetMapping("/vehiculo/{vehiculoId}/rango-fechas")
    public ResponseEntity<List<RegistroMantenimiento>> obtenerEntreFechas(
            @PathVariable Long vehiculoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(registroService.obtenerEntreFechas(vehiculoId, fechaInicio, fechaFin));
    }

    @GetMapping("/vehiculo/{vehiculoId}/costo-total")
    public ResponseEntity<BigDecimal> calcularCostoTotal(@PathVariable Long vehiculoId) {
        return ResponseEntity.ok(registroService.calcularCostoTotalPorVehiculo(vehiculoId));
    }

    @GetMapping("/vehiculo/{vehiculoId}/costo-rango")
    public ResponseEntity<BigDecimal> calcularCostoEnRango(
            @PathVariable Long vehiculoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(registroService.calcularCostoEnRango(vehiculoId, fechaInicio, fechaFin));
    }

    @GetMapping("/vehiculo/{vehiculoId}/ultimo-tipo/{tipoServicio}")
    public ResponseEntity<RegistroMantenimiento> obtenerUltimoPorTipo(
            @PathVariable Long vehiculoId,
            @PathVariable TipoServicio tipoServicio) {
        RegistroMantenimiento ultimo = registroService.obtenerUltimoMantenimientoPorTipo(vehiculoId, tipoServicio);
        return ultimo != null ? ResponseEntity.ok(ultimo) : ResponseEntity.notFound().build();
    }

    @GetMapping("/vehiculo/{vehiculoId}/cantidad")
    public ResponseEntity<Long> contarPorVehiculo(@PathVariable Long vehiculoId) {
        return ResponseEntity.ok(registroService.contarPorVehiculoId(vehiculoId));
    }
    */

}
