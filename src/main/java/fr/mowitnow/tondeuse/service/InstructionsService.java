package fr.mowitnow.tondeuse.service;

import java.util.List;

import fr.mowitnow.tondeuse.model.Tondeuse;

/**
 * Service des instructions
 * 
 * @author Benjamin
 *
 */
@FunctionalInterface
public interface InstructionsService {

    /**
     * Créer les {@link Tondeuse} en fonction des données d'entrée du fichier.
     * 
     * Pour programmer la tondeuse, on lui fournit un fichier d'entrée construit
     * comme suit :
     * 
     *  La première ligne correspond aux coordonnées du coin supérieur droit de
     * la pelouse, celles du coin inférieur gauche sont supposées être (0,0) 
     * La suite du fichier permet de piloter toutes les tondeuses qui ont été
     * déployées. Chaque tondeuse a deux lignes la concernant :
     * 
     *  la première ligne donne la position initiale de la tondeuse, ainsi que
     * son orientation. La position et l'orientation sont fournies sous la forme
     * de 2 chiffres et une lettre, séparés par un espace
     * 
     *  la seconde ligne est une série d'instructions ordonnant à la tondeuse
     * d'explorer la pelouse. Les instructions sont une suite de caractères sans
     * espaces.
     * 
     * @param entrees
     * @return {@link List} de {@link Tondeuse}
     */
    List<Tondeuse> creerTondeuses(List<String> entrees);

}
