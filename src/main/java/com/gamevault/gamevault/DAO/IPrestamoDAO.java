package com.gamevault.gamevault.DAO;

import com.gamevault.gamevault.dto.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface IPrestamoDAO {
    List<PrestamoDetalleDTO> buscar(FiltroPrestamosDTO filtro );
    int insert(PrestamoDTO p);
    void devolver(Connection c, int idPrestamo, LocalDate fechaDevolucion, double multa);
    PrestamoDetalleDTO buscarPorId(int idPrestamo);
    int contarAbiertos();
    int contarVencidos();
    List<PrestamoDetalleDTO> listarAbiertos(String socio);

}
