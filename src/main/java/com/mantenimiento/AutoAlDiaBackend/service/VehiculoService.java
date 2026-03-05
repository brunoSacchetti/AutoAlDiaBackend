package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.dto.VehiculoCreateDTO;
import com.mantenimiento.AutoAlDiaBackend.dto.VehiculoResponseDTO;
import com.mantenimiento.AutoAlDiaBackend.model.Usuario;
import com.mantenimiento.AutoAlDiaBackend.model.Vehiculo;
import com.mantenimiento.AutoAlDiaBackend.repository.UsuarioRepository;
import com.mantenimiento.AutoAlDiaBackend.repository.VehiculoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.interfaz.VehiculoServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculoService implements VehiculoServiceInterface {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Vehiculo crear(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Optional<Vehiculo> obtenerPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    @Override
    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    @Override
    @Transactional
    public Vehiculo actualizar(Long id, Vehiculo vehiculo) {
        System.out.println("=== Actualizando Vehículo ID: " + id + " ===");

        // 1. Buscar el vehículo existente (managed por Hibernate)
        Vehiculo vehiculoExistente = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));

        System.out.println("Vehículo existente: " + vehiculoExistente.getMarca() + " " + vehiculoExistente.getModelo());

        // 2. Actualizar campos
        vehiculoExistente.setMarca(vehiculo.getMarca());
        vehiculoExistente.setModelo(vehiculo.getModelo());
        vehiculoExistente.setAnio(vehiculo.getAnio());
        vehiculoExistente.setPatente(vehiculo.getPatente());
        vehiculoExistente.setKm_actual(vehiculo.getKm_actual());

        if (vehiculo.getUrl_foto() != null) {
            vehiculoExistente.setUrl_foto(vehiculo.getUrl_foto());
        }

        // 3. Actualizar usuario si viene
        if (vehiculo.getUsuario() != null && vehiculo.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(vehiculo.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + vehiculo.getUsuario().getId()));
            vehiculoExistente.setUsuario(usuario);
            System.out.println("Usuario actualizado: " + usuario.getNombre());
        }

        // 4. Guardar
        Vehiculo actualizado = vehiculoRepository.save(vehiculoExistente);
        System.out.println("Vehículo actualizado: " + actualizado.getMarca() + " " + actualizado.getModelo() + " - KM: " + actualizado.getKm_actual());

        return actualizado;
    }

    @Override
    public void eliminar(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new RuntimeException("Vehículo no encontrado con ID: " + id);
        }
        vehiculoRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return vehiculoRepository.existsById(id);
    }

    // Métodos personalizados
    @Override
    public List<Vehiculo> obtenerPorUsuarioId(Long usuarioId) {
        return vehiculoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Vehiculo> obtenerPorUsuarioIdOrdenado(Long usuarioId) {
        return vehiculoRepository.findByUsuarioIdOrderByCreatedAtDesc(usuarioId);
    }

    @Override
    public Optional<Vehiculo> buscarPorPatente(String patente) {
        return vehiculoRepository.findByPatente(patente);
    }

    @Override
    public boolean existePorPatente(String patente) {
        return vehiculoRepository.existsByPatente(patente);
    }

    @Override
    public long contarPorUsuarioId(Long usuarioId) {
        return vehiculoRepository.countByUsuarioId(usuarioId);
    }

    // Métodos DTO
    public VehiculoResponseDTO crearDesdeDTO(VehiculoCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setUsuario(usuario);
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setPatente(dto.getPatente());
        vehiculo.setKm_actual(dto.getKm_actual());
        vehiculo.setUrl_foto(dto.getUrl_foto());

        Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculo);
        return convertirAResponseDTO(vehiculoGuardado);
    }

    public VehiculoResponseDTO convertirAResponseDTO(Vehiculo vehiculo) {
        VehiculoResponseDTO dto = new VehiculoResponseDTO();
        dto.setId(vehiculo.getId());
        dto.setUsuarioId(vehiculo.getUsuario().getId());
        dto.setMarca(vehiculo.getMarca());
        dto.setModelo(vehiculo.getModelo());
        dto.setAnio(vehiculo.getAnio());
        dto.setPatente(vehiculo.getPatente());
        dto.setKm_actual(vehiculo.getKm_actual());
        dto.setUrl_foto(vehiculo.getUrl_foto());
        dto.setCreatedAt(vehiculo.getCreatedAt());
        dto.setUpdatedAt(vehiculo.getUpdatedAt());
        return dto;
    }

    public List<VehiculoResponseDTO> convertirListaAResponseDTO(List<Vehiculo> vehiculos) {
        return vehiculos.stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

}
