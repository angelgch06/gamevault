package com.gamevault.gamevault.DAO.impl;

import com.gamevault.gamevault.DAO.IDesarrolladoraDAO;
import com.gamevault.gamevault.dto.DesarrolladoraDTO;
import com.gamevault.gamevault.repository.DesarrolladoraRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DesarrolladoraDAO implements IDesarrolladoraDAO {
    private final DesarrolladoraRepository rep;


    public DesarrolladoraDAO(DesarrolladoraRepository rep) {
        this.rep = rep;
    }


    @Override
    public List<DesarrolladoraDTO> listarTodos() {
        return rep.listarTodos();
    }
}
