package org.UMG.principal.basedatos.dao;

import org.UMG.principal.basedatos.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void insertar(Usuario usuario);  // Método para insertar un usuario
    Usuario obtenerPorId(int id);    // Método para obtener un usuario por ID
    List<Usuario> obtenerTodos();    // Método para obtener todos los usuarios
    void actualizar(Usuario usuario);  // Método para actualizar un usuario
    void eliminar(int id);  // Método para eliminar un usuario
}
