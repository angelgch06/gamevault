package com.gamevault.gamevault.repository;

import com.gamevault.gamevault.dto.FiltroPrestamosDTO;
import com.gamevault.gamevault.dto.PrestamoDTO;
import com.gamevault.gamevault.dto.PrestamoDetalleDTO;
import com.gamevault.gamevault.dto.SocioDTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.text.spi.DateFormatSymbolsProvider;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrestamoRepository {
    private final DataSource dataSource;

    public PrestamoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<PrestamoDetalleDTO> buscar(FiltroPrestamosDTO filtro){
        StringBuilder query=new StringBuilder("SELECT p.id, p.id_socio, p.id_copia, p.fecha_prestamo, p.fecha_limite, p.fecha_devolucion, p.multa, CONCAT(s.nombre,' ',s.apellidos) AS socio, v.titulo, pl.nombre AS plataforma, c.codigo_inventario FROM prestamos p JOIN socios s ON s.id=p.id_socio JOIN copias c ON c.id=p.id_copia JOIN videojuegos v ON v.id=c.id_videojuego JOIN plataformas pl ON pl.id=c.id_plataforma WHERE 1=1 ");
        List<Object> parametros=new ArrayList<>();
        if(filtro.getSocio()!=null && !filtro.getSocio().isBlank()){
            query.append("AND (s.nombre LIKE ? OR s.apellidos LIKE ?) ");
            String like="%"+filtro.getSocio().trim()+"%";
            parametros.add(like);
            parametros.add(like);
        }
        if(filtro.getTitulo()!=null && !filtro.getTitulo().isBlank()){
            query.append("AND v.titulo LIKE ? ");
            parametros.add("%"+filtro.getTitulo().trim()+"%");
        }
        if(filtro.getFechaDesde()!=null){
            query.append("AND p.fecha_prestamo >= ? ");
            parametros.add(Date.valueOf(filtro.getFechaDesde()));
        }
        if(filtro.getEstado()!=null && !filtro.getEstado().isBlank()){
            switch(filtro.getEstado()){
                case "EN_PLAZO":
                    query.append("AND p.fecha_devolucion IS NULL AND p.fecha_limite >= CURDATE() ");
                    break;
                case "VENCIDO":
                    query.append("AND p.fecha_devolucion IS NULL AND p.fecha_limite < CURDATE() ");
                    break;
                case "DEVUELTO":
                    query.append("AND p.fecha_devolucion IS NOT NULL ");
                    break;
            }
        }
        query.append("ORDER BY p.fecha_prestamo DESC ");
        List<PrestamoDetalleDTO> lista= new ArrayList<>();
        try{
            Connection c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query.toString());
            for(int i=0;i<parametros.size();i++){
                ps.setObject(i+1,parametros.get(i));
            }
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Date devolucion=rs.getDate("fecha_devolucion");
                PrestamoDetalleDTO d=new PrestamoDetalleDTO(rs.getInt("id"),rs.getInt("id_socio"),rs.getInt("id_copia"),rs.getDate("fecha_prestamo").toLocalDate(),rs.getDate("fecha_limite").toLocalDate(),devolucion==null ? null: devolucion.toLocalDate(),rs.getDouble("multa"),rs.getString("socio"),rs.getString("titulo"),rs.getString("plataforma"));
                lista.add(d);

            }
            c.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return lista;
    }
    public int insert(PrestamoDTO p){
        //los 2 ultimos, el penultimo es null porque por defecto es nulo al yo registrar un valor ese valor todavia no tiene dato hasta que yo lo devuelva, y la multa por defecto es 0
        String query="INSERT INTO prestamos (id_socio, id_copia, fecha_prestamo, fecha_limite, fecha_devolucion, multa) VALUES (?,?,?,?,NULL,0) ";
        try{
            Connection c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,p.getIdSocio());
            ps.setInt(2,p.getIdCopia());
            ps.setDate(3,Date.valueOf(p.getFechaPrestamo()));
            ps.setDate(4,Date.valueOf(p.getFechaLimite()));
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
            c.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return -1;

    }
    public void devolver(Connection c,int idPrestamo, LocalDate fechaDevolucion,double multa){
        String query="UPDATE prestamos SET fecha_devolucion = ?, multa = ? WHERE id = ? AND fecha_devolucion IS NULL ";
        try{
            PreparedStatement ps=c.prepareStatement(query);
            ps.setDate(1,Date.valueOf(fechaDevolucion));
            ps.setDouble(2,multa);
            ps.setInt(3,idPrestamo);
            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public PrestamoDetalleDTO buscarPorId(int idPrestamo){
        String query="SELECT p.id, p.id_socio, p.id_copia, p.fecha_prestamo, p.fecha_limite, p.fecha_devolucion, p.multa, CONCAT(s.nombre,' ',s.apellidos) AS socio, v.titulo, pl.nombre AS plataforma, c.codigo_inventario FROM prestamos p JOIN socios s ON s.id=p.id_socio JOIN copias c ON c.id=p.id_copia JOIN videojuegos v ON v.id=c.id_videojuego JOIN plataformas pl ON pl.id=c.id_plataforma WHERE p.id = ? ";
        try{
            Connection c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ps.setInt(1,idPrestamo);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                Date devolucion=rs.getDate("fecha_devolucion");
                PrestamoDetalleDTO d=new PrestamoDetalleDTO(rs.getInt("id"),rs.getInt("id_socio"),rs.getInt("id_copia"),rs.getDate("fecha_prestamo").toLocalDate(),rs.getDate("fecha_limite").toLocalDate(),devolucion==null ? null: devolucion.toLocalDate(),rs.getDouble("multa"),rs.getString("socio"),rs.getString("titulo"),rs.getString("plataforma"));
                return d;

            }
            c.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }
    public int contarAbiertos() throws SQLException {
        String query="SELECT COUNT(*) FROM prestamos WHERE fecha_devolucion IS NULL";
        Connection c=null;
        try{
            c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ResultSet rs= ps.executeQuery();

            return rs.next() ? rs.getInt(1) : 0;

        }catch(SQLException e){
            c.close();
            throw new RuntimeException(e);
        }
    }
    public int contarVencidos() throws SQLException {
        String query="SELECT COUNT(*) FROM prestamos WHERE fecha_devolucion IS NULL AND fecha_limite < CURDATE()";
        Connection c=null;
        try{
            c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ResultSet rs= ps.executeQuery();

            return rs.next() ? rs.getInt(1) : 0;
        }catch(SQLException e){
            c.close();
            throw new RuntimeException(e);
        }
    }
    public List<PrestamoDetalleDTO> listarAbiertos(String socio){
        StringBuilder query=new StringBuilder("SELECT p.id, p.id_socio, p.id_copia, p.fecha_prestamo, p.fecha_limite, p.fecha_devolucion, p.multa, CONCAT(s.nombre,' ',s.apellidos) AS socio, v.titulo, pl.nombre AS plataforma, c.codigo_inventario FROM prestamos p JOIN socios s ON s.id=p.id_socio JOIN copias c ON c.id=p.id_copia JOIN videojuegos v ON v.id=c.id_videojuego JOIN plataformas pl ON pl.id=c.id_plataforma WHERE p.fecha_devolucion IS NULL ");
        List<Object> parametros=new ArrayList<>();
        if(socio != null && !socio.isBlank()){
            query.append("AND (s.nombre LIKE ? OR s.apellidos LIKE ?) ");
            String like="%"+socio.trim()+"%";
            parametros.add(like);
            parametros.add(like);
        }
        query.append("ORDER BY p.fecha_limite");
        List<PrestamoDetalleDTO> lista = new ArrayList<>();
        try{
            Connection c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query.toString());
            for(int i=0; i< parametros.size();i++){
                ps.setObject(i+1,parametros.get(i));
            }
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Date devolucion=rs.getDate("fecha_devolucion");
                PrestamoDetalleDTO p=new PrestamoDetalleDTO(rs.getInt("id"),rs.getInt("id_socio"),rs.getInt("id_copia"),rs.getDate("fecha_prestamo").toLocalDate(),rs.getDate("fecha_limite").toLocalDate(),devolucion==null ? null: devolucion.toLocalDate(),rs.getDouble("multa"),rs.getString("socio"),rs.getString("titulo"),rs.getString("plataforma"));
                lista.add(p);
            }
            c.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

}
