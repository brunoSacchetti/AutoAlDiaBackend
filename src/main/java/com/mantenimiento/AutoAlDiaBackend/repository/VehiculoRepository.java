package com.mantenimiento.AutoAlDiaBackend.repository;

import com.mantenimiento.AutoAlDiaBackend.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    // Buscar todos los vehiculos de un usuario
    List<Vehiculo> findByUsuarioId(Long usuarioId);

    // Buscar por patente
    Optional<Vehiculo> findByPatente(String patente);

    // Verificar si existe una patente
    boolean existsByPatente(String patente);

    // Buscar vehículos de un usuario ordenados por fecha de creación (más reciente primero)
    List<Vehiculo> findByUsuarioIdOrderByCreatedAtDesc(Long usuarioId);

    // Contar vehiculos de un usuario
    long countByUsuarioId(Long usuarioId);
}