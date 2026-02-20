package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.model.RecordatorioMantenimiento;
import com.mantenimiento.AutoAlDiaBackend.repository.RecordatorioMantenimientoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.Base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordatorioMantenimientoService extends BaseServiceImpl<RecordatorioMantenimiento, Long> {

    @Autowired
    private RecordatorioMantenimientoRepository recordatorioMantenimientoRepository;

}
