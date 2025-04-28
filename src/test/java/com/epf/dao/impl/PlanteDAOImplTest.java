package com.epf.dao.impl;

import com.epf.model.Plante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Tests unitaires de PlanteDAOImpl
class PlanteDAOImplTest {

    private JdbcTemplate jdbcTemplate;
    private PlanteDAOImpl planteDAO;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        planteDAO = new PlanteDAOImpl(jdbcTemplate);
    }

    @Test
    void testGetAllPlantes() {
        Plante plante1 = new Plante(1L, "Tournesol", 100, 0.0, 0, 50, 25.0, null, "tournesol.png");
        Plante plante2 = new Plante(2L, "Pisto-pois", 100, 1.5, 20, 100, 0.0, null, "pisto-pois.png");
        List<Plante> expectedPlantes = Arrays.asList(plante1, plante2);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedPlantes);

        List<Plante> actualPlantes = planteDAO.getAllPlantes();

        assertEquals(expectedPlantes.size(), actualPlantes.size());
        assertEquals(expectedPlantes.get(0).getNom(), actualPlantes.get(0).getNom());
        assertEquals(expectedPlantes.get(1).getNom(), actualPlantes.get(1).getNom());

        verify(jdbcTemplate, times(1)).query(eq("SELECT * FROM plante"), any(RowMapper.class));
    }

    @Test
    void testGetPlanteById() {
        Plante plante = new Plante(1L, "Tournesol", 100, 0.0, 0, 50, 25.0, null, "tournesol.png");

        when(jdbcTemplate.queryForObject(eq("SELECT * FROM plante WHERE id_plante = ?"), any(RowMapper.class), eq(1L)))
                .thenReturn(plante);

        Plante actualPlante = planteDAO.getPlanteById(1L);

        assertNotNull(actualPlante);
        assertEquals("Tournesol", actualPlante.getNom());
        verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM plante WHERE id_plante = ?"), any(RowMapper.class), eq(1L));
    }

    @Test
    void testCreatePlante() {
        Plante plante = new Plante(0L, "Noix", 3000, 0.0, 0, 50, 0.0, null, "noix.png");

        planteDAO.createPlante(plante);

        verify(jdbcTemplate, times(1)).update(
                eq("INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
                eq(plante.getNom()),
                eq(plante.getPoint_de_vie()),
                eq(plante.getAttaque_par_seconde()),
                eq(plante.getDegat_attaque()),
                eq(plante.getCout()),
                eq(plante.getSoleil_par_seconde()),
                eq(plante.getEffet()),
                eq(plante.getChemin_image())
        );
    }

    @Test
    void testUpdatePlante() {
        Plante plante = new Plante(1L, "Pisto-pois amélioré", 120, 2.0, 40, 125, 0.0, "gel", "pisto-pois-gele.png");

        planteDAO.updatePlante(plante);

        verify(jdbcTemplate, times(1)).update(
                eq("UPDATE plante SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE id_plante = ?"),
                eq(plante.getNom()),
                eq(plante.getPoint_de_vie()),
                eq(plante.getAttaque_par_seconde()),
                eq(plante.getDegat_attaque()),
                eq(plante.getCout()),
                eq(plante.getSoleil_par_seconde()),
                eq(plante.getEffet()),
                eq(plante.getChemin_image()),
                eq(plante.getId_plante())
        );
    }

    @Test
    void testDeletePlante() {
        long idPlante = 1L;

        planteDAO.deletePlante(idPlante);

        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM plante WHERE id_plante = ?"), eq(idPlante));
    }
}
