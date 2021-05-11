package com.piramide.dao.profesores;

import com.piramide.entities.Modulo;
import com.piramide.entities.Profesor;

import java.util.HashMap;
import java.util.List;

public interface DAOProfesores {

    public Boolean exists(String email);
    public Boolean alta(Profesor profesor);
    public void asigna(Profesor profesor, Modulo modulo, int horas);
    public List<Profesor> lista();
    public List<Modulo> modulos(Profesor profesor);

}
