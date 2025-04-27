package com.epf.service;

import com.epf.dto.PlanteDTO;
import java.util.List;

//Service de plante
public interface PlanteService {
    List<PlanteDTO> getAllPlantes();
    PlanteDTO getPlanteById(long id_plante);
    void createPlante(PlanteDTO planteDTO);
    void updatePlante(long id_plante, PlanteDTO planteDTO);
    void deletePlante(long id_plante);
}
