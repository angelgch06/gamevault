package com.gamevault.gamevault.DAO.impl;

import com.gamevault.gamevault.DAO.ICopiaDAO;
import com.gamevault.gamevault.dto.CopiaDTO;
import com.gamevault.gamevault.dto.CopiaDisponibleDTO;
import com.gamevault.gamevault.repository.CopiaRepository;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class CopiaDAO implements ICopiaDAO {
    private final CopiaRepository rep;

    public CopiaDAO(CopiaRepository rep) {
        this.rep = rep;
    }


    @Override
    public List<CopiaDisponibleDTO> listarDisponibles() {
        return rep.listarDisponibles();
    }

    @Override
    public CopiaDTO busqueadaPorId(int id) {
        return rep.busqueadaPorId(id);
    }

    @Override
    public boolean estaDisponible(int idCopia) throws SQLException {
        return rep.estaDisponible(idCopia);
    }

    @Override
    public void marcarDanada(Connection c, int idCopia) {
        rep.marcarDanada(c,idCopia);
    }

}
