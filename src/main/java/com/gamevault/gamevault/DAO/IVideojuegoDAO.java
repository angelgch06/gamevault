package com.gamevault.gamevault.DAO;


import com.gamevault.gamevault.dto.FiltroVideojuegosDTO;
import com.gamevault.gamevault.dto.VideojuegoDTO;
import com.gamevault.gamevault.dto.VideojuegoDetalleDTO;

import java.util.List;

public interface IVideojuegoDAO {
    List<VideojuegoDetalleDTO> buscar(FiltroVideojuegosDTO filtro);
    int insertar(VideojuegoDTO v);
    VideojuegoDetalleDTO buscarDetallePorId(int id);
}
