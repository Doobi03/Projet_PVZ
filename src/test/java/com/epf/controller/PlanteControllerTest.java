package com.epf.controller;

import com.epf.dto.PlanteDTO;
import com.epf.service.PlanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Tests unitaires de PlanteController
class PlanteControllerTest {

    @Mock
    private PlanteService planteService;

    @InjectMocks
    private PlanteController planteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPlantes() {
        PlanteDTO plante1 = new PlanteDTO(
                1L, "Tournesol", 100, 0.0, 0, 50, 25.0, "Produit du soleil", "chemin1.png"
        );
        PlanteDTO plante2 = new PlanteDTO(
                2L, "Pisto-pois", 150, 1.5, 20, 100, 0.0, "Attaque", "chemin2.png"
        );
        List<PlanteDTO> mockPlantes = Arrays.asList(plante1, plante2);

        when(planteService.getAllPlantes()).thenReturn(mockPlantes);

        List<PlanteDTO> result = planteController.getAllPlantes();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(planteService, times(1)).getAllPlantes();
    }

    @Test
    void testGetPlanteById() {
        PlanteDTO plante = new PlanteDTO(
                1L, "Noix", 300, 0.0, 0, 50, 0.0, "Bloque les zombies", "chemin_noix.png"
        );

        when(planteService.getPlanteById(1L)).thenReturn(plante);

        PlanteDTO result = planteController.getPlanteById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId_plante());
        assertEquals("Noix", result.getNom());
        assertEquals(300, result.getPoint_de_vie());
        assertEquals(0.0, result.getAttaque_par_seconde());
        assertEquals(0, result.getDegat_attaque());
        assertEquals(50, result.getCout());
        assertEquals(0.0, result.getSoleil_par_seconde());
        assertEquals("Bloque les zombies", result.getEffet());
        assertEquals("chemin_noix.png", result.getChemin_image());
        verify(planteService, times(1)).getPlanteById(1L);
    }

    @Test
    void testCreatePlante() {
        PlanteDTO plante = new PlanteDTO(
                0L, "Champignon", 50, 1.0, 15, 25, 0.0, "Attaque la nuit", "chemin_champi.png"
        );

        planteController.createPlante(plante);

        verify(planteService, times(1)).createPlante(plante);
    }

    @Test
    void testUpdatePlante() {
        PlanteDTO plante = new PlanteDTO(
                1L, "Pisto-pois Gelé", 150, 1.5, 20, 175, 0.0, "Ralenti les zombies", "chemin_gel.png"
        );

        planteController.updatePlante(1L, plante);

        verify(planteService, times(1)).updatePlante(1L, plante);
    }

    @Test
    void testDeletePlante() {
        planteController.deletePlante(1L);

        verify(planteService, times(1)).deletePlante(1L);
    }
}
