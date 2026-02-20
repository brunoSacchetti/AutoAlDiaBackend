package com.mantenimiento.AutoAlDiaBackend.controller;

import com.mantenimiento.AutoAlDiaBackend.controller.Base.BaseController;
import com.mantenimiento.AutoAlDiaBackend.model.Usuario;
import com.mantenimiento.AutoAlDiaBackend.model.Vehiculo;
import com.mantenimiento.AutoAlDiaBackend.service.UsuarioService;
import com.mantenimiento.AutoAlDiaBackend.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController extends BaseController<Vehiculo, Long>{
    @Autowired
    private VehiculoService vehiculoService;

}
