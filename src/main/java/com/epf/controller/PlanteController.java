package com.epf.controller;

import com.epf.dto.PlanteDTO;
import com.epf.service.PlanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantes")
public class PlanteController {

    private final PlanteService planteService;

    @Autowired
    public PlanteController(PlanteService planteService) {
        this.planteService = planteService;
    }
    // Récupérer toutes les plantes
    @GetMapping
    public List<PlanteDTO> getAllPlantes() {
        return planteService.getAllPlantes();
    }
    // Récupérer une plante par ID
    @GetMapping("/{id_plante}")
    public PlanteDTO getPlanteById(@PathVariable long id_plante) {
        return planteService.getPlanteById(id_plante);
    }

    //Créer une nouvelle plante
    @PostMapping
    public void createPlante(@RequestBody PlanteDTO planteDTO) {
        planteService.createPlante(planteDTO);
    }

    //Maj plante
    @PutMapping("/{id_plante}")
    public void updatePlante(@PathVariable long id_plante, @RequestBody PlanteDTO planteDTO) {
        planteService.updatePlante(id_plante, planteDTO);
    }

    //Supprimer une plante
    @DeleteMapping("/{id_plante}")
    public void deletePlante(@PathVariable long id_plante) {
        planteService.deletePlante(id_plante);
    }
}
