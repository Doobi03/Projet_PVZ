package com.epf.service.impl;

import com.epf.dao.MapDAO;
import com.epf.dto.MapDTO;
import com.epf.model.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MapServiceImplTest {

    private MapDAO mapDAO;
    private MapServiceImpl mapService;

    @BeforeEach
    void setUp() {
        mapDAO = mock(MapDAO.class);
        mapService = new MapServiceImpl(mapDAO);
    }

    @Test
    void testGetAllMaps() {
        List<Map> maps = Arrays.asList(
                new Map(1L, 5, 5, "chemin1.png"),
                new Map(2L, 6, 6, "chemin2.png")
        );
        when(mapDAO.getAllMaps()).thenReturn(maps);

        List<MapDTO> result = mapService.getAllMaps();

        assertEquals(2, result.size());
        assertEquals(5, result.get(0).getLigne());
        verify(mapDAO, times(1)).getAllMaps();
    }

    @Test
    void testGetMapById() {
        Map map = new Map(1L, 5, 5, "chemin.png");
        when(mapDAO.getMapById(1L)).thenReturn(map);

        MapDTO result = mapService.getMapById(1L);

        assertNotNull(result);
        assertEquals(5, result.getLigne());
        verify(mapDAO, times(1)).getMapById(1L);
    }

    @Test
    void testCreateMap() {
        MapDTO mapDTO = new MapDTO(1L, 5, 5, "chemin.png");

        mapService.createMap(mapDTO);

        verify(mapDAO, times(1)).createMap(any(Map.class));
    }

    @Test
    void testUpdateMap() {
        MapDTO mapDTO = new MapDTO(1L, 5, 5, "chemin.png");

        mapService.updateMap(1L, mapDTO);

        verify(mapDAO, times(1)).updateMap(any(Map.class));
    }

    @Test
    void testDeleteMap_ShouldCallDAO() {
        mapService.deleteMap(1L);

        verify(mapDAO, times(1)).deleteMap(1L);
    }
}
