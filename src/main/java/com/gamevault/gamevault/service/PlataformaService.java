package com.gamevault.gamevault.service;

import com.gamevault.gamevault.DAO.IPlataformaDAO;
import com.gamevault.gamevault.dto.PlataformaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaService {
    private final IPlataformaDAO dao;


    public PlataformaService(IPlataformaDAO dao) {
        this.dao = dao;
    }

    public List<PlataformaDTO> listarTodos(){
        return dao.listarTodos();
    }
}
