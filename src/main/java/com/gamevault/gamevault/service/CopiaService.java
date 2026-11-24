package com.gamevault.gamevault.service;


import com.gamevault.gamevault.DAO.ICopiaDAO;
import com.gamevault.gamevault.dto.CopiaDetalleDTO;
import com.gamevault.gamevault.dto.CopiaDisponibleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopiaService {
    private final ICopiaDAO dao;


    public CopiaService(ICopiaDAO dao) {
        this.dao = dao;
    }

    public List<CopiaDisponibleDTO> listarDisponibles(){
        return dao.listarDisponibles();
    }
    public List<CopiaDetalleDTO> listarTodos(){
        return dao.listarTodos();
    }
}
