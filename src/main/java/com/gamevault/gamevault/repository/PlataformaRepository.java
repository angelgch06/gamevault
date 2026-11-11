package com.gamevault.gamevault.repository;

import com.gamevault.gamevault.dto.DesarrolladoraDTO;
import com.gamevault.gamevault.dto.PlataformaDTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlataformaRepository {
    private final DataSource dataSource;

    public PlataformaRepository (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<PlataformaDTO> listarTodos(){//tratamos de que este metodo se llama igual en todos los repositories
        String query="SELECT id, nombre FROM plataformas ORDER BY nombre";
        List<PlataformaDTO> lista=new ArrayList<>();
        //hacemos conexion y ejecutamos la query
        //tiene que estar dentro de un try catch
        try{
            Connection conexion=dataSource.getConnection();
            PreparedStatement ps=conexion.prepareStatement(query);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){//mientras haya una fila procesa, eso es el next
                //definimos los datos
                PlataformaDTO d=new PlataformaDTO(rs.getInt("id"),rs.getString("nombre"));
                lista.add(d);
            }
            conexion.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

}
