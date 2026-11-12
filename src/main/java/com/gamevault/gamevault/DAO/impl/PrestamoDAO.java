package com.gamevault.gamevault.DAO.impl;

import com.gamevault.gamevault.DAO.IPrestamoDAO;
import com.gamevault.gamevault.dto.FiltroPrestamosDTO;
import com.gamevault.gamevault.dto.PrestamoDTO;
import com.gamevault.gamevault.dto.PrestamoDetalleDTO;
import com.gamevault.gamevault.repository.PrestamoRepository;
import com.gamevault.gamevault.repository.SocioRepository;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Component
public class PrestamoDAO implements IPrestamoDAO {
    private final PrestamoRepository rep;

    public PrestamoDAO(PrestamoRepository rep) {
        this.rep = rep;
    }

    @Override
    public List<PrestamoDetalleDTO> buscar(FiltroPrestamosDTO filtro) {
        return rep.buscar(filtro);
    }

    @Override
    public int insert(PrestamoDTO p) {
        return rep.insert(p);
    }

    @Override
    public void devolver(Connection c, int idPrestamo, LocalDate fechaDevolucion, double multa) {
        rep.devolver(c,idPrestamo,fechaDevolucion,multa);
    }

    @Override
    public PrestamoDetalleDTO buscarPorId(int idPrestamo) {
        return rep.buscarPorId(idPrestamo);
    }

    @Override
    public int contarAbiertos() {
        try {
            return rep.contarAbiertos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int contarVencidos() {
        try {
            return rep.contarVencidos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PrestamoDetalleDTO> listarAbiertos(String socio) {
        return rep.listarAbiertos(socio);
    }

}
