package com.piramide.dao.modulos;

import com.piramide.entities.Modulo;
import com.piramide.entities.Profesor;

import java.util.HashMap;
import java.util.List;

public interface DAOModulos {

    public void alta(Modulo modulo);
    public HashMap<Modulo, List<Profesor>> detalle();
    public List<Profesor> profesores(Modulo modulo);

}
