package com.mantenimiento.AutoAlDiaBackend.service.Base;

import com.mantenimiento.AutoAlDiaBackend.model.BaseEntity;
import com.mantenimiento.AutoAlDiaBackend.repository.Base.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, ID> implements BaseService<T, ID> {

    private BaseRepository<T, ID> repository;

    // Crear
    @Override
    public T crear(T entity){
        return repository.save(entity);
    }

    // Obtener por id
    @Override
    public Optional<T> obtenerPorId(ID id){
        return repository.findById(id);
    }

    // Obtener todos
    @Override
    public List<T> obtenerTodos(){
        return repository.findAll();
    }

    // Actualizar
    @Override
    public T actualizar(ID id, T entity){
        if(!repository.existsById(id)){
            throw new RuntimeException("Entidad no encontrada con ID: " + id);
        }
        return repository.save(entity);
    }

    // Eliminar
    @Override
    public void eliminar(ID id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Entidad no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public boolean existe(ID id){
        return repository.existsById(id);
    }
}
