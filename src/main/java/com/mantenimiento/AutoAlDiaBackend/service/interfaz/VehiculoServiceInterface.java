package com.mantenimiento.AutoAlDiaBackend.service.interfaz;

import com.mantenimiento.AutoAlDiaBackend.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoServiceInterface {

    // CRUD Básico
    Vehiculo crear(Vehiculo vehiculo);
    Optional<Vehiculo> obtenerPorId(Long id);
    List<Vehiculo> obtenerTodos();
    Vehiculo actualizar(Long id, Vehiculo vehiculo);
    void eliminar(Long id);
    boolean existe(Long id);

    // Métodos personalizados
    List<Vehiculo> obtenerPorUsuarioId(Long usuarioId);
    List<Vehiculo> obtenerPorUsuarioIdOrdenado(Long usuarioId);
    Optional<Vehiculo> buscarPorPatente(String patente);
    boolean existePorPatente(String patente);
    long contarPorUsuarioId(Long usuarioId);

}
