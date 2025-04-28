package com.epf.service.impl;

import com.epf.dao.PlanteDAO;
import com.epf.dto.PlanteDTO;
import com.epf.model.Plante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

//Tests unitaires de PlanteServiceImpl
class PlanteServiceImplTest {

    private PlanteDAO planteDAO;
    private PlanteServiceImpl planteService;

    @BeforeEach
    void setUp() {
        planteDAO = mock(PlanteDAO.class);
        planteService = new PlanteServiceImpl(planteDAO);
    }

    @Test
    void testGetAllPlantes() {
        List<Plante> plantes = Arrays.asList(
                new Plante(1L, "Tournesol", 100, 1, 0, 50, 25, "effet1", "chemin1.png"),
                new Plante(2L, "Pisto-pois", 300, 2, 20, 100, 0, "effet2", "chemin2.png")
        );
        when(planteDAO.getAllPlantes()).thenReturn(plantes);

        List<PlanteDTO> result = planteService.getAllPlantes();

        assertEquals(2, result.size());
        assertEquals("Tournesol", result.get(0).getNom());
        verify(planteDAO, times(1)).getAllPlantes();
    }

    @Test
    void testGetPlanteById() {
        Plante plante = new Plante(1L, "Tournesol", 100, 1, 0, 50, 25, "effet", "chemin.png");
        when(planteDAO.getPlanteById(1L)).thenReturn(plante);

        PlanteDTO result = planteService.getPlanteById(1L);

        assertNotNull(result);
        assertEquals("Tournesol", result.getNom());
        verify(planteDAO, times(1)).getPlanteById(1L);
    }

    @Test
    void testCreatePlante() {
        PlanteDTO planteDTO = new PlanteDTO(1L, "Tournesol", 100, 1, 0, 50, 25, "effet", "chemin.png");

        planteService.createPlante(planteDTO);

        verify(planteDAO, times(1)).createPlante(any(Plante.class));
    }

    @Test
    void testUpdatePlante() {
        PlanteDTO planteDTO = new PlanteDTO(1L, "Tournesol", 100, 1, 0, 50, 25, "effet", "chemin.png");

        planteService.updatePlante(1L, planteDTO);

        verify(planteDAO, times(1)).updatePlante(any(Plante.class));
    }

    @Test
    void testDeletePlante() {
        planteService.deletePlante(1L);

        verify(planteDAO, times(1)).deletePlante(1L);
    }
}
