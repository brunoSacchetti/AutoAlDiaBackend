package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.model.Vehiculo;
import com.mantenimiento.AutoAlDiaBackend.repository.VehiculoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.Base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService extends BaseServiceImpl<Vehiculo, Long> {

    @Autowired
    private VehiculoRepository vehiculoRepository;

}
