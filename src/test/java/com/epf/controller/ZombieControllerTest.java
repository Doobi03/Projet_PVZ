package com.epf.controller;

import com.epf.dto.ZombieDTO;
import com.epf.service.ZombieService;
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

//Tests unitaires de ZombieController
class ZombieControllerTest {

    @Mock
    private ZombieService zombieService;

    @InjectMocks
    private ZombieController zombieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllZombies() {
        ZombieDTO zombie1 = new ZombieDTO(
                1L, "Zombie 1", 100, 1.5, 15, 1.2, "chemin1.png", 1L
        );
        ZombieDTO zombie2 = new ZombieDTO(
                2L, "Zombie 2", 150, 1.2, 20, 0.8, "chemin2.png", 2L
        );
        List<ZombieDTO> mockZombies = Arrays.asList(zombie1, zombie2);

        when(zombieService.getAllZombies()).thenReturn(mockZombies);

        List<ZombieDTO> result = zombieController.getAllZombies();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(zombieService, times(1)).getAllZombies();
    }

    @Test
    void testGetZombieById() {
        ZombieDTO zombie = new ZombieDTO(
                1L, "Zombie Test", 120, 2.0, 25, 1.0, "chemin_test.png", 3L
        );

        when(zombieService.getZombieById(1L)).thenReturn(zombie);

        ZombieDTO result = zombieController.getZombieById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId_zombie());
        assertEquals("Zombie Test", result.getNom());
        assertEquals(120, result.getPoint_de_vie());
        assertEquals(2.0, result.getAttaque_par_seconde());
        assertEquals(25, result.getDegat_attaque());
        assertEquals(1.0, result.getVitesse_de_deplacement());
        assertEquals("chemin_test.png", result.getChemin_image());
        assertEquals(3L, result.getId_map());
        verify(zombieService, times(1)).getZombieById(1L);
    }

    @Test
    void testCreateZombie() {
        ZombieDTO zombie = new ZombieDTO(
                0L, "New Zombie", 80, 1.0, 10, 1.5, "chemin_new.png", 1L
        );

        ResponseEntity<Void> response = zombieController.createZombie(zombie);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(zombieService, times(1)).createZombie(zombie);
    }

    @Test
    void testUpdateZombie() {
        ZombieDTO zombie = new ZombieDTO(
                1L, "Updated Zombie", 90, 0.9, 12, 1.4, "chemin_updated.png", 2L
        );

        ResponseEntity<Void> response = zombieController.updateZombie(1L, zombie);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(zombieService, times(1)).updateZombie(1L, zombie);
    }

    @Test
    void testDeleteZombie() {
        ResponseEntity<Void> response = zombieController.deleteZombie(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(zombieService, times(1)).deleteZombie(1L);
    }
}
