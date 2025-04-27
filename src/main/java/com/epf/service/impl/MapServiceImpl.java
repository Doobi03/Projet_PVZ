package com.epf.service.impl;

import com.epf.dao.MapDAO;
import com.epf.dto.MapDTO;
import com.epf.model.Map;
import com.epf.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//Service Impl de Map, lien DTO <-> Model
@Service
public class MapServiceImpl implements MapService {

    private final MapDAO mapDAO;

    @Autowired
    public MapServiceImpl(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    //Conversion DTO-Model
    private MapDTO convertToDTO(Map map) {
        return new MapDTO(
                map.getId_map(),
                map.getLigne(),
                map.getColonne(),
                map.getChemin_image()
        );
    }

    private Map convertToModel(MapDTO mapDTO) {
        return new Map(
                mapDTO.getId_map(),
                mapDTO.getLigne(),
                mapDTO.getColonne(),
                mapDTO.getChemin_image()
        );
    }

    //Méthodes DAO
    @Override
    public List<MapDTO> getAllMaps() {
        return mapDAO.getAllMaps()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MapDTO getMapById(long id_map) {
        return convertToDTO(mapDAO.getMapById(id_map));
    }

    @Override
    public void createMap(MapDTO mapDTO) {
        mapDAO.createMap(convertToModel(mapDTO));
    }

    @Override
    public void updateMap(long id_map, MapDTO mapDTO) {
        Map map = convertToModel(mapDTO);
        map.setId_map(id_map);
        mapDAO.updateMap(map);
    }

    @Transactional
    public void deleteMap(long id_map) {
        mapDAO.updateZombiesBeforeDelete(id_map);  // Met à jour les zombies
        mapDAO.deleteMap(id_map);  // Supprime la map
    }
}
