package com.mantenimiento.AutoAlDiaBackend.controller;

import com.mantenimiento.AutoAlDiaBackend.controller.Base.BaseController;
import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documento")
public class DocumentoController extends BaseController<Documento, Long> {
    @Autowired
    private DocumentoService documentoService;


}
