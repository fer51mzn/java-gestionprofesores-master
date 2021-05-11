package com.piramide.dao;

import com.piramide.dao.profesores.DAOProfesores;
import com.piramide.dao.profesores.DAOProfesoresSQL;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOProfesores daoProfesores;

    private DAOFactory(){}

    public static DAOFactory getInstance() {
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public DAOProfesores getDAOProfesores() {
        if(this.daoProfesores == null){
            daoProfesores = new DAOProfesoresSQL();
        }
        return daoProfesores;
    }
}
