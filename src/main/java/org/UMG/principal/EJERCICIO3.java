package org.UMG.principal;

import org.UMG.principal.basedatos.dao.EquipoDAOImpl;
import org.UMG.principal.basedatos.model.Equipo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EJERCICIO3 extends JFrame {
    private JPanel FRM4;
    private JTextField textFieldidequipo;
    private JTextField textFieldnombre;
    private JTextField textFieldpais;
    private JTextField textFieldciudad;
    private JTextField textFieldestadio;
    private JTextField textFieldfundacion;
    private JTextField textFieldentrenador;
    private JTextField textFieldweboficial;
    private JTextField textFieldfacebook;
    private JTextField textFieldtwitter;
    private JTextField textFieldinstagram;
    private JTextField textFieldpatrocinadorprincipal;
    private JTextField textFieldcreadoen;
    private JButton INGRESAR;
    private JButton ACTUALIZAR;
    private JButton ELIMINAR;
    private JButton BUSCAR;
    private JButton ANTERIORFRM;

    private EquipoDAOImpl equipoDAO = new EquipoDAOImpl(); // Instancia de EquipoDAOImpl

    public EJERCICIO3() {
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo este formulario
        setLocationRelativeTo(null); // Centrar el formulario en pantalla

        // Añadir el panel al contenedor principal
        setContentPane(FRM4);

        // Acción del botón INGRESAR
        INGRESAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarEquipo();
            }
        });

        // Acción del botón ACTUALIZAR
        ACTUALIZAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEquipo();
            }
        });

        // Acción del botón ELIMINAR
        ELIMINAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEquipo();
            }
        });

        // Acción del botón BUSCAR
        BUSCAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEquipo();
            }
        });
        ANTERIORFRM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EJERCICIO2 nuevoEquipo = new EJERCICIO2();

                nuevoEquipo.setVisible(true);
                dispose();

            }
        });
    }

    private void insertarEquipo() {
        try {
            // Obtener los valores de los campos de texto
            String nombre = textFieldnombre.getText();
            String pais = textFieldpais.getText();
            String ciudad = textFieldciudad.getText();
            String estadio = textFieldestadio.getText();
            int fundacion = Integer.parseInt(textFieldfundacion.getText());
            String entrenador = textFieldentrenador.getText();
            String webOficial = textFieldweboficial.getText();
            String facebook = textFieldfacebook.getText();
            String twitter = textFieldtwitter.getText();
            String instagram = textFieldinstagram.getText();
            String patrocinador = textFieldpatrocinadorprincipal.getText();

            // Crear un objeto Equipo
            Equipo equipo = new Equipo(0, nombre, pais, ciudad, estadio, fundacion, entrenador, webOficial, facebook, twitter, instagram, patrocinador, null);

            // Insertar el equipo en la base de datos
            equipoDAO.insertar(equipo);
            JOptionPane.showMessageDialog(this, "Equipo ingresado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al insertar equipo: " + ex.getMessage());
        }
    }

    private void actualizarEquipo() {
        try {
            // Obtener los valores de los campos de texto
            int idEquipo = Integer.parseInt(textFieldidequipo.getText());
            String nombre = textFieldnombre.getText();
            String pais = textFieldpais.getText();
            String ciudad = textFieldciudad.getText();
            String estadio = textFieldestadio.getText();
            int fundacion = Integer.parseInt(textFieldfundacion.getText());
            String entrenador = textFieldentrenador.getText();
            String webOficial = textFieldweboficial.getText();
            String facebook = textFieldfacebook.getText();
            String twitter = textFieldtwitter.getText();
            String instagram = textFieldinstagram.getText();
            String patrocinador = textFieldpatrocinadorprincipal.getText();

            // Crear un objeto Equipo con los nuevos datos
            Equipo equipo = new Equipo(idEquipo, nombre, pais, ciudad, estadio, fundacion, entrenador, webOficial, facebook, twitter, instagram, patrocinador, null);

            // Actualizar el equipo en la base de datos
            equipoDAO.actualizar(equipo);
            JOptionPane.showMessageDialog(this, "Equipo actualizado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar equipo: " + ex.getMessage());
        }
    }

    private void eliminarEquipo() {
        try {
            // Obtener el ID del equipo a eliminar
            int idEquipo = Integer.parseInt(textFieldidequipo.getText());

            // Eliminar el equipo
            equipoDAO.eliminar(idEquipo);
            JOptionPane.showMessageDialog(this, "Equipo eliminado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar equipo: " + ex.getMessage());
        }
    }

    private void buscarEquipo() {
        try {
            // Obtener el ID del equipo a buscar
            int idEquipo = Integer.parseInt(textFieldidequipo.getText());

            // Buscar el equipo por su ID
            Equipo equipo = equipoDAO.obtenerPorId(idEquipo);

            if (equipo != null) {
                // Mostrar los datos en los campos de texto
                textFieldnombre.setText(equipo.getNombre());
                textFieldpais.setText(equipo.getPais());
                textFieldciudad.setText(equipo.getCiudad());
                textFieldestadio.setText(equipo.getEstadio());
                textFieldfundacion.setText(String.valueOf(equipo.getFundacion()));
                textFieldentrenador.setText(equipo.getEntrenador());
                textFieldweboficial.setText(equipo.getWebOficial());
                textFieldfacebook.setText(equipo.getFacebook());
                textFieldtwitter.setText(equipo.getTwitter());
                textFieldinstagram.setText(equipo.getInstagram());
                textFieldpatrocinadorprincipal.setText(equipo.getPatrocinadorPrincipal());
                textFieldcreadoen.setText(equipo.getCreadoEn().toString());
            } else {
                JOptionPane.showMessageDialog(this, "Equipo no encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar equipo: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario4");
        frame.setContentPane(new EJERCICIO3().FRM4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
