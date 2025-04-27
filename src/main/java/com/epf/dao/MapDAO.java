package com.epf.dao;

import com.epf.model.Map;
import java.util.List;

public interface MapDAO {

    // CRUD de base

    List<Map> getAllMaps();
    Map getMapById(long id_map);
    void createMap(Map map);
    void updateMap(Map map);
    void deleteMap(long id_map);
    void updateZombiesBeforeDelete(Long id_map);
}

