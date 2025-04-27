package com.epf.dao.impl;

import com.epf.dao.PlanteDAO;
import com.epf.model.Map;
import com.epf.model.Plante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlanteDAOImpl implements PlanteDAO {

    private final JdbcTemplate jdbcTemplate;

    // Constructeur avec injection de JdbcTemplate
    public PlanteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //Création d'un mapper qui appelle les seters plutôt que de devoir les renseigner dans chaque méthode
    static final class PlanteMapper implements RowMapper<Plante> {
        public Plante mapRow(ResultSet rs, int rowNum) throws SQLException {
            Plante plante= new Plante();
            plante.setId_plante(rs.getLong("id_plante"));
            plante.setNom(rs.getString("nom"));
            plante.setPoint_de_vie(rs.getInt("point_de_vie"));
            plante.setAttaque_par_seconde(rs.getDouble("attaque_par_seconde"));
            plante.setDegat_attaque(rs.getInt("degat_attaque"));
            plante.setCout(rs.getInt("cout"));
            plante.setSoleil_par_seconde(rs.getDouble("soleil_par_seconde"));
            plante.setEffet(rs.getString("effet"));
            plante.setChemin_image(rs.getString("chemin_image"));
            return plante;

        }
    }
    @Override
    public List<Plante> getAllPlantes() {
        String sql = "SELECT * FROM plante";
        return jdbcTemplate.query(sql, new PlanteMapper());
    }

    @Override
    public Plante getPlanteById(long id_plante) {
        String sql = "SELECT * FROM plante WHERE id_plante = ?";
        return jdbcTemplate.queryForObject(sql, new PlanteMapper(), id_plante);
    }

    @Override
    public void createPlante(Plante plante) {
        String sql = "INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, plante.getNom(), plante.getPoint_de_vie(), plante.getAttaque_par_seconde(), plante.getDegat_attaque(), plante.getCout(), plante.getSoleil_par_seconde(), plante.getEffet(), plante.getChemin_image());
    }

    @Override
    public void updatePlante(Plante plante) {
        String sql = "UPDATE plante SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE id_plante = ?";
        jdbcTemplate.update(sql, plante.getNom(), plante.getPoint_de_vie(), plante.getAttaque_par_seconde(), plante.getDegat_attaque(), plante.getCout(), plante.getSoleil_par_seconde(), plante.getEffet(), plante.getChemin_image(), plante.getId_plante());
    }


    @Override
    public void deletePlante(long id_plante) {
        String sql = "DELETE FROM plante WHERE id_plante = ?";
        jdbcTemplate.update(sql, id_plante);
    }
}
