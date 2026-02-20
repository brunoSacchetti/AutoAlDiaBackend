package com.mantenimiento.AutoAlDiaBackend.controller;

import com.mantenimiento.AutoAlDiaBackend.controller.Base.BaseController;
import com.mantenimiento.AutoAlDiaBackend.model.RecordatorioMantenimiento;
import com.mantenimiento.AutoAlDiaBackend.model.Usuario;
import com.mantenimiento.AutoAlDiaBackend.service.RecordatorioMantenimientoService;
import com.mantenimiento.AutoAlDiaBackend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/recordatorio")
public class RecordatorioMantenimientoController extends BaseController<RecordatorioMantenimiento, Long>{

    @Autowired
    private RecordatorioMantenimientoService recordatorioService;


}
