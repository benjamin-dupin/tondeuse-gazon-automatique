package fr.mowitnow.tondeuse.model;

import java.util.List;

import fr.mowitnow.tondeuse.enums.InstructionEnum;
import lombok.Getter;

/**
 * Représente les instructions données à une tondeuse
 * 
 * @author Benjamin
 *
 */
public class Instruction {

    @Getter
    private List<InstructionEnum> instructions;

    public Instruction(List<InstructionEnum> instructions) {
        this.instructions = instructions;
    }

}
