package org.UMG.principal.basedatos.dao;

import org.UMG.principal.basedatos.model.Equipo;
import java.util.List;

public interface EquipoDAO {
    void insertar(Equipo equipo);
    Equipo obtenerPorId(int id);
    List<Equipo> obtenerTodos();
    void actualizar(Equipo equipo);
    void eliminar(int id);
}

