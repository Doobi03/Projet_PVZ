package com.epf.dao.impl;

import com.epf.dao.ZombieDAO;
import com.epf.model.Zombie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ZombieDAOImpl implements ZombieDAO {

    private final JdbcTemplate jdbcTemplate;

    public ZombieDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Création d'un mapper qui appelle les seters plutôt que de devoir les renseigner dans chaque méthode
    static final class ZombieMapper implements RowMapper<Zombie> {
        public Zombie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Zombie zombie = new Zombie();
            zombie.setId_zombie(rs.getLong("id_zombie"));
            zombie.setNom(rs.getString("nom"));
            zombie.setPoint_de_vie(rs.getInt("point_de_vie"));
            zombie.setAttaque_par_seconde(rs.getDouble("attaque_par_seconde"));
            zombie.setDegat_attaque(rs.getInt("degat_attaque"));
            zombie.setVitesse_de_deplacement(rs.getDouble("vitesse_de_deplacement"));
            zombie.setChemin_image(rs.getString("chemin_image"));
            zombie.setId_map(rs.getObject("id_map", Long.class)); // mapId peut être null
            return zombie;
        }
    }

    //Méthodes DAO
    @Override
    public List<Zombie> getAllZombies() {
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, new ZombieMapper());
    }

    @Override
    public Zombie getZombieById(long id_zombie) {
        String sql = "SELECT * FROM zombie WHERE id_zombie = ?";
        return jdbcTemplate.queryForObject(sql, new ZombieMapper(), id_zombie);
    }

    @Override
    public void createZombie(Zombie zombie) {
        String sql = "INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPoint_de_vie(), zombie.getAttaque_par_seconde(), zombie.getDegat_attaque(), zombie.getVitesse_de_deplacement(), zombie.getChemin_image(), zombie.getId_map());
    }

    @Override
    public void updateZombie(Zombie zombie) {
        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPoint_de_vie(), zombie.getAttaque_par_seconde(), zombie.getDegat_attaque(), zombie.getVitesse_de_deplacement(), zombie.getChemin_image(), zombie.getId_map(), zombie.getId_zombie());
    }

    @Override
    public void deleteZombie(long id_zombie) {
        String sql = "DELETE FROM zombie WHERE id_zombie = ?";
        jdbcTemplate.update(sql, id_zombie);
    }

    @Override
    public List<Zombie> getZombiesByIdMap(Long id_map) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql, new ZombieMapper(), id_map);
    }
}
