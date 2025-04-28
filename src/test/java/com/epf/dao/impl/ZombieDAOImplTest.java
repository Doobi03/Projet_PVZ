package com.epf.dao.impl;

import com.epf.model.Zombie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Tests unitaires de ZombieDAOImpl
class ZombieDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ZombieDAOImpl zombieDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllZombies() {
        Zombie zombie1 = new Zombie(1L, "Zombie1", 100, 1.5, 10, 2.0, "zombie1.png", 1L);
        Zombie zombie2 = new Zombie(2L, "Zombie2", 120, 2.0, 15, 2.5, "zombie2.png", 1L);
        List<Zombie> mockZombies = Arrays.asList(zombie1, zombie2);

        when(jdbcTemplate.query(anyString(), any(ZombieDAOImpl.ZombieMapper.class))).thenReturn(mockZombies);

        List<Zombie> result = zombieDAO.getAllZombies();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ZombieDAOImpl.ZombieMapper.class));
    }

    @Test
    void testGetZombieById() {
        Zombie zombie = new Zombie(1L, "Zombie1", 100, 1.5, 10, 2.0, "zombie1.png", 1L);

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(ZombieDAOImpl.ZombieMapper.class),
                eq(1L)
        )).thenReturn(zombie);

        Zombie result = zombieDAO.getZombieById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId_zombie());
        assertEquals("Zombie1", result.getNom());
        assertEquals(100, result.getPoint_de_vie());
        assertEquals(1.5, result.getAttaque_par_seconde());
        assertEquals(10, result.getDegat_attaque());
        assertEquals(2.0, result.getVitesse_de_deplacement());
        assertEquals("zombie1.png", result.getChemin_image());
        assertEquals(1L, result.getId_map());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(ZombieDAOImpl.ZombieMapper.class), eq(1L));
    }

    @Test
    void testCreateZombie() {
        Zombie zombie = new Zombie(0L, "ZombieNew", 90, 1.2, 8, 1.8, "zombie_new.png", 2L);

        zombieDAO.createZombie(zombie);

        verify(jdbcTemplate, times(1)).update(
                eq("INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)"),
                eq("ZombieNew"), eq(90), eq(1.2), eq(8), eq(1.8), eq("zombie_new.png"), eq(2L)
        );
    }

    @Test
    void testUpdateZombie() {
        Zombie zombie = new Zombie(1L, "ZombieUpdated", 110, 1.7, 12, 2.2, "zombie_updated.png", 3L);

        zombieDAO.updateZombie(zombie);

        verify(jdbcTemplate, times(1)).update(
                eq("UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?"),
                eq("ZombieUpdated"), eq(110), eq(1.7), eq(12), eq(2.2), eq("zombie_updated.png"), eq(3L), eq(1L)
        );
    }

    @Test
    void testDeleteZombie() {
        Long idZombie = 1L;

        zombieDAO.deleteZombie(idZombie);

        verify(jdbcTemplate, times(1)).update(
                eq("DELETE FROM zombie WHERE id_zombie = ?"),
                eq(idZombie)
        );
    }

    @Test
    void testGetZombiesByIdMap() {
        Zombie zombie1 = new Zombie(1L, "Zombie1", 100, 1.5, 10, 2.0, "zombie1.png", 1L);
        Zombie zombie2 = new Zombie(2L, "Zombie2", 120, 2.0, 15, 2.5, "zombie2.png", 1L);
        List<Zombie> mockZombies = Arrays.asList(zombie1, zombie2);

        when(jdbcTemplate.query(
                anyString(),
                any(ZombieDAOImpl.ZombieMapper.class),
                eq(1L)
        )).thenReturn(mockZombies);

        List<Zombie> result = zombieDAO.getZombiesByIdMap(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ZombieDAOImpl.ZombieMapper.class), eq(1L));
    }
}
