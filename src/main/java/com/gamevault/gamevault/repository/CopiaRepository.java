package com.gamevault.gamevault.repository;

import com.gamevault.gamevault.dto.CopiaDTO;
import com.gamevault.gamevault.dto.CopiaDisponibleDTO;
import com.gamevault.gamevault.dto.SocioDetalleDTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CopiaRepository {
    private final DataSource dataSource;

    public CopiaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<CopiaDisponibleDTO> listarDisponibles(){
        String query="SELECT c.id, c.codigo_inventario, v.titulo, v.pegi, v.anio_lanzamiento, p.nombre AS plataforma FROM copias c JOIN videojuegos v ON v.id = c.id_videojuego JOIN plataformas p ON p.id = c.id_plataforma WHERE c.activa = 1 AND NOT EXISTS (SELECT 1 FROM prestamos pr WHERE pr.id_copia = c.id AND pr.fecha_devolucion IS NULL  ) ORDER BY v.titulo ";
        List<CopiaDisponibleDTO> lista= new ArrayList<>();
        try{
            Connection conexion=dataSource.getConnection();
            PreparedStatement ps=conexion.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                CopiaDisponibleDTO c= new CopiaDisponibleDTO(rs.getInt("id"),rs.getString("codigo_inventario"),rs.getString("titulo"),rs.getInt("pegi"),rs.getString("plataforma"),rs.getInt("anio_lanzamiento"));
                lista.add(c);
            }
            conexion.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }
    public CopiaDTO busqueadaPorId(int id){
        String query="SELECT id, codigo_inventario, id_videojuego, id_plataforma, estado, activa FROM copias WHERE id = ? ";
        try{
            Connection conexion=dataSource.getConnection();
            PreparedStatement ps=conexion.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                CopiaDTO c= new CopiaDTO(rs.getInt("id"),rs.getString("codigo_inventario"),rs.getInt("id_videojuego"),rs.getInt("id_plataforma"),rs.getString("estado"),rs.getBoolean("activa"));
                return c;

            }
            conexion.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }
    //Regla de negocio 2
    public boolean estaDisponible(int idCopia) throws SQLException {
        String query = "SELECT 1 FROM copias c WHERE c.id = ? AND c.activa = 1 AND NOT EXISTS (SELECT 1 FROM prestamos p WHERE p.id_copia = c.id AND p.fecha_devolucion IS NULL )";
        Connection conexion=null;
        try {
            conexion = dataSource.getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idCopia);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            conexion.close();
            throw new RuntimeException(e);
        }
    }
    public void marcarDanada(Connection c, int idCopia){
        String query="UPDATE copias SET estado = 'DANADO', activa = 0 WHERE id = ? ";
        try{
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, idCopia);
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
