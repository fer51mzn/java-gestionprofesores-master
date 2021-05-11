package com.piramide;

import com.piramide.dao.DAOFactory;
import com.piramide.entities.Modulo;
import com.piramide.entities.Profesor;
import com.piramide.gui.GUIProfesorAlta;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        GUIProfesorAlta guiProfesorAlta = new GUIProfesorAlta();
        guiProfesorAlta.setVisible(true);
    }

}
