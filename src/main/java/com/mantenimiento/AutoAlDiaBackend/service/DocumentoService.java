package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.model.Documento;
import com.mantenimiento.AutoAlDiaBackend.repository.DocumentoRepository;
import com.mantenimiento.AutoAlDiaBackend.service.Base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService extends BaseServiceImpl<Documento, Long> {

    @Autowired
    private DocumentoRepository documentoRepository;




}
