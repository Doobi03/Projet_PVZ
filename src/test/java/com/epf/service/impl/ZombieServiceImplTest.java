package com.epf.service.impl;

import com.epf.dao.ZombieDAO;
import com.epf.dto.ZombieDTO;
import com.epf.model.Zombie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

//Tests unitaires de ZombieServiceImpl
class ZombieServiceImplTest {

    private ZombieDAO zombieDAO;
    private ZombieServiceImpl zombieService;

    @BeforeEach
    void setUp() {
        zombieDAO = mock(ZombieDAO.class); // On mock le DAO
        zombieService = new ZombieServiceImpl(zombieDAO); // On injecte le mock dans le service
    }

    @Test
    void testGetAllZombies() {
        // Arrange : on prépare des données simulées
        Zombie zombie1 = new Zombie(1L, "Zombie1", 100, 1, 10, 1.0, "chemin1.png", 1L);
        Zombie zombie2 = new Zombie(2L, "Zombie2", 200, 2, 20, 2.0, "chemin2.png", 1L);

        when(zombieDAO.getAllZombies()).thenReturn(Arrays.asList(zombie1, zombie2));

        // Act
        List<ZombieDTO> zombies = zombieService.getAllZombies();

        // Assert
        assertEquals(2, zombies.size());
        assertEquals("Zombie1", zombies.get(0).getNom());
        assertEquals(100, zombies.get(0).getPoint_de_vie());
        verify(zombieDAO, times(1)).getAllZombies(); // Vérifie que le DAO a été appelé UNE fois
    }

    @Test
    void testGetZombieById() {
        // Arrange
        Zombie zombie = new Zombie(1L, "ZombieTest", 150, 1, 15, 1.5, "zombie.png", 2L);

        when(zombieDAO.getZombieById(1L)).thenReturn(zombie);

        // Act
        ZombieDTO zombieDTO = zombieService.getZombieById(1L);

        // Assert
        assertNotNull(zombieDTO);
        assertEquals("ZombieTest", zombieDTO.getNom());
        assertEquals(150, zombieDTO.getPoint_de_vie());
        verify(zombieDAO, times(1)).getZombieById(1L);
    }

    @Test
    void testCreateZombie() {
        // Arrange
        ZombieDTO zombieDTO = new ZombieDTO(1L, "NewZombie", 100, 1, 10, 1.0, "path.png", 1L);

        // Act
        zombieService.createZombie(zombieDTO);

        // Assert
        verify(zombieDAO, times(1)).createZombie(any(Zombie.class));
    }

    @Test
    void testDeleteZombie() {
        // Act
        zombieService.deleteZombie(1L);

        // Assert
        verify(zombieDAO, times(1)).deleteZombie(1L);
    }
}
