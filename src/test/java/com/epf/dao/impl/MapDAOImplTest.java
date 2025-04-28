package com.epf.dao.impl;

import com.epf.model.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Tests unitaires de MapDAOImpl
class MapDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MapDAOImpl mapDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMaps() {
        Map map1 = new Map(1L, 0, 0, "chemin1.png");
        Map map2 = new Map(2L, 1, 1, "chemin2.png");
        List<Map> mockMaps = Arrays.asList(map1, map2);

        when(jdbcTemplate.query(anyString(), any(MapDAOImpl.MapMapper.class))).thenReturn(mockMaps);

        List<Map> result = mapDAO.getAllMaps();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(MapDAOImpl.MapMapper.class));
    }

    @Test
    void testGetMapById() {
        Map map = new Map(1L, 2, 3, "chemin1.png");

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(MapDAOImpl.MapMapper.class),
                eq(1L)
        )).thenReturn(map);

        Map result = mapDAO.getMapById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId_map());
        assertEquals(2, result.getLigne());
        assertEquals(3, result.getColonne());
        assertEquals("chemin1.png", result.getChemin_image());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(MapDAOImpl.MapMapper.class), eq(1L));
    }

    @Test
    void testCreateMap() {
        Map map = new Map(0L, 2, 3, "chemin1.png");

        mapDAO.createMap(map);

        verify(jdbcTemplate, times(1)).update(
                eq("INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)"),
                eq(2), eq(3), eq("chemin1.png")
        );
    }

    @Test
    void testUpdateMap() {
        Map map = new Map(1L, 5, 6, "chemin_updated.png");

        mapDAO.updateMap(map);

        verify(jdbcTemplate, times(1)).update(
                eq("UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?"),
                eq(5), eq(6), eq("chemin_updated.png"), eq(1L)
        );
    }

    @Test
    void testUpdateZombiesBeforeDelete() {
        Long idMap = 1L;

        mapDAO.updateZombiesBeforeDelete(idMap);

        verify(jdbcTemplate, times(1)).update(
                eq("UPDATE zombie SET id_map =NULL WHERE id_map = ?"),
                eq(idMap)
        );
    }

    @Test
    void testDeleteMap() {
        Long idMap = 1L;

        mapDAO.deleteMap(idMap);

        verify(jdbcTemplate, times(1)).update(
                eq("DELETE FROM map WHERE id_map = ?"),
                eq(idMap)
        );
    }
}
