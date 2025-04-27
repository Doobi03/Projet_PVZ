//package com.epf.controller;
//
//import com.epf.dto.PlanteDTO;
//import com.epf.service.PlanteService;
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
//public class PlanteControllerTest {
//
//    @Mock
//    private PlanteService planteService;
//
//    @InjectMocks
//    private PlanteController planteController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(planteController).build();
//    }
//
//    @Test
//    void testGetAllPlantes() throws Exception {
//        List<PlanteDTO> plantes = List.of(new PlanteDTO(1L, "Plante A", 100, 1.5, 10, 5,2,"feu" , "path_image"));
//        when(planteService.getAllPlantes()).thenReturn(plantes);
//
//        mockMvc.perform(get("/CoursEpfBack/plantes"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].nom").value("Plante A"));
//    }
//
//    @Test
//    void testGetPlanteById() throws Exception {
//        PlanteDTO plante = new PlanteDTO(1L, "Plante A", 100, 1.5, 10, 5,2,"feu" , "path_image");
//        when(planteService.getPlanteById(1L)).thenReturn(plante);
//
//        mockMvc.perform(get("/CoursEpfBack/plantes/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.nom").value("Plante A"));
//    }
//
//    @Test
//    void testGetPlanteByIdNotFound() throws Exception {
//        when(planteService.getPlanteById(1L)).thenReturn(null);
//
//        mockMvc.perform(get("/CoursEpfBack/plantes/{id}", 1L))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testCreatePlante() throws Exception {
//        PlanteDTO planteDTO = new PlanteDTO(1L, "Plante A", 100, 1.5, 10, 5,2,"feu" , "path_image");
//
//        mockMvc.perform(post("/CoursEpfBack/plantes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"nom\":\"Plante B\",\"point_de_vie\":120,\"attaque_par_seconde\":2.0,\"degat_attaque\":15,\"vitesse_de_deplacement\":2.5,\"chemin_image\":\"path_image\",\"id_map\":1}"))
//                .andExpect(status().isCreated());
//
//        verify(planteService, times(1)).createPlante(any(PlanteDTO.class));
//    }
//
//    @Test
//    void testUpdatePlante() throws Exception {
//        PlanteDTO planteDTO = new PlanteDTO(1L, "Plante A", 100, 1.5, 10, 5,2,"feu" , "path_image");
//
//        mockMvc.perform(put("/CoursEpfBack/plantes/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"nom\":\"Plante C\",\"point_de_vie\":150,\"attaque_par_seconde\":2.5,\"degat_attaque\":20,\"vitesse_de_deplacement\":3.0,\"chemin_image\":\"new_path_image\",\"id_map\":2}"))
//                .andExpect(status().isOk());
//
//        verify(planteService, times(1)).updatePlante(eq(1L), any(PlanteDTO.class));
//    }
//
//    @Test
//    void testDeletePlante() throws Exception {
//        doNothing().when(planteService).deletePlante(1L);
//
//        mockMvc.perform(delete("/CoursEpfBack/plantes/{id}", 1L))
//                .andExpect(status().isNoContent());
//
//        verify(planteService, times(1)).deletePlante(1L);
//    }
//}
