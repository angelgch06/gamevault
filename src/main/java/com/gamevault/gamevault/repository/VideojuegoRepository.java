package com.gamevault.gamevault.repository;

import com.gamevault.gamevault.dto.FiltroVideojuegosDTO;
import com.gamevault.gamevault.dto.VideojuegoDTO;
import com.gamevault.gamevault.dto.VideojuegoDetalleDTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VideojuegoRepository {
    private final DataSource dataSource;

    public VideojuegoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<VideojuegoDetalleDTO> buscar(FiltroVideojuegosDTO filtro) {
        StringBuilder query = new StringBuilder("SELECT id, titulo, anio_lanzamiento, pegi, id_desarrolladora, desarrolladora,copias_totales,copias_disponibles FROM v_videojuegos_detalle WHERE 1=1 ");
        //WHERE 1=1 es para decirle que concatene querys
        List<Object> parametros = new ArrayList<>();
        if (filtro.getTitulo() != null && !filtro.getTitulo().isBlank()) {
            query.append("AND titulo LIKE ? ");
            parametros.add("%" + filtro.getTitulo().trim() + "%");
        }
        if (filtro.getIdDesarrolladora() != null) {
            query.append("AND id_desarrolladora = ? ");
            parametros.add(filtro.getIdDesarrolladora());
        }
        if (filtro.getAnioDesde() != null) {
            query.append("AND anio_lanzamiento >= ? ");
            parametros.add(filtro.getAnioDesde());
        }
        if (filtro.getPegiMax() != null) {
            query.append("AND pegi <= ? ");
            parametros.add(filtro.getPegiMax());
        }
        if (filtro.getSoloDisponibles()) {
            query.append("AND copias_disponibles > 0 ");
        }
        query.append("ORDER BY titulo");

        List<VideojuegoDetalleDTO> lista = new ArrayList<>();
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement(query.toString());
            //este for sustituye cualquier signo de interrogacion que tengamos ahi
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VideojuegoDetalleDTO d = new VideojuegoDetalleDTO(rs.getInt("id"), rs.getString("titulo"), rs.getInt("anio_lanzamiento"), rs.getInt("pegi"), rs.getInt("id_desarrolladora"), rs.getString("desarrolladora"), rs.getInt("copias_totales"), rs.getInt("copias_disponibles"));
                lista.add(d);
            }
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public int insertar(VideojuegoDTO videojuego) {
        //ponemos un query que permita insertar en la base de datos, lo que nos dice el entero que retorna es si lo hizo o no lo hizo+
        String query = "INSERT INTO videojuegos (titulo,anio_lanzamiento,pegi,id_desarrolladora) VALUES (?,?,?,?)";
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //el segundo parametro en el ps consiste en que escucha el query el query coge una clave y pide esa clave para verificar que se insertó
            ps.setString(1, videojuego.getTitulo());
            ps.setInt(2, videojuego.getAnioLanzamiento());
            ps.setInt(3, videojuego.getPegi());
            ps.setInt(4, videojuego.getIdDesarrolladora());
            //execute query se utiliza cuando uso SELECT, para modificación se usa executeUpdate
            ps.executeUpdate();
            ResultSet clave = ps.getGeneratedKeys();
            if (clave.next()) {
                //el 1 significa la posicion 1 en esa columna
                return clave.getInt(1);
            }
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //si devuelve -1 no insertó nada
        return -1;
    }

    public VideojuegoDetalleDTO buscarDetallePorId(int id) {
        String query = "SELECT id, titulo, anio_lanzamiento, pegi, id_desarrolladora, desarrolladora,copias_totales,copias_disponibles FROM v_videojuegos_detalle WHERE id = ? ";
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            //el primer y unico signo de interrogacion que tenemos al final es sustituido por el id
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                VideojuegoDetalleDTO d = new VideojuegoDetalleDTO(rs.getInt("id"), rs.getString("titulo"), rs.getInt("anio_lanzamiento"), rs.getInt("pegi"), rs.getInt("id_desarrolladora"), rs.getString("desarrolladora"), rs.getInt("copias_totales"), rs.getInt("copias_disponibles"));
                return d;
            }
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return null;
    }
}
