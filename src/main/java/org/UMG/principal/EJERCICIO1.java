package org.UMG.principal;

import org.UMG.principal.basedatos.dao.DatosDAOImpl;
import org.UMG.principal.basedatos.model.Datos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EJERCICIO1 extends JFrame {
    private JTextField textFieldcodigo;
    private JTextField textFieldnombre;
    private JTextField textFielddepartamento;
    private JTextField textFieldfecha;
    private JTextField textFieldapellido;
    private JButton SIGUENTEFRM;
    private JButton ANTERIORFRM;
    private JButton ELIMINAR;
    private JButton ACTUALIZAR;
    private JButton INGRESAR;
    private JButton BUSCAR;  // Añadir el botón de BUSCAR
    private JPanel FRM2;
    private JPanel formulario1;

    private DatosDAOImpl datosDAO = new DatosDAOImpl(); // Instancia del DAO para interactuar con la base de datos

    public EJERCICIO1() {
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo este formulario
        setLocationRelativeTo(null); // Centrar el formulario en pantalla

        // Añadir el panel al contenedor principal
        setContentPane(FRM2);

        // Acción del botón INGRESAR
        INGRESAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarDatos();
            }
        });

        // Acción del botón ACTUALIZAR
        ACTUALIZAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDatos();
            }
        });

        // Acción del botón ELIMINAR
        ELIMINAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDatos();
            }
        });

        // Acción del botón BUSCAR
        BUSCAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarDatos();
            }
        });

        // Acción del botón SIGUENTEFRM
        SIGUENTEFRM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EJERCICIO2 nuevoFormulario = new EJERCICIO2();
                nuevoFormulario.setVisible(true);
            }
        });

        // Acción del botón ANTERIORFRM
        ANTERIORFRM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // Método para insertar datos
    private void insertarDatos() {
        try {
            String nombre = textFieldnombre.getText();
            String apellido = textFieldapellido.getText();
            String departamento = textFielddepartamento.getText();
            Date fechaNacimiento = new SimpleDateFormat("dd-MM-yyyy").parse(textFieldfecha.getText());

            Datos datos = new Datos();
            datos.setNombre(nombre);
            datos.setApellido(apellido);
            datos.setDepartamento(departamento);
            datos.setFechaNacimiento(fechaNacimiento);

            datosDAO.insertar(datos);
            JOptionPane.showMessageDialog(this, "Datos insertados correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
        }
    }

    // Método para actualizar datos
    private void actualizarDatos() {
        try {
            int codigo = Integer.parseInt(textFieldcodigo.getText());
            String nombre = textFieldnombre.getText();
            String apellido = textFieldapellido.getText();
            String departamento = textFielddepartamento.getText();
            Date fechaNacimiento = new SimpleDateFormat("dd-MM-yyyy").parse(textFieldfecha.getText());

            Datos datos = new Datos();
            datos.setCodigo(codigo);
            datos.setNombre(nombre);
            datos.setApellido(apellido);
            datos.setDepartamento(departamento);
            datos.setFechaNacimiento(fechaNacimiento);

            datosDAO.actualizar(datos);
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar datos: " + ex.getMessage());
        }
    }

    // Método para eliminar datos
    private void eliminarDatos() {
        try {
            int codigo = Integer.parseInt(textFieldcodigo.getText());
            datosDAO.eliminar(codigo);
            JOptionPane.showMessageDialog(this, "Datos eliminados correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar datos: " + ex.getMessage());
        }
    }

    // Método para buscar datos por código
    private void buscarDatos() {
        try {
            int codigo = Integer.parseInt(textFieldcodigo.getText());

            // Obtener los datos por el código
            Datos datos = datosDAO.obtenerPorCodigo(codigo);

            if (datos != null) {
                // Mostrar la información en los campos de texto
                textFieldnombre.setText(datos.getNombre());
                textFieldapellido.setText(datos.getApellido());
                textFielddepartamento.setText(datos.getDepartamento());
                textFieldfecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(datos.getFechaNacimiento()));
            } else {
                JOptionPane.showMessageDialog(this, "Datos no encontrados.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar datos: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        EJERCICIO1 frame = new EJERCICIO1();
        frame.pack();
        frame.setVisible(true);
    }
}
