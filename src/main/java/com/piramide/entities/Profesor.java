package com.piramide.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Profesor {

    private final String email, nombre;
    //modulo -> horas dedicadas
    private final HashMap<Modulo, Integer> modulos;

    public Profesor(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
        this.modulos = new HashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public void add(Modulo modulo, int horas){
        this.modulos.put(modulo, horas);
    }

}
