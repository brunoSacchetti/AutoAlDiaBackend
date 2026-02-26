package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.repository.DocumentoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.Base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService extends BaseServiceImpl<Documento, Long> {

    @Autowired
    private DocumentoRepository documentoRepository;

    // Crear
    @Override
    public Documento crear(Documento entity){
        return documentoRepository.save(entity);
    }

    // Obtener por id
    @Override
    public Optional<Documento> obtenerPorId(Long id){
        return documentoRepository.findById(id);
    }

    // Obtener todos
    @Override
    public List<Documento> obtenerTodos(){
        return documentoRepository.findAll();
    }

    // Actualizar
    @Override
    public Documento actualizar(Long id, Documento entity){
        if(!documentoRepository.existsById(id)){
            throw new RuntimeException("Entidad no encontrada con ID: " + id);
        }
        return documentoRepository.save(entity);
    }

    // Eliminar
    @Override
    public void eliminar(Long id){
        if(!documentoRepository.existsById(id)){
            throw new RuntimeException("Entidad no encontrada con ID: " + id);
        }
        documentoRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id){
        return documentoRepository.existsById(id);
    }


}
