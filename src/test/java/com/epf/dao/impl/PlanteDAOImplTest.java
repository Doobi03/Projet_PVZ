//package com.epf.dao.impl;
//
//import static org.mockito.Mockito.*;
//
//import com.epf.model.Plante;
//import org.junit.Before;
//import org.mockito.*;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//
//public class PlanteDAOImplTest {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    private PlanteDAOImpl planteDAO;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        planteDAO = new PlanteDAOImpl(jdbcTemplate);
//    }
//
//    @Test
//    public void testGetAllPlantes() {
//        // Arrange
//        Plante plante1 = new Plante(1L, "Plante1", 100, 5.0, 20, 50, 0.5, "effet1", "image1.png");
//        Plante plante2 = new Plante(2L, "Plante2", 150, 6.0, 25, 60, 0.6, "effet2", "image2.png");
//        List<Plante> expectedPlantes = Arrays.asList(plante1, plante2);
//
//        // Simuler le comportement de JdbcTemplate
//        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedPlantes);
//
//        // Act
//        List<Plante> actualPlantes = planteDAO.getAllPlantes();
//
//        // Assert
//        assertEquals(2, actualPlantes.size());
//        assertEquals("Plante1", actualPlantes.get(0).getNom());
//        assertEquals("Plante2", actualPlantes.get(1).getNom());
//    }
//
//    @Test
//    public void testCreatePlante() {
//        // Arrange
//        Plante planteToCreate = new Plante(0L, "Plante3", 200, 7.0, 30, 70, 0.7, "effet3", "image3.png");
//
//        // Simuler le comportement de JdbcTemplate
//        doNothing().when(jdbcTemplate).update(anyString(), any(), any(), any(), any(), any(), any(), any());
//
//        // Act
//        planteDAO.createPlante(planteToCreate);
//
//        // Assert
//        verify(jdbcTemplate, times(1)).update(anyString(), any(), any(), any(), any(), any(), any(), any());
//    }
//
//    @Test
//    public void testDeletePlante() {
//        // Arrange
//        long planteId = 1L;
//
//        // Simuler le comportement de JdbcTemplate
//        doNothing().when(jdbcTemplate).update(anyString(), eq(planteId));
//
//        // Act
//        planteDAO.deletePlante(planteId);
//
//        // Assert
//        verify(jdbcTemplate, times(1)).update(anyString(), eq(planteId));
//    }
//}
