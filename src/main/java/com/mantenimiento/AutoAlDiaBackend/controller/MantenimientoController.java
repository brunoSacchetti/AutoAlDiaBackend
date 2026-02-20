package com.mantenimiento.AutoAlDiaBackend.controller;

import com.mantenimiento.AutoAlDiaBackend.controller.Base.BaseController;
import com.mantenimiento.AutoAlDiaBackend.model.Mantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.RecordatorioMantenimiento;
import com.mantenimiento.AutoAlDiaBackend.service.MantenimientoService;
import com.mantenimiento.AutoAlDiaBackend.service.RecordatorioMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mantenimiento")
public class MantenimientoController extends BaseController<Mantenimiento, Long>{
    @Autowired
    private MantenimientoService mantenimientoService;


}
