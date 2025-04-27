package com.epf.dto;

public class ZombieDTO {

    //Paramètres de la classe zombieDTO
    private long id_zombie;
    private String nom;
    private int point_de_vie;
    private double attaque_par_seconde;
    private int degat_attaque;
    private double vitesse_de_deplacement;
    private String chemin_image;
    private Long id_map; // Bien garder Long et pas long pour accepter null si besoin

    // Constructeur par défaut
    public ZombieDTO() {}

    // Constructeur complet
    public ZombieDTO(long id_zombie, String nom, int point_de_vie, double attaque_par_seconde,
                     int degat_attaque, double vitesse_de_deplacement,
                     String chemin_image, Long id_map) {
        this.id_zombie = id_zombie;
        this.nom = nom;
        this.point_de_vie = point_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.vitesse_de_deplacement = vitesse_de_deplacement;
        this.chemin_image = chemin_image;
        this.id_map = id_map;
    }

    // Getters et Setters
    public long getId_zombie() {
        return id_zombie;
    }

    public void setId_zombie(long id_zombie) {
        this.id_zombie = id_zombie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoint_de_vie() {
        return point_de_vie;
    }

    public void setPoint_de_vie(int point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    public double getAttaque_par_seconde() {
        return attaque_par_seconde;
    }

    public void setAttaque_par_seconde(double attaque_par_seconde) {
        this.attaque_par_seconde = attaque_par_seconde;
    }

    public int getDegat_attaque() {
        return degat_attaque;
    }

    public void setDegat_attaque(int degat_attaque) {
        this.degat_attaque = degat_attaque;
    }

    public double getVitesse_de_deplacement() {
        return vitesse_de_deplacement;
    }

    public void setVitesse_de_deplacement(double vitesse_de_deplacement) {
        this.vitesse_de_deplacement = vitesse_de_deplacement;
    }

    public String getChemin_image() {
        return chemin_image;
    }

    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }

    public Long getId_map() {
        return id_map;
    }

    public void setId_map(Long id_map) {
        this.id_map = id_map;
    }
}
