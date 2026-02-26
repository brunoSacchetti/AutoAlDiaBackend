package com.mantenimiento.AutoAlDiaBackend.service.interfaz;

import com.mantenimiento.AutoAlDiaBackend.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceInterface {

    // CRUD Básico
    Usuario crear(Usuario usuario);
    Optional<Usuario> obtenerPorId(Long id);
    List<Usuario> obtenerTodos();
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
    boolean existe(Long id);

    // Métodos personalizados
    Optional<Usuario> buscarPorEmail(String email);
    boolean existePorEmail(String email);

}
