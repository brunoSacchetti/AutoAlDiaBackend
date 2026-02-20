package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.model.Usuario;
import com.mantenimiento.AutoAlDiaBackend.repository.UsuarioRepository;
import com.mantenimiento.AutoAlDiaBackend.service.Base.BaseService;
import com.mantenimiento.AutoAlDiaBackend.service.Base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseServiceImpl<Usuario, Long> {

    @Autowired
    private UsuarioRepository usuarioRepository;




}
