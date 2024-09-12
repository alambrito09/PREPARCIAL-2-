package org.UMG.principal.basedatos.dao;

import org.UMG.principal.basedatos.conexion.DatabaseConnection;
import org.UMG.principal.basedatos.model.Equipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAOImpl implements EquipoDAO {

    @Override
    public void insertar(Equipo equipo) {
        String sql = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getPais());
            stmt.setString(3, equipo.getCiudad());
            stmt.setString(4, equipo.getEstadio());
            stmt.setInt(5, equipo.getFundacion());
            stmt.setString(6, equipo.getEntrenador());
            stmt.setString(7, equipo.getWebOficial());
            stmt.setString(8, equipo.getFacebook());
            stmt.setString(9, equipo.getTwitter());
            stmt.setString(10, equipo.getInstagram());
            stmt.setString(11, equipo.getPatrocinadorPrincipal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Equipo obtenerPorId(int id) {
        String sql = "SELECT * FROM equipos_champions WHERE id_equipo = ?";
        Equipo equipo = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                equipo = new Equipo();
                equipo.setIdEquipo(rs.getInt("id_equipo"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setPais(rs.getString("pais"));
                equipo.setCiudad(rs.getString("ciudad"));
                equipo.setEstadio(rs.getString("estadio"));
                equipo.setFundacion(rs.getInt("fundacion"));
                equipo.setEntrenador(rs.getString("entrenador"));
                equipo.setWebOficial(rs.getString("web_oficial"));
                equipo.setFacebook(rs.getString("facebook"));
                equipo.setTwitter(rs.getString("twitter"));
                equipo.setInstagram(rs.getString("instagram"));
                equipo.setPatrocinadorPrincipal(rs.getString("patrocinador_principal"));
                equipo.setCreadoEn(rs.getTimestamp("creado_en"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipo;
    }

    @Override
    public List<Equipo> obtenerTodos() {
        String sql = "SELECT * FROM equipos_champions";
        List<Equipo> listaEquipos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIdEquipo(rs.getInt("id_equipo"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setPais(rs.getString("pais"));
                equipo.setCiudad(rs.getString("ciudad"));
                equipo.setEstadio(rs.getString("estadio"));
                equipo.setFundacion(rs.getInt("fundacion"));
                equipo.setEntrenador(rs.getString("entrenador"));
                equipo.setWebOficial(rs.getString("web_oficial"));
                equipo.setFacebook(rs.getString("facebook"));
                equipo.setTwitter(rs.getString("twitter"));
                equipo.setInstagram(rs.getString("instagram"));
                equipo.setPatrocinadorPrincipal(rs.getString("patrocinador_principal"));
                equipo.setCreadoEn(rs.getTimestamp("creado_en"));
                listaEquipos.add(equipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEquipos;
    }

    @Override
    public void actualizar(Equipo equipo) {
        String sql = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? WHERE id_equipo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getPais());
            stmt.setString(3, equipo.getCiudad());
            stmt.setString(4, equipo.getEstadio());
            stmt.setInt(5, equipo.getFundacion());
            stmt.setString(6, equipo.getEntrenador());
            stmt.setString(7, equipo.getWebOficial());
            stmt.setString(8, equipo.getFacebook());
            stmt.setString(9, equipo.getTwitter());
            stmt.setString(10, equipo.getInstagram());
            stmt.setString(11, equipo.getPatrocinadorPrincipal());
            stmt.setInt(12, equipo.getIdEquipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM equipos_champions WHERE id_equipo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
