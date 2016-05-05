package fr.mowitnow.tondeuse.model;

import lombok.Getter;

/**
 * Représente une pelouse
 * 
 * @author Benjamin
 *
 */
public class Pelouse {

    /**
     * Longueur du rectangle (axe X)
     */
    @Getter
    private int longueur;

    /**
     * Largeur du rectangle (axe Y)
     */
    @Getter
    private int largeur;

    public Pelouse(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
    }

}
