package org.UMG.principal.basedatos.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_telebot"; // Cambia "tu_base_de_datos" por el nombre de tu base de datos
    private static final String USER = "root"; // Cambia "tu_usuario" por tu usuario de MySQL
    private static final String PASSWORD = "root"; // Cambia "tu_contraseña" por la contraseña de tu base de datos

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() {
        Connection connection = null;

        try {
            // Registrar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Obtener la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos.");
            e.printStackTrace();
        }

        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}


