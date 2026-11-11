package com.gamevault.gamevault.DAO.impl;

import com.gamevault.gamevault.DAO.ISocioDAO;
import com.gamevault.gamevault.dto.FiltroSociosDTO;
import com.gamevault.gamevault.dto.SocioDTO;
import com.gamevault.gamevault.dto.SocioDetalleDTO;
import com.gamevault.gamevault.repository.SocioRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class SocioDAO implements ISocioDAO {
    private final SocioRepository rep;

    public SocioDAO(SocioRepository rep) {
        this.rep = rep;
    }

    @Override
    public List<SocioDetalleDTO> buscar(FiltroSociosDTO filtro) {
        return rep.buscar(filtro);
    }

    @Override
    public boolean existePorCorreo(String email) {
        try {
            return rep.existePorCorreo(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(SocioDTO s) {
        return rep.insert(s);
    }

    @Override
    public boolean estaActivo(int idSocio) {
        try {
            return rep.estaActivo(idSocio);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int contarPrestamosAbiertos(int idSocio) {
        try {
            return rep.contarPrestamosAbiertos(idSocio);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SocioDetalleDTO buscarId(int idSocio) {
        return rep.buscarId(idSocio);
    }

    @Override
    public boolean tienePrestamoVencido(int idSocio) {
        try {
            return rep.tienePrestamoVencido(idSocio);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int contarActivos() {
        try {
            return rep.contarActivos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SocioDTO> listarActivos() {
        return rep.listarActivos();
    }

}
