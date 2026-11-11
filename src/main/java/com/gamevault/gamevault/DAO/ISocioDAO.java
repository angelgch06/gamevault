package com.gamevault.gamevault.DAO;

import com.gamevault.gamevault.dto.FiltroSociosDTO;
import com.gamevault.gamevault.dto.SocioDTO;
import com.gamevault.gamevault.dto.SocioDetalleDTO;

import java.util.List;

public interface ISocioDAO {
    List<SocioDetalleDTO> buscar(FiltroSociosDTO filtro );
    boolean existePorCorreo(String email);
    int insert(SocioDTO s);
    boolean estaActivo(int idSocio);
    int contarPrestamosAbiertos(int idSocio);
    SocioDetalleDTO buscarId(int idSocio);
    boolean tienePrestamoVencido(int idSocio);
    int contarActivos();
    List<SocioDTO> listarActivos();

}
