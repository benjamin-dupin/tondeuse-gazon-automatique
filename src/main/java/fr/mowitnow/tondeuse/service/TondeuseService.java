package fr.mowitnow.tondeuse.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Service de programmation des tondeuses
 * 
 * @author Benjamin
 *
 */
public interface TondeuseService {

    /**
     * Démarrer la programmation des tondeuses
     * 
     * @param cheminFichier
     */
    void demarrer(String cheminFichier);

    /**
     * @see {@link #demarrer(String)}
     * @param fichier
     */
    void demarrer(File fichier);

    /**
     * @see {@link #demarrer(String)}
     * @param fichier
     */
    void demarrer(InputStream fichier);

    /**
     * @see {@link #demarrer(String)}
     * @param fichier
     */
    void demarrer(List<String> fichier);

}
