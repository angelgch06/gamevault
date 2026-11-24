package com.gamevault.gamevault.service;

import com.gamevault.gamevault.DAO.IDesarrolladoraDAO;
import com.gamevault.gamevault.DAO.IVideojuegoDAO;
import com.gamevault.gamevault.dto.*;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VideojuegoService {
    private final IVideojuegoDAO dao;
    private final IDesarrolladoraDAO dDao;

    public VideojuegoService(IVideojuegoDAO dao, IDesarrolladoraDAO dDao) {
        this.dao = dao;
        this.dDao = dDao;
    }

    public List<VideojuegoDetalleDTO> buscar(FiltroVideojuegosDTO filtro){
        return dao.buscar(filtro);
    }

    public VideojuegoDetalleDTO insertar(VideojuegoRequestDTO v){
        if(v.getTitulo()==null || v.getTitulo().isBlank() ){
            throw new IllegalArgumentException("RN: El titulo es obligatorio");
        }
        int anioActual= Year.now().getValue();
        if(v.getAnioDesde()<1970 || v.getAnioDesde()>anioActual){
            throw new IllegalArgumentException("RN: El año de lanzamiento debe estar entre 1970 y "+anioActual);
        }
        List<Integer> pegis=new ArrayList<>();
        pegis.add(3);
        pegis.add(7);
        pegis.add(12);
        pegis.add(16);
        pegis.add(18);
        if(!pegis.contains(v.getPegiMax())){
            throw new IllegalArgumentException("RN: El pegi debe estar entre "+pegis);
        }
        DesarrolladoraDTO desarrolladora=dDao.buscarPorId(v.getIdDesarrolladora());
        if(desarrolladora == null){
            throw new NoSuchElementException("No existe ninguna desarrolladora con ese id");
        }
        int idGenerado=dao.insertar(v);
        return dao.buscarDetallePorId(idGenerado);
    }
    public VideojuegoDetalleDTO buscarDetallePorId(int id){
        VideojuegoDetalleDTO v= dao.buscarDetallePorId(id);
        if(v==null){
            throw new NoSuchElementException("id del videojuego no existe");
        }
        return v;
    }

    public int contarVideojuegos(){
        return dao.contarVideojuegos();
    }

}
