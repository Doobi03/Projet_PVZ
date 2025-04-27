//package com.epf.controller;
//
//import com.epf.dto.ZombieDTO;
//import com.epf.service.ZombieService;
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
//public class ZombieControllerTest {
//
//    @Mock
//    private ZombieService zombieService;
//
//    @InjectMocks
//    private ZombieController zombieController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(zombieController).build();
//    }
//
//    @Test
//    void testGetAllZombies() throws Exception {
//        List<ZombieDTO> zombies = List.of(new ZombieDTO(1L, "Zombie A", 100, 1.5, 10, 2.0, "path_image", 1L));
//        when(zombieService.getAllZombies()).thenReturn(zombies);
//
//        mockMvc.perform(get("/CoursEpfBack/zombies"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].nom").value("Zombie A"));
//    }
//
//    @Test
//    void testGetZombieById() throws Exception {
//        ZombieDTO zombie = new ZombieDTO(1L, "Zombie A", 100, 1.5, 10, 2.0, "path_image", 1L);
//        when(zombieService.getZombieById(1L)).thenReturn(zombie);
//
//        mockMvc.perform(get("/CoursEpfBack/zombies/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.nom").value("Zombie A"));
//    }
//
//    @Test
//    void testGetZombieByIdNotFound() throws Exception {
//        when(zombieService.getZombieById(1L)).thenReturn(null);
//
//        mockMvc.perform(get("/CoursEpfBack/zombies/{id}", 1L))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testCreateZombie() throws Exception {
//        ZombieDTO zombieDTO = new ZombieDTO(1L, "Zombie B", 120, 2.0, 15, 2.5, "path_image", 1L);
//
//        mockMvc.perform(post("/CoursEpfBack/zombies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"nom\":\"Zombie B\",\"point_de_vie\":120,\"attaque_par_seconde\":2.0,\"degat_attaque\":15,\"vitesse_de_deplacement\":2.5,\"chemin_image\":\"path_image\",\"id_map\":1}"))
//                .andExpect(status().isCreated());
//
//        verify(zombieService, times(1)).createZombie(any(ZombieDTO.class));
//    }
//
//    @Test
//    void testUpdateZombie() throws Exception {
//        ZombieDTO zombieDTO = new ZombieDTO(1L, "Zombie C", 150, 2.5, 20, 3.0, "new_path_image", 2L);
//
//        mockMvc.perform(put("/CoursEpfBack/zombies/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"nom\":\"Zombie C\",\"point_de_vie\":150,\"attaque_par_seconde\":2.5,\"degat_attaque\":20,\"vitesse_de_deplacement\":3.0,\"chemin_image\":\"new_path_image\",\"id_map\":2}"))
//                .andExpect(status().isOk());
//
//        verify(zombieService, times(1)).updateZombie(eq(1L), any(ZombieDTO.class));
//    }
//
//    @Test
//    void testDeleteZombie() throws Exception {
//        doNothing().when(zombieService).deleteZombie(1L);
//
//        mockMvc.perform(delete("/CoursEpfBack/zombies/{id}", 1L))
//                .andExpect(status().isNoContent());
//
//        verify(zombieService, times(1)).deleteZombie(1L);
//    }
//}
