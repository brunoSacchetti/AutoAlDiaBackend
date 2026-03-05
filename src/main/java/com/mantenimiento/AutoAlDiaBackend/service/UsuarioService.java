package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.dto.UsuarioCreateDTO;
import com.mantenimiento.AutoAlDiaBackend.dto.UsuarioResponseDTO;
import com.mantenimiento.AutoAlDiaBackend.model.Usuario;
import com.mantenimiento.AutoAlDiaBackend.repository.UsuarioRepository;
import com.mantenimiento.AutoAlDiaBackend.service.interfaz.UsuarioServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UsuarioServiceInterface {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return usuarioRepository.existsById(id);
    }

    // Métodos personalizados
    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    // ==================== MÉTODOS DE CONVERSIÓN Y DTO ====================

    /**
     * Convierte un UsuarioCreateDTO a una entidad Usuario
     */
    public Usuario dtoCreateToUsuario(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setNombre(dto.getNombre());
        return usuario;
    }

    /**
     * Convierte una entidad Usuario a UsuarioResponseDTO
     */
    public UsuarioResponseDTO usuarioToResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setNombre(usuario.getNombre());
        return dto;
    }

    /**
     * Crea un usuario a partir de un UsuarioCreateDTO
     */
    public UsuarioResponseDTO crearDesdeDTO(UsuarioCreateDTO usuarioCreateDTO) {
        // Validar que el email no existe ya
        if (existePorEmail(usuarioCreateDTO.getEmail())) {
            throw new RuntimeException("El email ya está registrado: " + usuarioCreateDTO.getEmail());
        }

        // Convertir DTO a entidad y guardar
        Usuario usuario = dtoCreateToUsuario(usuarioCreateDTO);
        Usuario usuarioGuardado = crear(usuario);

        // Convertir a DTO de respuesta
        return usuarioToResponseDTO(usuarioGuardado);
    }

    /**
     * Obtiene un usuario por ID y lo devuelve como DTO
     */
    public Optional<UsuarioResponseDTO> obtenerPorIdDTO(Long id) {
        return obtenerPorId(id).map(this::usuarioToResponseDTO);
    }

    /**
     * Obtiene todos los usuarios como DTOs
     */
    public List<UsuarioResponseDTO> obtenerTodosDTO() {
        return obtenerTodos().stream()
                .map(this::usuarioToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un usuario por email y lo devuelve como DTO
     */
    public Optional<UsuarioResponseDTO> buscarPorEmailDTO(String email) {
        return buscarPorEmail(email).map(this::usuarioToResponseDTO);
    }
}
