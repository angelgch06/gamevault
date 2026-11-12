package com.gamevault.gamevault.DAO.impl;

import com.gamevault.gamevault.DAO.IVideojuegoDAO;
import com.gamevault.gamevault.dto.FiltroVideojuegosDTO;
import com.gamevault.gamevault.dto.VideojuegoDTO;
import com.gamevault.gamevault.dto.VideojuegoDetalleDTO;
import com.gamevault.gamevault.repository.VideojuegoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideojuegoDAO  implements IVideojuegoDAO {
    private final VideojuegoRepository rep;

    public VideojuegoDAO(VideojuegoRepository rep) {
        this.rep = rep;
    }

    @Override
    public List<VideojuegoDetalleDTO> buscar(FiltroVideojuegosDTO filtro) {
        return rep.buscar(filtro);
    }

    @Override
    public int insertar(VideojuegoDTO v) {
        return rep.insertar(v);
    }

    @Override
    public VideojuegoDetalleDTO buscarDetallePorId(int id) {
        return rep.buscarDetallePorId(id);
    }
}
