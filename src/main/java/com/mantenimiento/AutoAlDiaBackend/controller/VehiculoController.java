package com.mantenimiento.AutoAlDiaBackend.controller;

import com.mantenimiento.AutoAlDiaBackend.dto.VehiculoCreateDTO;
import com.mantenimiento.AutoAlDiaBackend.dto.VehiculoResponseDTO;
import com.mantenimiento.AutoAlDiaBackend.model.Usuario;
import com.mantenimiento.AutoAlDiaBackend.model.Vehiculo;
import com.mantenimiento.AutoAlDiaBackend.service.UsuarioService;
import com.mantenimiento.AutoAlDiaBackend.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;


    @PostMapping("/crear")
    public ResponseEntity<VehiculoResponseDTO> crear(@RequestBody VehiculoCreateDTO vehiculoDTO) {
        VehiculoResponseDTO creado = vehiculoService.crearDesdeDTO(vehiculoDTO);
        System.out.println("Vehículo creado con éxito");
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<VehiculoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return vehiculoService.obtenerPorId(id)
                .map(v -> ResponseEntity.ok(vehiculoService.convertirAResponseDTO(v)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<VehiculoResponseDTO>> obtenerTodos() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerTodos();
        return ResponseEntity.ok(vehiculoService.convertirListaAResponseDTO(vehiculos));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<VehiculoResponseDTO> actualizar(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {

        System.out.println("=== ACTUALIZAR Vehículo ===");
        System.out.println("ID del path: " + id);
        System.out.println("Marca recibida: " + vehiculo.getMarca());
        System.out.println("Usuario ID recibido: " + (vehiculo.getUsuario() != null ? vehiculo.getUsuario().getId() : "null"));

        try {
            Vehiculo actualizado = vehiculoService.actualizar(id, vehiculo);
            return ResponseEntity.ok(vehiculoService.convertirAResponseDTO(actualizado));
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarVehiculo/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            vehiculoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @GetMapping("/existeVehiculo/{id}")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculoService.existe(id));
    }

    // Endpoints Específicos
    @GetMapping("/usuarioVehiculo/{usuarioId}")
    public ResponseEntity<List<Vehiculo>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(vehiculoService.obtenerPorUsuarioId(usuarioId));
    }

    @GetMapping("/usuarioVehiculo/{usuarioId}/ordenado")
    public ResponseEntity<List<Vehiculo>> obtenerPorUsuarioOrdenado(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(vehiculoService.obtenerPorUsuarioIdOrdenado(usuarioId));
    }

    @GetMapping("/patente/{patente}")
    public ResponseEntity<Vehiculo> buscarPorPatente(@PathVariable String patente) {
        return vehiculoService.buscarPorPatente(patente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/existe/patente/{patente}")
    public ResponseEntity<Boolean> existePorPatente(@PathVariable String patente) {
        return ResponseEntity.ok(vehiculoService.existePorPatente(patente));
    }

    @GetMapping("/usuario/{usuarioId}/cantidad")
    public ResponseEntity<Long> contarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(vehiculoService.contarPorUsuarioId(usuarioId));
    }
    */

}
