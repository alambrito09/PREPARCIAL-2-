package org.UMG.principal;

import org.UMG.principal.basedatos.dao.UsuarioDAOImpl;
import org.UMG.principal.basedatos.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EJERCICIO2 extends JFrame {

    private JPanel FRM3;
    private JTextField textFieldidusuario;
    private JTextField textFieldcarne;
    private JTextField textFieldnombre;  // Nuevo campo para el nombre
    private JTextField textFieldcorreo;
    private JTextField textFieldseccion;
    private JTextField textFieldtelegranid;
    private JTextField textFieldactivo;
    private JButton INGRESARButton;
    private JButton ACTUALIZARButton;
    private JButton ELIMINARButton;
    private JButton ANTERIORFRMButton;
    private JButton SIGUENTEFRMButton;
    private JButton BUSCAR;

    private UsuarioDAOImpl usuarioDAO;

    public EJERCICIO2() {
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo este formulario
        setLocationRelativeTo(null); // Centrar el formulario en pantalla

        usuarioDAO = new UsuarioDAOImpl(); // Inicializar el DAO para interactuar con la base de datos

        // Añadir el panel al contenedor principal
        setContentPane(FRM3);

        // Acción del botón INGRESAR
        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarUsuario();
            }
        });

        // Acción del botón ACTUALIZAR
        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        // Acción del botón ELIMINAR
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        // Acción del botón BUSCAR
        BUSCAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
        SIGUENTEFRMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EJERCICIO3 frm = new EJERCICIO3();
                frm.setVisible(true);
                dispose();
            }
        });
        ANTERIORFRMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EJERCICIO1 frm = new EJERCICIO1();
                frm.setVisible(true);
                dispose();
            }
        });
    }

    private void insertarUsuario() {
        // Obtener los valores de los campos de texto
        String carne = textFieldcarne.getText();
        String nombre = textFieldnombre.getText();  // Nuevo campo para el nombre
        String correo = textFieldcorreo.getText();
        String seccion = textFieldseccion.getText();
        long telegramId = Long.parseLong(textFieldtelegranid.getText());
        String activo = textFieldactivo.getText();

        // Validar si el correo ya existe
        if (esCorreoDuplicado(correo)) {
            JOptionPane.showMessageDialog(this, "El correo ya está registrado, por favor ingrese otro.");
            return;
        }

        // Crear un objeto Usuario con el nombre incluido
        Usuario usuario = new Usuario(0, carne, nombre, correo, seccion, telegramId, activo);

        // Insertar el usuario
        usuarioDAO.insertar(usuario);
        JOptionPane.showMessageDialog(this, "Usuario ingresado correctamente.");
    }

    private void actualizarUsuario() {
        // Obtener los valores de los campos de texto
        int idUsuario = Integer.parseInt(textFieldidusuario.getText());
        String carne = textFieldcarne.getText();
        String nombre = textFieldnombre.getText();  // Nuevo campo para el nombre
        String correo = textFieldcorreo.getText();
        String seccion = textFieldseccion.getText();
        long telegramId = Long.parseLong(textFieldtelegranid.getText());
        String activo = textFieldactivo.getText();

        // Crear un objeto Usuario con el nombre incluido
        Usuario usuario = new Usuario(idUsuario, carne, nombre, correo, seccion, telegramId, activo);

        // Actualizar el usuario
        usuarioDAO.actualizar(usuario);
        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
    }

    private void eliminarUsuario() {
        // Obtener el ID del usuario desde el campo de texto
        int idUsuario = Integer.parseInt(textFieldidusuario.getText());

        // Eliminar el usuario
        usuarioDAO.eliminar(idUsuario);
        JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
    }

    private void buscarUsuario() {
        // Obtener el ID del usuario a buscar
        int idUsuario = Integer.parseInt(textFieldidusuario.getText());

        // Obtener el usuario por ID
        Usuario usuario = usuarioDAO.obtenerPorId(idUsuario);

        if (usuario != null) {
            // Mostrar la información del usuario en los campos de texto
            textFieldcarne.setText(usuario.getCarne());
            textFieldnombre.setText(usuario.getNombre());  // Mostrar el nombre
            textFieldcorreo.setText(usuario.getCorreo());
            textFieldseccion.setText(usuario.getSeccion());
            textFieldtelegranid.setText(String.valueOf(usuario.getTelegramid()));
            textFieldactivo.setText(usuario.getActivo());
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
        }
    }

    private boolean esCorreoDuplicado(String correo) {
        // Obtener todos los usuarios
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();

        // Verificar si el correo ya existe en la lista de usuarios
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return true; // Correo duplicado
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario3");
        frame.setContentPane(new EJERCICIO2().FRM3);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setVisible(true);
    }
}
