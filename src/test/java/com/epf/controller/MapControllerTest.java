package com.epf.controller;

import com.epf.dto.MapDTO;
import com.epf.service.MapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Tests unitaires de MapController
class MapControllerTest {

    @Mock
    private MapService mapService;

    @InjectMocks
    private MapController mapController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMaps() {
        MapDTO map1 = new MapDTO(1L, 10, 10, "chemin_map1.png");
        MapDTO map2 = new MapDTO(2L, 20, 20, "chemin_map2.png");

        List<MapDTO> mockMaps = Arrays.asList(map1, map2);

        when(mapService.getAllMaps()).thenReturn(mockMaps);

        List<MapDTO> result = mapController.getAllMaps();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(mapService, times(1)).getAllMaps();
    }

    @Test
    void testGetMapById() {
        MapDTO map = new MapDTO(1L, 10, 10, "chemin_map1.png");

        when(mapService.getMapById(1L)).thenReturn(map);

        MapDTO result = mapController.getMapById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId_map());
        assertEquals(10, result.getLigne());
        assertEquals(10, result.getColonne());
        assertEquals("chemin_map1.png", result.getChemin_image());
        verify(mapService, times(1)).getMapById(1L);
    }

    @Test
    void testCreateMap() {
        MapDTO map = new MapDTO(0L, 5, 5, "chemin_map_nouveau.png");

        ResponseEntity<Void> response = mapController.createMap(map);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(mapService, times(1)).createMap(map);
    }

    @Test
    void testUpdateMap() {
        MapDTO map = new MapDTO(1L, 15, 15, "chemin_map_modifie.png");

        ResponseEntity<Void> response = mapController.updateMap(1L, map);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(mapService, times(1)).updateMap(1L, map);
    }

    @Test
    void testDeleteMap() {
        doNothing().when(mapService).deleteMap(1L);

        ResponseEntity<String> response = mapController.deleteMap(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Map and associated zombies updated successfully.", response.getBody());
        verify(mapService, times(1)).deleteMap(1L);
    }

    @Test
    void testDeleteMapWithError() {
        doThrow(new RuntimeException("Map deletion failed")).when(mapService).deleteMap(1L);

        ResponseEntity<String> response = mapController.deleteMap(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("Error deleting map"));
        verify(mapService, times(1)).deleteMap(1L);
    }
}
