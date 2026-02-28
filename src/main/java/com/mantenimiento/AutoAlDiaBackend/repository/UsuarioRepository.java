package com.mantenimiento.AutoAlDiaBackend.repository;

import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // CONSULTAS PERSONALIZADAS

    // Buscar por email
    Optional<Usuario> findByEmail(String email);

    // Verificar si existe email
    boolean existsByEmail(String email);

}
