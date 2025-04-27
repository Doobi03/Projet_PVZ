//package com.epf.dao.impl;
//
//import com.epf.model.Zombie;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//
//import javax.sql.DataSource;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class ZombieDAOImplTest {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    private ZombieDAOImpl zombieDAO;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        zombieDAO = new ZombieDAOImpl((DataSource) jdbcTemplate);
//    }
//
//    @Test
//    void testGetAllZombies() {
//        Zombie zombie1 = new Zombie();
//        zombie1.setId_zombie(1L);
//        zombie1.setNom("Zombie1");
//
//        Zombie zombie2 = new Zombie();
//        zombie2.setId_zombie(2L);
//        zombie2.setNom("Zombie2");
//
//        List<Zombie> zombies = Arrays.asList(zombie1, zombie2);
//
//        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(zombies);
//
//        List<Zombie> result = zombieDAO.getAllZombies();
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals("Zombie1", result.get(0).getNom());
//        assertEquals("Zombie2", result.get(1).getNom());
//    }
//
//    @Test
//    void testGetZombieById() {
//        Zombie zombie = new Zombie();
//        zombie.setId_zombie(1L);
//        zombie.setNom("Zombie1");
//
//        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(1L))).thenReturn(zombie);
//
//        Zombie result = zombieDAO.getZombieById(1L);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId_zombie());
//        assertEquals("Zombie1", result.getNom());
//    }
//
//    @Test
//    void testCreateZombie() {
//        Zombie zombie = new Zombie();
//        zombie.setNom("Zombie1");
//        zombie.setPoint_de_vie(100);
//        zombie.setAttaque_par_seconde(10.5);
//        zombie.setDegat_attaque(50);
//        zombie.setVitesse_de_deplacement(2.5);
//        zombie.setChemin_image("/images/zombie1.png");
//        zombie.setId_map(1L);
//
//        doNothing().when(jdbcTemplate).update(anyString(), any(), anyInt(), anyDouble(), anyInt(), anyDouble(), anyString(), anyLong());
//
//        zombieDAO.createZombie(zombie);
//
//        verify(jdbcTemplate, times(1)).update(anyString(), any(), anyInt(), anyDouble(), anyInt(), anyDouble(), anyString(), anyLong());
//    }
//
//    @Test
//    void testUpdateZombie() {
//        Zombie zombie = new Zombie();
//        zombie.setId_zombie(1L);
//        zombie.setNom("Zombie Updated");
//        zombie.setPoint_de_vie(120);
//        zombie.setAttaque_par_seconde(12.0);
//        zombie.setDegat_attaque(60);
//        zombie.setVitesse_de_deplacement(3.0);
//        zombie.setChemin_image("/images/zombie_updated.png");
//        zombie.setId_map(1L);
//
//        doNothing().when(jdbcTemplate).update(anyString(), any(), anyInt(), anyDouble(), anyInt(), anyDouble(), anyString(), anyLong(), anyLong());
//
//        zombieDAO.updateZombie(zombie);
//
//        verify(jdbcTemplate, times(1)).update(anyString(), any(), anyInt(), anyDouble(), anyInt(), anyDouble(), anyString(), anyLong(), anyLong());
//    }
//
//    @Test
//    void testDeleteZombie() {
//        doNothing().when(jdbcTemplate).update(anyString(), anyLong());
//
//        zombieDAO.deleteZombie(1L);
//
//        verify(jdbcTemplate, times(1)).update(anyString(), eq(1L));
//    }
//
//    @Test
//    void testGetZombiesByIdMap() {
//        Zombie zombie1 = new Zombie();
//        zombie1.setId_zombie(1L);
//        zombie1.setNom("Zombie1");
//
//        Zombie zombie2 = new Zombie();
//        zombie2.setId_zombie(2L);
//        zombie2.setNom("Zombie2");
//
//        List<Zombie> zombies = Arrays.asList(zombie1, zombie2);
//
//        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(1L))).thenReturn(zombies);
//
//        List<Zombie> result = zombieDAO.getZombiesByIdMap(1L);
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals("Zombie1", result.get(0).getNom());
//        assertEquals("Zombie2", result.get(1).getNom());
//    }
//}
