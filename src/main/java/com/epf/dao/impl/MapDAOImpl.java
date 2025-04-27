package com.epf.dao.impl;

import com.epf.dao.MapDAO;
import com.epf.model.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MapDAOImpl implements MapDAO {

    private final JdbcTemplate jdbcTemplate;

    public MapDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Création d'un mapper qui appelle les seters plutôt que de devoir les renseigner dans chaque méthode
    static final class MapMapper implements RowMapper<Map> {
        @Override
        public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
            Map map = new Map();
            map.setId_map(rs.getLong("id_map")); // attention : respecter la base
            map.setLigne(rs.getInt("ligne"));
            map.setColonne(rs.getInt("colonne"));
            map.setChemin_image(rs.getString("chemin_image"));
            return map;
        }
    }

    @Override
    public List<Map> getAllMaps() {
        String sql = "SELECT * FROM map";
        return jdbcTemplate.query(sql, new MapMapper());
    }

    @Override
    public Map getMapById(long id_map) {
        String sql = "SELECT * FROM map WHERE id_map = ?";
        return jdbcTemplate.queryForObject(sql, new MapMapper(), id_map);
    }

    @Override
    public void createMap(Map map) {
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getChemin_image());
    }

    @Override
    public void updateMap(Map map) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getChemin_image(), map.getId_map());
    }

    @Override
    // Mettre à jour les zombies pour réinitialiser l'ID de la map (id_map = NULL)
    public void updateZombiesBeforeDelete(Long id_map) {
        String sql = "UPDATE zombie SET id_map =NULL WHERE id_map = ?";
        jdbcTemplate.update(sql, id_map);
    }

    @Override
    public void deleteMap(long id_map) {
        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(sql, id_map);
    }
}
