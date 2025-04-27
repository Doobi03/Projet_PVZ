package com.epf.service.impl;

import com.epf.dao.PlanteDAO;
import com.epf.dto.PlanteDTO;
import com.epf.model.Plante;
import com.epf.service.PlanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Service Impl de Plante, lien DTO <-> Model
@Service
public class PlanteServiceImpl implements PlanteService {

    private final PlanteDAO planteDAO;

    @Autowired
    public PlanteServiceImpl(PlanteDAO planteDAO) {
        this.planteDAO = planteDAO;
    }
    //Conversion DTO-Model
    private PlanteDTO convertToDTO(Plante plante) {
        return new PlanteDTO(
                plante.getId_plante(),
                plante.getNom(),
                plante.getPoint_de_vie(),
                plante.getAttaque_par_seconde(),
                plante.getDegat_attaque(),
                plante.getCout(),
                plante.getSoleil_par_seconde(),
                plante.getEffet(),
                plante.getChemin_image()
        );
    }

    private Plante convertToModel(PlanteDTO planteDTO) {
        return new Plante(
                planteDTO.getId_plante(),
                planteDTO.getNom(),
                planteDTO.getPoint_de_vie(),
                planteDTO.getAttaque_par_seconde(),
                planteDTO.getDegat_attaque(),
                planteDTO.getCout(),
                planteDTO.getSoleil_par_seconde(),
                planteDTO.getEffet(),
                planteDTO.getChemin_image()
        );
    }

    //Méthodes DAO
    @Override
    public List<PlanteDTO> getAllPlantes() {
        return planteDAO.getAllPlantes()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlanteDTO getPlanteById(long id_plante) {
        return convertToDTO(planteDAO.getPlanteById(id_plante));
    }

    @Override
    public void createPlante(PlanteDTO planteDTO) {
        planteDAO.createPlante(convertToModel(planteDTO));
    }

    @Override
    public void updatePlante(long id_plante, PlanteDTO planteDTO) {
        Plante plante = convertToModel(planteDTO);
        plante.setId_plante(id_plante);
        planteDAO.updatePlante(plante);
    }

    @Override
    public void deletePlante(long id_plante) {
        planteDAO.deletePlante(id_plante);
    }
}
