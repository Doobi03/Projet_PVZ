package com.epf.dao;

import com.epf.model.Zombie;
import java.util.List;

public interface ZombieDAO {

    // CRUD de base
    List<Zombie> getAllZombies();
    Zombie getZombieById(long id_zombie);
    void createZombie(Zombie zombie);
    void updateZombie(Zombie zombie);
    void deleteZombie(long id_zombie);

    // Méthode supplémentaire : Récupérer les zombies par map
    List<Zombie> getZombiesByIdMap(Long id_map);
}
