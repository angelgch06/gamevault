package com.gamevault.gamevault.service;

import com.gamevault.gamevault.DAO.ICopiaDAO;
import com.gamevault.gamevault.DAO.IPrestamoDAO;
import com.gamevault.gamevault.DAO.ISocioDAO;
import com.gamevault.gamevault.dto.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PrestamoService {
    private final IPrestamoDAO prestamoDao;
    private final ISocioDAO socioDao;
    private final ICopiaDAO copiaDao;
    private final DataSource dataSource;

    public PrestamoService(IPrestamoDAO prestamoDao, ISocioDAO socioDao, ICopiaDAO copiaDao, DataSource dataSource) {
        this.prestamoDao = prestamoDao;
        this.socioDao = socioDao;
        this.copiaDao = copiaDao;
        this.dataSource = dataSource;
    }

    public List<PrestamoDetalleDTO> buscar(FiltroPrestamosDTO filtro){
       return prestamoDao.buscar(filtro);
    }

    public PrestamoDetalleDTO insert(int idSocio,int idCopia) throws SQLException {
       //Regla de negocio 1
       if(!socioDao.estaActivo(idSocio)){
           throw new IllegalArgumentException("El socio no está activo");
       }
       //Regla de negocio 2
       if(!copiaDao.estaDisponible(idCopia)){
           throw new IllegalArgumentException("La copia no está disponible");
       }
       //Regla de negocio 3
        if(socioDao.contarPrestamosAbiertos(idSocio) >= 3){
            throw new IllegalArgumentException("El socio ya tiene 3 prestamos abiertos");
        }
        //Regla de negocio 4
        SocioDetalleDTO socio=socioDao.buscarId(idSocio);
        List<CopiaDisponibleDTO> copias=copiaDao.listarDisponibles();
        CopiaDisponibleDTO copia=null;
        for(int i=0;i<copias.size();i++){
            if(copias.get(i).getId() == idCopia){
                copia=copias.get(i);
            }
        }
        if(socio.getEdad()<copia.getPegi()){
            throw new IllegalArgumentException("La edad del socio no es válida para el pegi del juego");
        }
        //Regla de negocio 5
        if(socioDao.tienePrestamoVencido(idSocio)){
            throw new IllegalArgumentException("El socio tiene un prestamo vencido");
        }
        int anioActual= Year.now().getValue();
        int dias=0;
        if (copia.getAnioLanzamiento() == anioActual || copia.getAnioLanzamiento() == anioActual - 1) {
            dias=7;

        }else{
            dias=15;
        }
        LocalDate hoy=LocalDate.now();
        PrestamoDTO prestamo=new PrestamoDTO();
        prestamo.setIdSocio(idSocio);
        prestamo.setIdCopia(idCopia);
        prestamo.setFechaPrestamo(hoy);
        //plusdays le suma dias a la fecha que yo le ponga
        prestamo.setFechaLimite(hoy.plusDays(dias));
        prestamo.setMulta(0);
        int id=prestamoDao.insert(prestamo);
        return prestamoDao.buscarPorId(id);
    }
    public PrestamoDetalleDTO devolver(int idPrestamo, boolean copiaDanada) throws SQLException {
        PrestamoDetalleDTO p=prestamoDao.buscarPorId(idPrestamo);
        if(p==null){
            throw new IllegalArgumentException("El prestamo no existe");
        }
        if(p.getFechaDevolucion() != null){
            throw new IllegalArgumentException("El prestamo ya ha sido devuelto");
        }
        LocalDate hoy=LocalDate.now();
        long diasRetraso=0;
        if(hoy.isAfter(p.getFechaLimite())){
            diasRetraso= ChronoUnit.DAYS.between(p.getFechaLimite(),hoy);
        }
        double multa=diasRetraso*0.50;
        Connection c=null;
        try{
            c=dataSource.getConnection();
            c.setAutoCommit(false);
            prestamoDao.devolver(c,idPrestamo,hoy,multa);
            if(copiaDanada){
                copiaDao.marcarDanada(c,p.getIdCopia());
            }
            c.commit();
        }catch(SQLException e){
            c.rollback();
            throw new RuntimeException(e);
        }
        finally {
            c.setAutoCommit(true);
        }
        return prestamoDao.buscarPorId(idPrestamo);
    }
    public int contarAbiertos(){
        return prestamoDao.contarAbiertos();
    }
    public int contarVencidos(){
        return prestamoDao.contarVencidos();
    }
    public List<PrestamoDetalleDTO> listarAbiertos(String socio){
        return prestamoDao.listarAbiertos(socio);
    }

}
