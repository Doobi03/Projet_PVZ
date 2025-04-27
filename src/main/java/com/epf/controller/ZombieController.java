package com.epf.controller;

import com.epf.dto.ZombieDTO;
import com.epf.service.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/zombies")
public class ZombieController {

    private final ZombieService zombieService;
    @Autowired
    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    // Récupérer tous les zombies
    @GetMapping
    public List<ZombieDTO> getAllZombies() {
        return zombieService.getAllZombies();
    }

    // Récupérer un zombie par ID
    @GetMapping("/{id_zombie}")
    public ZombieDTO getZombieById(@PathVariable long id_zombie) {
        return zombieService.getZombieById(id_zombie);
    }

    // Créer un nouveau zombie
    @PostMapping
    public ResponseEntity<Void> createZombie(@RequestBody ZombieDTO zombieDTO) {
        zombieService.createZombie(zombieDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Mettre à jour un zombie existant
    @PutMapping("/{id_zombie}")
    public ResponseEntity<Void> updateZombie(@PathVariable long id_zombie, @RequestBody ZombieDTO zombieDTO) {
        zombieService.updateZombie(id_zombie, zombieDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Supprimer un zombie par ID
    @DeleteMapping("/{id_zombie}")
    public ResponseEntity<Void> deleteZombie(@PathVariable long id_zombie) {
        zombieService.deleteZombie(id_zombie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
