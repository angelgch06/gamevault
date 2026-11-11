package com.gamevault.gamevault.DAO.impl;

import com.gamevault.gamevault.DAO.IPlataformaDAO;
import com.gamevault.gamevault.dto.PlataformaDTO;
import com.gamevault.gamevault.repository.PlataformaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlataformaDAO implements IPlataformaDAO {
    private final PlataformaRepository rep;


    public PlataformaDAO(PlataformaRepository rep) {
        this.rep = rep;
    }

    @Override
    public List<PlataformaDTO> listarTodos() {
        return rep.listarTodos();
    }
}
