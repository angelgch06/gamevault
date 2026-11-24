package com.gamevault.gamevault.service;

import com.gamevault.gamevault.DAO.impl.PrestamoDAO;
import com.gamevault.gamevault.DAO.impl.SocioDAO;
import com.gamevault.gamevault.DAO.impl.VideojuegoDAO;
import com.gamevault.gamevault.dto.EstadisticasDTO;
import org.springframework.stereotype.Service;

@Service
public class EstadisticaService {
    private VideojuegoDAO vDao;
    private SocioDAO sDao;
    private PrestamoDAO pDao;

    public EstadisticaService(VideojuegoDAO vDao, SocioDAO sDao, PrestamoDAO pDao) {
        this.vDao = vDao;
        this.sDao = sDao;
        this.pDao = pDao;
    }

    public EstadisticasDTO obtenerEstadisticas(){
        EstadisticasDTO e=new EstadisticasDTO(vDao.contarVideojuegos(),sDao.contarActivos(),pDao.contarAbiertos(),pDao.contarVencidos());
        return e;
    }
}
