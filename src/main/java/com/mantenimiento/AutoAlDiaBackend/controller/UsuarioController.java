package com.mantenimiento.AutoAlDiaBackend.controller;

import com.mantenimiento.AutoAlDiaBackend.dto.UsuarioCreateDTO;
import com.mantenimiento.AutoAlDiaBackend.dto.UsuarioResponseDTO;
import com.mantenimiento.AutoAlDiaBackend.model.Usuario;
import com.mantenimiento.AutoAlDiaBackend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // CRUD Básico
    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponseDTO> crear(@RequestBody UsuarioCreateDTO usuarioDTO) {
        UsuarioResponseDTO creado = usuarioService.crearDesdeDTO(usuarioDTO);
        System.out.println("Usuario creado con éxito");
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorIdDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodosDTO());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario actualizado = usuarioService.actualizar(id, usuario);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.existe(id));
    }

    // Endpoints Específicos
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/existe/email/{email}")
    public ResponseEntity<Boolean> existePorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.existePorEmail(email));
    }
    */

}
