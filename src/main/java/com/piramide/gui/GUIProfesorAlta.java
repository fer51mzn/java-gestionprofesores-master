package com.piramide.gui;

import com.piramide.dao.DAOFactory;
import com.piramide.entities.Profesor;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUIProfesorAlta extends JFrame {
    private JTextField inputEmail;
    private JLabel infoEmail;
    private JTextField inputNombre;
    private JButton altaButton;
    private JPanel root;

    public GUIProfesorAlta(){
        setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(root);
        this.setHandlers();
        altaButton.setEnabled(false);
    }

    private void setHandlers(){
        altaButton.addActionListener(e->{
            buildProfesor();
        });
        inputEmail.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                checkProfesor();
            }
        });
    }

    private void buildProfesor(){
        String email = inputEmail.getText();
        String nombre = inputNombre.getText();
        Profesor profesor = new Profesor(email,nombre);
        Boolean created = DAOFactory.getInstance().getDAOProfesores().alta(profesor);
        if(created){
            JOptionPane.showMessageDialog(this,
                    "Profesor creado correctamente",
                    "Alta de profesor",
                    JOptionPane.PLAIN_MESSAGE);
            inputEmail.setText("");
            inputNombre.setText("");
            infoEmail.setVisible(false);
        }
        else {
            infoEmail.setVisible(true);
        }
    }

    private void checkProfesor(){
        String currentEmail = inputEmail.getText();
        if(currentEmail.length()>4) {
            Boolean exists = DAOFactory.getInstance().getDAOProfesores().exists(currentEmail);
            if (exists) {
                infoEmail.setVisible(true);
                altaButton.setEnabled(false);
            } else {
                infoEmail.setVisible(false);
                altaButton.setEnabled(true);
            }
        }
        else {
            infoEmail.setVisible(false);
            altaButton.setEnabled(false);
        }
    }
}
