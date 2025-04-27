//package com.epf.dao.impl;
//
//import com.epf.model.Map;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class MapDAOImplTest {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private MapDAOImpl mapDAO;
//
//    private Map map;
//
//    @BeforeEach
//    void setUp() {
//        map = new Map();
//        map.setId_map(1L);
//        map.setLigne(10);
//        map.setColonne(5);
//        map.setChemin_image("image_path");
//    }
//
//    @Test
//    void testGetAllMaps() {
//        List<Map> maps = Arrays.asList(map);
//        when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(MapDAOImpl.MapMapper.class))).thenReturn(maps);
//
//        List<Map> result = mapDAO.getAllMaps();
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(map, result.get(0));
//    }
//
//    @Test
//    void testGetMapById() {
//        when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(MapDAOImpl.MapMapper.class), Mockito.anyLong())).thenReturn(map);
//
//        Map result = mapDAO.getMapById(1L);
//        assertNotNull(result);
//        assertEquals(map, result);
//    }
//
//    @Test
//    void testCreateMap() {
//        doNothing().when(jdbcTemplate).update(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
//
//        mapDAO.createMap(map);
//
//        verify(jdbcTemplate, times(1)).update(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
//    }
//
//    @Test
//    void testUpdateMap() {
//        doNothing().when(jdbcTemplate).update(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyLong());
//
//        mapDAO.updateMap(map);
//
//        verify(jdbcTemplate, times(1)).update(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyLong());
//    }
//
//    @Test
//    void testDeleteMap() {
//        doNothing().when(jdbcTemplate).update(Mockito.anyString(), Mockito.anyLong());
//
//        mapDAO.deleteMap(1L);
//
//        verify(jdbcTemplate, times(1)).update(Mockito.anyString(), Mockito.anyLong());
//    }
//}
