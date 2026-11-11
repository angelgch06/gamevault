package com.gamevault.gamevault.DAO;

import com.gamevault.gamevault.dto.CopiaDTO;
import com.gamevault.gamevault.dto.CopiaDisponibleDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ICopiaDAO {
    List<CopiaDisponibleDTO> listarDisponibles();
    CopiaDTO busqueadaPorId(int id);
    boolean estaDisponible(int idCopia) throws SQLException;
    void marcarDanada(Connection c, int idCopia);

}
