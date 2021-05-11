package com.piramide.dao.profesores;

import com.piramide.db.DBConnection;
import com.piramide.entities.Ciclo;
import com.piramide.entities.Modulo;
import com.piramide.entities.Profesor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DAOProfesoresSQL implements DAOProfesores{


    @Override
    public Boolean exists(String email) {
        try{
            Statement statement = DBConnection.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select count(*) as numProfesores " +
                            "from profesores where email = '"+email+"'");
            while (resultSet.next()){
                int numProfesores = resultSet.getInt("numProfesores");
                if(numProfesores == 0){
                    return false;
                }
                else return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean alta(Profesor profesor) {
        try {
            Statement statement = DBConnection.getInstance().createStatement();
            statement.execute("" +
                    "insert into profesores (email,nombre) " +
                    "values ('"+profesor.getEmail()+"','"+profesor.getNombre()+"')");
        } catch (SQLException exception) {
            //SQLError duplicate PK
            if(exception.getErrorCode() == 1062){
                System.err.println("Ya existe un profesor con ese email");
            }
            else {
                System.err.println(exception.getMessage());
            }
            return false;
        }
        return true;
    }

    @Override
    public void asigna(Profesor profesor, Modulo modulo, int horas) {
        try {
            Statement statement = DBConnection.getInstance().createStatement();
            statement.execute("" +
                    "insert into imparte (profesor,modulo,ciclo,horas) " +
                    "values ('"+profesor.getEmail()+"','"+modulo.getNombre()+"'," +
                    "'"+modulo.getCiclo()+"','"+horas+"')");
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public List<Profesor> lista() {
       List<Profesor> profesores = new ArrayList<>();
        try{
            Statement statement = DBConnection.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery("" +
                    "select * from profesores p join imparte i on i.profesor = p.email");
            while (resultSet.next()){
                //datos del profesor
                String email = resultSet.getString("email");
                String nombre = resultSet.getString("nombre");
                //compruebo en la lista si existe un profesor con ese email
                Profesor profesor = null;
                Boolean existe = false;
                /*
                for (int i = 0; i < profesores.size(); i++) {
                    Profesor actual = profesores.get(i);
                    if(actual.getEmail().equals(email)){
                        profesor = actual;
                        existe = true;
                        break;
                    }
                }
                 */
                Optional<Profesor> posibleProfesor = profesores.stream().filter(actual->{
                    return actual.getEmail().equals(email);
                }).findFirst();
                if(posibleProfesor.isPresent()){
                    profesor = posibleProfesor.get();
                    existe = true;
                }
                if(profesor == null){
                    profesor = new Profesor(email,nombre);
                }
                //modulo
                String nombreModulo = resultSet.getString("modulo");
                String nombreCiclo = resultSet.getString("ciclo");
                int horas = resultSet.getInt("horas");
                Ciclo ciclo = null;
                ciclo = Ciclo.valueOf(nombreCiclo);
                Modulo modulo = new Modulo(nombreModulo,ciclo);
                profesor.add(modulo,horas);
                if(!existe) {
                    profesores.add(profesor);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return profesores;
    }

    @Override
    public List<Modulo> modulos(Profesor profesor) {
        List<Modulo> modulos = new ArrayList<>();
        try{
            Statement statement = DBConnection.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery("" +
                    "select modulo, ciclo " +
                    "from imparte " +
                    "where profesor = '"+profesor.getEmail()+"'");
            while (resultSet.next()){
                String nombreModulo = resultSet.getString("modulo");
                String nombreCiclo = resultSet.getString("ciclo");
                Ciclo ciclo = Ciclo.valueOf(nombreCiclo);
                Modulo modulo = new Modulo(nombreModulo,ciclo);
                modulos.add(modulo);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return modulos;
    }
}
