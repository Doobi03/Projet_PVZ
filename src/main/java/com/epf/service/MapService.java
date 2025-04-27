package com.epf.service;

import com.epf.dto.MapDTO;
import java.util.List;

//Service de Map
public interface MapService {

    List<MapDTO> getAllMaps();
    MapDTO getMapById(long id_map);
    void createMap(MapDTO mapDTO);
    void updateMap(long id_map, MapDTO mapDTO);
    void deleteMap(long id_map);
}

