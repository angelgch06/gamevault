package com.gamevault.gamevault.service;

import com.gamevault.gamevault.DAO.ISocioDAO;
import com.gamevault.gamevault.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SocioService {
    private final ISocioDAO dao;


    public SocioService(ISocioDAO dao) {
        this.dao = dao;
    }

    public List<SocioDetalleDTO> buscar(FiltroSociosDTO filtro){
        return dao.buscar(filtro);
    }
    public SocioDetalleDTO insert(SocioRequestDTO s){
        if(s.getNombre()==null || s.getNombre().isBlank()){
            throw new IllegalArgumentException("RN: El nombre es obligatiorio");
        }
        if(s.getApellidos()==null || s.getApellidos().isBlank()){
            throw new IllegalArgumentException("RN: Los apellidos son obligatiorios");
        }
        if(s.getEmail()==null || s.getEmail().isBlank()){
            throw new IllegalArgumentException("RN: El correo es obligatiorio");
        }
        if(s.getFechaNacimiento()==null){
            throw new IllegalArgumentException("RN: La fecha de nacimiento es obligatioria");
        }
        if(s.getFechaNacimiento().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("RN: La fecha de nacimiento no puede ser futura");
        }
        if(dao.existePorCorreo(s.getEmail())){
            throw new IllegalStateException("RN: Ya existe un socio con ese correo");
        }
        s.setFechaAlta(LocalDate.now());
        s.setActivo(true);
        int idSocioCreado = dao.insert(s);
        return dao.buscarId(idSocioCreado);
    }
    public int contarActivos(){
       return dao.contarActivos();
    }
    public List<SocioDTO>listarActivos(){
        return dao.listarActivos();
    }
    public SocioDetalleDTO actualizarSocio(int id, SocioRequestDTO request){
        SocioDetalleDTO socioExiste= dao.buscarId(id);
        if(socioExiste == null){
            throw new NoSuchElementException("RN: No existe un socio con ese id");
        }
        if(dao.existePorCorreoActualizacion(request.getEmail(),id)){
            throw new IllegalStateException("RN: Ya existe otro socio con ese correo");
        }
        if(request.getNombre()==null || request.getNombre().isBlank()){
            throw new IllegalArgumentException("RN: El nombre es obligatiorio");
        }
        if(request.getApellidos()==null || request.getApellidos().isBlank()){
            throw new IllegalArgumentException("RN: Los apellidos son obligatiorios");
        }
        if(request.getFechaNacimiento()==null){
            throw new IllegalArgumentException("RN: La fecha de nacimiento es obligatioria");
        }
        socioExiste.setNombre(request.getNombre());
        socioExiste.setApellidos(request.getApellidos());
        socioExiste.setEmail(request.getEmail());
        socioExiste.setFechaNacimiento(request.getFechaNacimiento());
        socioExiste.setActivo(request.isActivo());
        dao.actualizar(socioExiste);
        return dao.buscarId(socioExiste.getId());
    }
    public void borrarSocio(int id){
        SocioDetalleDTO socioExiste= dao.buscarId(id);
        if(socioExiste == null){
            throw new NoSuchElementException("RN: No existe un socio con ese id");
        }
        if(dao.tienePrestamos(id)){
            throw new IllegalStateException("RN: No se puede borrar el socio porque tiene prestamos registrados");
        }
        dao.borrarSocio(id);

    }
    public SocioDetalleDTO buscarId(int idSocio){
       SocioDetalleDTO s=dao.buscarId(idSocio);
       if(s == null){
           throw new NoSuchElementException("No existe ningún socio con ese id");
       }
       return s;
    }

}
