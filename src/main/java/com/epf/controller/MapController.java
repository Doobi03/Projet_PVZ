package com.epf.controller;

import com.epf.dto.MapDTO;
import com.epf.model.Map;
import com.epf.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;
    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    // Récupérer toutes les maps
    @GetMapping
    public List<MapDTO> getAllMaps() {
        return mapService.getAllMaps();
    }

    // Récupérer une map par ID
    @GetMapping("/{id_map}")
    public MapDTO getMapById(@PathVariable long id_map) {
        return mapService.getMapById(id_map);
    }

    // Ajouter une nouvelle map
    @PostMapping
    public ResponseEntity<Void> createMap(@RequestBody MapDTO mapDTO) {
        mapService.createMap(mapDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Mettre à jour une map
    @PutMapping("/{id_map}")
    public ResponseEntity<Void> updateMap(@PathVariable long id_map, @RequestBody MapDTO mapDTO) {
        mapService.updateMap(id_map, mapDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Supprimer une map
    @DeleteMapping("/{id_map}")
    public ResponseEntity<String> deleteMap(@PathVariable long id_map) {
        try {
            mapService.deleteMap(id_map);
            return new ResponseEntity<>("Map and associated zombies updated successfully.", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting map: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
