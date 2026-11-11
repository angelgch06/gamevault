package com.gamevault.gamevault.repository;

import com.gamevault.gamevault.dto.DesarrolladoraDTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DesarrolladoraRepository {
    private final DataSource dataSource;

    public DesarrolladoraRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }//recibo la fuente y digo cual es la fuente de mi dato

    public List<DesarrolladoraDTO> listarTodos(){
        //primero el SQL
        //creamos la sentencia para buscar en mi base de datos
        String query="SELECT id, nombre, pais FROM desarrolladoras ORDER BY nombre";
        List<DesarrolladoraDTO> lista=new ArrayList<>();
        //hacemos conexion y ejecutamos la query
        //tiene que estar dentro de un try catch
        try{
            //linea que conecta con la base de datos
            Connection conexion=dataSource.getConnection();
            //para ejecutarlo en la base de datos tenemos que meterlo en un objeto que permita la ejecucuion de la query
            PreparedStatement ps=conexion.prepareStatement(query);
            //pedimos que lo ejecute
            ResultSet rs= ps.executeQuery();
            //creamos un objeto para manejar cada dato, por cada fila creamos un objeto desarrolladora y meto columna con atributo
            while(rs.next()){//mientras haya una fila procesa, eso es el next
                //creamos el objeto desarrolladora
                //definimos los datos
                DesarrolladoraDTO d=new DesarrolladoraDTO(rs.getInt("id"),rs.getString("nombre"),rs.getString("pais"));
                lista.add(d);
            }
            conexion.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

}
