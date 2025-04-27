package com.epf.service.impl;

import com.epf.dao.ZombieDAO;
import com.epf.dto.ZombieDTO;
import com.epf.model.Zombie;
import com.epf.service.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//ServiceImpl Zombie, lien DTO <-> Model
@Service
public class ZombieServiceImpl implements ZombieService {

    private final ZombieDAO zombieDAO;

    @Autowired
    public ZombieServiceImpl(ZombieDAO zombieDAO) {
        this.zombieDAO = zombieDAO;
    }

    //Conversion DTO-Model

    private ZombieDTO convertToDTO(Zombie zombie) {
        return new ZombieDTO(
                zombie.getId_zombie(),
                zombie.getNom(),
                zombie.getPoint_de_vie(),
                zombie.getAttaque_par_seconde(),
                zombie.getDegat_attaque(),
                zombie.getVitesse_de_deplacement(),
                zombie.getChemin_image(),
                zombie.getId_map()
        );
    }

    private Zombie convertToModel(ZombieDTO zombieDTO) {
        return new Zombie(
                zombieDTO.getId_zombie(),
                zombieDTO.getNom(),
                zombieDTO.getPoint_de_vie(),
                zombieDTO.getAttaque_par_seconde(),
                zombieDTO.getDegat_attaque(),
                zombieDTO.getVitesse_de_deplacement(),
                zombieDTO.getChemin_image(),
                zombieDTO.getId_map()
        );
    }

    //Méthodes DAO
    @Override
    public List<ZombieDTO> getAllZombies() {
        return zombieDAO.getAllZombies()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ZombieDTO getZombieById(long id_zombie) {
        Zombie zombie = zombieDAO.getZombieById(id_zombie);
        if (zombie == null) {
            throw new RuntimeException("Zombie not found");
        }
        return convertToDTO(zombie);
    }

    @Override
    public void createZombie(ZombieDTO zombieDTO) {
        zombieDAO.createZombie(convertToModel(zombieDTO));
    }

    @Override
    public void updateZombie(long id_zombie, ZombieDTO zombieDTO) {
        Zombie zombie = convertToModel(zombieDTO);
        zombie.setId_zombie(id_zombie);
        zombieDAO.updateZombie(zombie);
    }

    @Override
    public void deleteZombie(long id_zombie) {
        zombieDAO.deleteZombie(id_zombie);
    }

    @Override
    public List<ZombieDTO> getZombiesByIdMap(Long id_map) {
        return zombieDAO.getZombiesByIdMap(id_map)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
