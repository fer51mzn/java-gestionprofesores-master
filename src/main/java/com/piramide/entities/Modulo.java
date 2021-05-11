package com.piramide.entities;

import java.util.ArrayList;
import java.util.List;

public class Modulo {

    private final String nombre;
    private final Ciclo ciclo;
    private final List<Profesor> profesores;

    public Modulo(String nombre, Ciclo ciclo) {
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.profesores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void add(Profesor profesor){
        this.profesores.add(profesor);
    }

    public void add(List<Profesor> profesores){
        this.profesores.addAll(profesores);
    }
}
