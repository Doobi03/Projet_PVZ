package com.epf.dao;

import com.epf.model.Plante;
import java.util.List;

public interface PlanteDAO {

    // CRUD de base
    List<Plante> getAllPlantes();
    Plante getPlanteById(long id_plante);
    void createPlante(Plante plante);
    void updatePlante(Plante plante);
    void deletePlante(long id_plante);
}

