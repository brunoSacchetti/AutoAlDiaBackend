package com.mantenimiento.AutoAlDiaBackend.service.Base;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {

    // Crear
    T crear(T entity);

    // Obtener por ID
    Optional<T> obtenerPorId(ID id);

    // Obtener todos
    List<T> obtenerTodos();

    // Actualizar
    T actualizar(ID id, T entity);

    // Eliminar
    void eliminar(ID id);

    // Verificar si existe
    boolean existe(ID id);







}
