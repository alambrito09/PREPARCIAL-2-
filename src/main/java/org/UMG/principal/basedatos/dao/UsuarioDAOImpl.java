package org.UMG.principal.basedatos.dao;

import org.UMG.principal.basedatos.conexion.DatabaseConnection;
import org.UMG.principal.basedatos.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void insertar(Usuario usuario) {
        String sql = "INSERT INTO tb_usuarios (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCarne());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getSeccion());
            stmt.setLong(5, usuario.getTelegramid());
            stmt.setString(6, usuario.getActivo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario obtenerPorId(int id) {
        String sql = "SELECT * FROM tb_usuarios WHERE idusuario = ?";
        Usuario usuario = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setCarne(rs.getString("carne"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setSeccion(rs.getString("seccion"));
                usuario.setTelegramid(rs.getLong("telegramid"));
                usuario.setActivo(rs.getString("activo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        String sql = "SELECT * FROM tb_usuarios";
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setCarne(rs.getString("carne"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setSeccion(rs.getString("seccion"));
                usuario.setTelegramid(rs.getLong("telegramid"));
                usuario.setActivo(rs.getString("activo"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }

    @Override
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCarne());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getSeccion());
            stmt.setLong(5, usuario.getTelegramid());
            stmt.setString(6, usuario.getActivo());
            stmt.setInt(7, usuario.getIdusuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tb_usuarios WHERE idusuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
