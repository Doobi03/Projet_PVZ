//package com.epf.controller;
//
//import com.epf.dto.MapDTO;
//import com.epf.service.MapService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//public class MapControllerTest {
//
//    @Mock
//    private MapService mapService;
//
//    @InjectMocks
//    private MapController mapController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(mapController).build();
//    }
//
//    @Test
//    void testGetAllMaps() throws Exception {
//        List<MapDTO> maps = List.of(new MapDTO(11L, 5, 5,"chemin.png"), new MapDTO(1L, 5, 5,"chemin.png"));
//        when(mapService.getAllMaps()).thenReturn(maps);
//
//        mockMvc.perform(get("/maps"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].nom").value("Map A"))
//                .andExpect(jsonPath("$[1].nom").value("Map B"));
//    }
//
//    @Test
//    void testGetMapById() throws Exception {
//        MapDTO map = new MapDTO(1L, 5, 5,"chemin.png");
//        when(mapService.getMapById(1L)).thenReturn(map);
//
//        mockMvc.perform(get("/maps/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.nom").value("Map A"));
//    }
//
//    @Test
//    void testGetMapByIdNotFound() throws Exception {
//        when(mapService.getMapById(1L)).thenReturn(null);
//
//        mockMvc.perform(get("/maps/{id}", 1L))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testCreateMap() throws Exception {
//        MapDTO mapDTO = new MapDTO(1L, 5, 5,"chemin.png");
//
//        mockMvc.perform(post("/maps")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"nom\":\"Map C\",\"description\":\"Description C\"}"))
//                .andExpect(status().isCreated());
//
//        verify(mapService, times(1)).createMap(any(MapDTO.class));
//    }
//
//    @Test
//    void testUpdateMap() throws Exception {
//        MapDTO mapDTO = new MapDTO(1L, 5, 5,"chemin.png");
//
//        mockMvc.perform(put("/maps/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"nom\":\"Map D\",\"description\":\"Updated Description\"}"))
//                .andExpect(status().isOk());
//
//        verify(mapService, times(1)).updateMap(eq(1L), any(MapDTO.class));
//    }
//
//    @Test
//    void testDeleteMap() throws Exception {
//        doNothing().when(mapService).deleteMap(1L);
//
//        mockMvc.perform(delete("/maps/{id}", 1L))
//                .andExpect(status().isNoContent());
//
//        verify(mapService, times(1)).deleteMap(1L);
//    }
//}
