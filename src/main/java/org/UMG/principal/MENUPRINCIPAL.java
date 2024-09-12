package org.UMG.principal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MENUPRINCIPAL extends JFrame {

    private JButton FORMULARIO1Button;
    private JButton button2;
    private JButton button3;
    private JPanel FRMMENU;
    private JButton SALIRButton;
    private JLabel labelTitulo;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario1");
        frame.setContentPane(new MENUPRINCIPAL().FRMMENU);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400); // Ajusta el tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setVisible(true);
    }

    public MENUPRINCIPAL() {

        // Acción del botón FORMULARIO1Button
        FORMULARIO1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EJERCICIO1 abrir = new EJERCICIO1();
                abrir.setVisible(true); // Abre el formulario 1
                dispose(); // Cierra la ventana actual (MENUPRINCIPAL)
            }
        });

        // Acción del botón button2 para abrir EJERCICIO2
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EJERCICIO2 abrir2 = new EJERCICIO2();
                abrir2.setVisible(true); // Abre el formulario 2
                dispose(); // Cierra la ventana actual (MENUPRINCIPAL)
            }
        });

        // Acción del botón button3 para abrir EJERCICIO3
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EJERCICIO3 abrir3 = new EJERCICIO3();
                abrir3.setVisible(true); // Abre el formulario 3
                dispose(); // Cierra la ventana actual (MENUPRINCIPAL)
            }
        });

        // Acción del botón SALIR para cerrar el programa
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra el programa
            }
        });
    }
}
