package com.epf.service;

import com.epf.dto.ZombieDTO;
import java.util.List;

//Service de zombie
public interface ZombieService {

    List<ZombieDTO> getAllZombies();
    ZombieDTO getZombieById(long id_zombie);
    void createZombie(ZombieDTO zombieDTO);
    void updateZombie(long id_zombie, ZombieDTO zombieDTO);
    void deleteZombie(long id_zombie);
    List<ZombieDTO> getZombiesByIdMap(Long id_map);
}