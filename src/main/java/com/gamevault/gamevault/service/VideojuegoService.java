package com.gamevault.gamevault.service;

import com.gamevault.gamevault.DAO.IVideojuegoDAO;
import com.gamevault.gamevault.dto.FiltroVideojuegosDTO;
import com.gamevault.gamevault.dto.VideojuegoDTO;
import com.gamevault.gamevault.dto.VideojuegoDetalleDTO;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideojuegoService {
    private final IVideojuegoDAO dao;

    public VideojuegoService(IVideojuegoDAO dao) {
        this.dao = dao;
    }

    public List<VideojuegoDetalleDTO> buscar(FiltroVideojuegosDTO filtro){
        return dao.buscar(filtro);
    }

    public int insertar(VideojuegoDTO v){
        if(v.getTitulo()==null || v.getTitulo().isBlank() ){
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        int anioActual= Year.now().getValue();
        if(v.getAnioLanzamiento()<1970 || v.getAnioLanzamiento()>anioActual){
            throw new IllegalArgumentException("El año de lanzamiento debe estar entre 1970 y "+anioActual);
        }
        List<Integer> pegis=new ArrayList<>();
        pegis.add(3);
        pegis.add(7);
        pegis.add(12);
        pegis.add(16);
        pegis.add(18);
        if(!pegis.contains(v.getPegi())){
            throw new IllegalArgumentException("El pegi debe estar entre "+pegis);
        }
        return dao.insertar(v);
    }

}
