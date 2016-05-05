package fr.mowitnow.tondeuse.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Représente une tondeuse
 * 
 * @author Benjamin
 *
 */
public class Tondeuse {

    @Getter
    private Pelouse pelouse;

    @Getter
    private Instruction instruction;

    @Getter
    @Setter
    private Position position;

    public Tondeuse(Pelouse pelouse, Position position, Instruction instructions) {
        this.pelouse = pelouse;
        this.position = position;
        this.instruction = instructions;
    }

}
