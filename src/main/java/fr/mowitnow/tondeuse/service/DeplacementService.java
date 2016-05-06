package fr.mowitnow.tondeuse.service;

import fr.mowitnow.tondeuse.model.Position;
import fr.mowitnow.tondeuse.model.Tondeuse;

/**
 * Service de déplacement d'une tondeuse
 * 
 * @author Benjamin
 *
 */
@FunctionalInterface
public interface DeplacementService {

    /**
     * Déplacer une tondeuse en exécutant ses instructions
     * 
     * @param tondeuse
     * @return {@link Position} finale d'une tondeuse
     */
    Position deplacerTondeuse(Tondeuse tondeuse);

}
