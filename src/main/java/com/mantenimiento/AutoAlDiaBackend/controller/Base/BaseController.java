package com.mantenimiento.AutoAlDiaBackend.controller.Base;

import com.mantenimiento.AutoAlDiaBackend.model.BaseEntity;
import com.mantenimiento.AutoAlDiaBackend.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


public abstract class BaseController<T extends BaseEntity, ID> {

    @Autowired
    private BaseService<T, ID> baseService;

    // Crear
    @PostMapping("/crear")
    public ResponseEntity<T> crear(@RequestBody T entity){
        T creado = baseService.crear(entity);
        System.out.println("Creado con exito");
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Obtener Por ID
    @GetMapping("/obtener/{id}")
    public Optional<T> obtenerPorId(@PathVariable ID id){
        return baseService.obtenerPorId(id);
    }


    // Optener Todos
    @GetMapping("/obtenerTodos")
    public List<T> obtenerTodos(){
        return baseService.obtenerTodos();
    }

    // Actualizar
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<T> actualizar(@PathVariable ID id, @RequestBody T entity){
        try {
            T actualizado = baseService.actualizar(id, entity);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException error){
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(ID id){
        baseService.eliminar(id);
    }

    // Verificar si existe
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existe(@PathVariable ID id){
        boolean existe = baseService.existe(id);
        return ResponseEntity.ok(existe);
    }


}
