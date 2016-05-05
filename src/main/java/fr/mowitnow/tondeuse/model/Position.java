package fr.mowitnow.tondeuse.model;

import fr.mowitnow.tondeuse.enums.OrientationEnum;
import lombok.Data;

/**
 * Représente la position d'une {@link Tondeuse}
 * 
 * @author Benjamin
 *
 */
@Data
public class Position {

    private int x;

    private int y;

    private OrientationEnum orientation;

    public Position(int x, int y, OrientationEnum orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

}
