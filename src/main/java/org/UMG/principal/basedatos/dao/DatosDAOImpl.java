package org.UMG.principal.basedatos.dao;


import org.UMG.principal.basedatos.conexion.DatabaseConnection;
import org.UMG.principal.basedatos.model.Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatosDAOImpl implements DatosDAO {

    @Override
    public void insertar(Datos datos) {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, datos.getNombre());
            stmt.setString(2, datos.getApellido());
            stmt.setString(3, datos.getDepartamento());
            stmt.setDate(4, new java.sql.Date(datos.getFechaNacimiento().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Datos obtenerPorCodigo(int codigo) {
        String sql = "SELECT * FROM tb_datos WHERE codigo = ?";
        Datos datos = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                datos = new Datos();
                datos.setCodigo(rs.getInt("codigo"));
                datos.setNombre(rs.getString("nombre"));
                datos.setApellido(rs.getString("apellido"));
                datos.setDepartamento(rs.getString("departamento"));
                datos.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }

    @Override
    public List<Datos> obtenerTodos() {
        String sql = "SELECT * FROM tb_datos";
        List<Datos> listaDatos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Datos datos = new Datos();
                datos.setCodigo(rs.getInt("codigo"));
                datos.setNombre(rs.getString("nombre"));
                datos.setApellido(rs.getString("apellido"));
                datos.setDepartamento(rs.getString("departamento"));
                datos.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                listaDatos.add(datos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDatos;
    }

    @Override
    public void actualizar(Datos datos) {
        String sql = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, datos.getNombre());
            stmt.setString(2, datos.getApellido());
            stmt.setString(3, datos.getDepartamento());
            stmt.setDate(4, new java.sql.Date(datos.getFechaNacimiento().getTime()));
            stmt.setInt(5, datos.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int codigo) {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

