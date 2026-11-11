package com.gamevault.gamevault.repository;

import com.gamevault.gamevault.dto.FiltroSociosDTO;
import com.gamevault.gamevault.dto.SocioDTO;
import com.gamevault.gamevault.dto.SocioDetalleDTO;
import com.gamevault.gamevault.service.SocioService;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SocioRepository {
    private final DataSource dataSource;

    public SocioRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<SocioDetalleDTO> buscar(FiltroSociosDTO filtro ){
        //aqui lo que se hace es que 2 columnas que no estan en la tabla se tienen que calcular, la edad se calcula a partir de la fecha de nacimiento tomamos la fecha actual y restamos, creamos la columna edad, en la otra relacionamos la tabla socios con prestamos verificamos si el socio tiene un prestamo abierto o asociado y contabilizamos ese prestamo
        StringBuilder query= new StringBuilder("SELECT s.id , s.nombre , s.apellidos , s.email , s.fecha_nacimiento , s.fecha_alta , s.activo , TIMESTAMPDIFF(YEAR,s.fecha_nacimiento,CURDATE()) AS edad , (SELECT COUNT(*) FROM prestamos p WHERE p.id_socio=s.id AND p.fecha_devolucion IS NULL ) AS prestamos_abiertos FROM socios s WHERE 1=1 ");
        List<Object> parametro=new ArrayList<>();
        if(filtro.getTexto()!=null && !filtro.getTexto().isBlank()){
            query.append("AND (s.nombre LIKE ? OR s.apellidos LIKE ? ) ");
            String like="%"+filtro.getTexto().trim()+"%";
            parametro.add(like);
            //pongo 2 veces add like para que se me añada en las dos interrogaciones
            parametro.add(like);
        }
        if(filtro.getEmail()!=null && !filtro.getEmail().isBlank()){
            query.append("AND s.email LIKE ? ");
            String like="%"+filtro.getEmail().trim()+"%";
            parametro.add(like);
        }
        if(filtro.getActivo()!=null){
            query.append("AND s.activo = ? ");
            //si esta activo le asigno 1 y si esta en falso o no esta activo le asigno 0
            parametro.add(filtro.getActivo() ? 1 : 0);
        }
        query.append("ORDER BY s.apellidos, s.nombre ");
        List<SocioDetalleDTO> lista=new ArrayList<>();
        try{
            Connection c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query.toString());
            for(int i=0;i<parametro.size();i++){
                ps.setObject(i+1,parametro.get(i));
            }
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                SocioDetalleDTO sd=new SocioDetalleDTO(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("email"),rs.getDate("fecha_nacimiento").toLocalDate(),rs.getDate("fecha_alta").toLocalDate(),rs.getBoolean("activo"),rs.getInt("edad"),rs.getInt("prestamos_abiertos"));
                lista.add(sd);
            }
            c.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return lista;
    }
    public boolean existePorCorreo(String email) throws SQLException {
        //busca en la tabla socios y filtra por el correo que le pasamos, ponemos * porque le vamos a pedir es que nos diga si tiene un siguiente o no
        String query="SELECT * FROM socios WHERE email = ? ";
        Connection c=null;
        try{
            c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();

            return rs.next();//retorna un booleano si encontró algo o no
        }catch(Exception e){
            if (c != null) {
                c.close();
            }
            throw new RuntimeException(e);
        }

    }
    public int insert(SocioDTO s){
        String query="INSERT INTO socios (nombre , apellidos , email , fecha_nacimiento , fecha_alta , activo ) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            Connection c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,s.getNombre());
            ps.setString(2,s.getEmail());
            ps.setString(3,s.getEmail());
            ps.setDate(4, Date.valueOf(s.getFechaNacimiento()));
            ps.setDate(5,Date.valueOf(s.getFechaAlta()));
            ps.setBoolean(6,s.isActivo());
            ps.executeUpdate();
            //aqui obtenemos el id que le di
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);

            }
            c.close();

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return -1;
    }
    public boolean estaActivo(int idSocio) throws SQLException {
        String query="SELECT activo FROM socios WHERE id = ? ";
        Connection c=null;
        try{
             c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ps.setInt(1,idSocio);
            ResultSet rs=ps.executeQuery();

            return rs.next() && rs.getBoolean("activo");
        }catch(Exception e){
            c.close();
            throw new RuntimeException(e);
        }
    }
    public int contarPrestamosAbiertos(int idSocio) throws SQLException {
        //count(*) contabiliza la cantidad de filas devueltas al ejecutar la query
        String query="SELECT COUNT(*) FROM prestamos WHERE id_socio = ? AND fecha_devolucion IS NULL ";
        Connection c=null;
        try{
            c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ps.setInt(1,idSocio);
            ResultSet rs=ps.executeQuery();

            return rs.next() ? rs.getInt(1) : 0;
        }catch(Exception e){
            c.close();
            throw new RuntimeException(e);
        }
    }
    //Regla de negocio 4
    public SocioDetalleDTO buscarId(int idSocio){
        String query= "SELECT s.id , s.nombre , s.apellidos , s.email , s.fecha_nacimiento , s.fecha_alta , s.activo , TIMESTAMPDIFF(YEAR,s.fecha_nacimiento,CURDATE()) AS edad , (SELECT COUNT(*) FROM prestamos p WHERE p.id_socio=s.id AND p.fecha_devolucion IS NULL ) AS prestamos_abiertos FROM socios s WHERE id = ? ";
        try{
            Connection c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ps.setInt(1,idSocio);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                SocioDetalleDTO sd=new SocioDetalleDTO(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("email"),rs.getDate("fecha_nacimiento").toLocalDate(),rs.getDate("fecha_alta").toLocalDate(),rs.getBoolean("activo"),rs.getInt("edad"),rs.getInt("prestamos_abiertos"));
                return sd;
            }
            c.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }
    //Regla de negocio 5
    public boolean tienePrestamoVencido(int idSocio) throws SQLException {
        String query="SELECT 1 FROM prestamos WHERE id_socio = ? AND fecha_devolucion IS NULL AND fecha_limite < CURDATE() LIMIT 1 ";
        Connection c=null;
        try{
            c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ps.setInt(1,idSocio);
            ResultSet rs=ps.executeQuery();

            return rs.next();
        }catch(Exception e){
            c.close();
            throw new RuntimeException(e);
        }
    }
    public int contarActivos() throws SQLException {
        String query="SELECT COUNT(*) FROM socios WHERE activo = 1";
        Connection c = null;
        try{
            c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ResultSet rs=ps.executeQuery();

            return rs.next() ? rs.getInt(1) : 0;

        }catch(Exception e){
            c.close();
            throw new RuntimeException(e);
        }

    }
    public List<SocioDTO> listarActivos(){
        String query="SELECT * FROM socios WHERE activo = 1 ORDER BY apellidos, nombre ";
        List<SocioDTO> lista=new ArrayList<>();
        try{
            Connection c=dataSource.getConnection();
            PreparedStatement ps=c.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                SocioDTO s=new SocioDTO(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("email"),rs.getDate("fecha_nacimiento").toLocalDate(),rs.getDate("fecha_alta").toLocalDate(),rs.getBoolean("activo"));
                lista.add(s);
            }
            c.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return lista;

    }

}
