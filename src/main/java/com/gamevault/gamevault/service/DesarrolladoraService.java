package com.gamevault.gamevault.service;

import com.gamevault.gamevault.DAO.IDesarrolladoraDAO;
import com.gamevault.gamevault.dto.DesarrolladoraDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesarrolladoraService {
    private final IDesarrolladoraDAO dao;


    public DesarrolladoraService(IDesarrolladoraDAO dao) {
        this.dao = dao;
    }

    public List<DesarrolladoraDTO> listarTodos(){
        return dao.listarTodos();
    }
}
