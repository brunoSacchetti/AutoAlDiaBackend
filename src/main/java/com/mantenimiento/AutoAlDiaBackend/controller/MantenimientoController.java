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

    @GetMapping("/obtener/{id}")
    public ResponseEntity<MantenimientoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return mantenimientoService.obtenerPorId(id)
                .map(m -> ResponseEntity.ok(mantenimientoService.convertirAResponseDTO(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<MantenimientoResponseDTO>> obtenerTodos() {
        List<Mantenimiento> mantenimientos = mantenimientoService.obtenerTodos();
        return ResponseEntity.ok(mantenimientoService.convertirListaAResponseDTO(mantenimientos));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<MantenimientoResponseDTO> actualizar(@PathVariable Long id, @RequestBody Mantenimiento registro) {
        try {
            Mantenimiento actualizado = mantenimientoService.actualizar(id, registro);
            return ResponseEntity.ok(mantenimientoService.convertirAResponseDTO(actualizado));
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            mantenimientoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(mantenimientoService.existe(id));
    }

    // Endpoints Específicos
    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<List<Mantenimiento>> obtenerPorVehiculo(@PathVariable Long vehiculoId) {
        return ResponseEntity.ok(mantenimientoService.obtenerPorVehiculoId(vehiculoId));
    }


    @GetMapping("/vehiculo/{vehiculoId}/ordenado")
    public ResponseEntity<List<Mantenimiento>> obtenerPorVehiculoOrdenado(@PathVariable Long vehiculoId) {
        return ResponseEntity.ok(mantenimientoService.obtenerPorVehiculoIdOrdenadoPorFecha(vehiculoId));
    }

    /*
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
