package fr.mowitnow.tondeuse.service.impl;

import org.springframework.stereotype.Service;

import fr.mowitnow.tondeuse.enums.InstructionEnum;
import fr.mowitnow.tondeuse.enums.OrientationEnum;
import fr.mowitnow.tondeuse.exception.TondeuseException;
import fr.mowitnow.tondeuse.exception.TondeuseExceptionEnum;
import fr.mowitnow.tondeuse.model.Position;
import fr.mowitnow.tondeuse.model.Tondeuse;
import fr.mowitnow.tondeuse.service.DeplacementService;

/**
 * Implémentation par défaut de {@link DeplacementService}
 * 
 * @author Benjamin
 *
 */
@Service
public class DefaultDeplacementService implements DeplacementService {

    @Override
    public Position deplacerTondeuse(Tondeuse tondeuse) {

        if (tondeuse.getInstruction() == null || tondeuse.getInstruction().getInstructions() == null) {
            return tondeuse.getPosition();
        }

        // Pour chaque instruction donnée à la tondeuse
        for (InstructionEnum instruction : tondeuse.getInstruction().getInstructions()) {

            if (!deplacementPossible(tondeuse, instruction)) {
                continue;
            }

            tondeuse.setPosition(recupererNouvellePosition(tondeuse, instruction));

        }

        return tondeuse.getPosition();
    }

    /**
     * Déterminer s'il est possible de déplacer la tondeuse
     * 
     * @param tondeuse
     * @param instruction
     * @return <code>true</code> si le déplacement est possible,
     *         <code>false</code> si non (lorsque la tondeuse risque de sortir
     *         de la pelouse)
     */
    private boolean deplacementPossible(Tondeuse tondeuse, InstructionEnum instruction) {

        if (!InstructionEnum.A.equals(instruction)) {
            return true;
        }

        Position positionApresDeplacement = recupererPositionAvancer(tondeuse);

        int x = positionApresDeplacement.getX();
        int y = positionApresDeplacement.getY();

        return (x <= tondeuse.getPelouse().getLongueur()) && (y <= tondeuse.getPelouse().getLargeur());
    }

    /**
     * Récupérer la nouvelle {@link Position} de la {@link Tondeuse} après
     * exécution de son {@link InstructionEnum}
     * 
     * @param tondeuse
     * @param instruction
     * @return nouvelle {@link Position} de la {@link Tondeuse}
     */
    private Position recupererNouvellePosition(Tondeuse tondeuse, InstructionEnum instruction) {

        Position position;

        switch (instruction) {

        case D:
        case G:
            position = new Position(tondeuse.getPosition().getX(), tondeuse.getPosition().getY(),
                    recupererNouvelleOrientation(tondeuse, instruction));
            break;

        case A:
            position = recupererPositionAvancer(tondeuse);
            break;

        default:
            throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_INSTRUCTIONS_INCONNUE_ERREUR);

        }

        return position;
    }

    /**
     * 
     * @param tondeuse
     * @param instruction
     * @return {@link OrientationEnum} de la {@link Tondeuse} après exécution de
     *         son {@link InstructionEnum}
     */
    private OrientationEnum recupererNouvelleOrientation(Tondeuse tondeuse, InstructionEnum instruction) {

        OrientationEnum actuelleOrientation = tondeuse.getPosition().getOrientation();
        OrientationEnum nouvelleOrientation;

        switch (instruction) {

        case D:
            nouvelleOrientation = recupererNouvelleOrientationInstructionDroite(actuelleOrientation);
            break;

        case G:
            nouvelleOrientation = recupererNouvelleOrientationInstructionGauche(actuelleOrientation);
            break;

        case A:
            nouvelleOrientation = actuelleOrientation;
            break;

        default:
            throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_INSTRUCTIONS_INCONNUE_ERREUR);

        }

        return nouvelleOrientation;
    }

    /**
     * Lorsque l'instruction donnée à une tondeuse est {@link InstructionEnum#D}
     * , renvoie sa nouvelle orientation
     * 
     * @param actuelleOrientation
     * @return {@link OrientationEnum}
     */
    private OrientationEnum recupererNouvelleOrientationInstructionDroite(OrientationEnum actuelleOrientation) {

        OrientationEnum nouvelleOrientation;

        switch (actuelleOrientation) {
        case N:
            nouvelleOrientation = OrientationEnum.E;
            break;
        case E:
            nouvelleOrientation = OrientationEnum.S;
            break;
        case W:
            nouvelleOrientation = OrientationEnum.N;
            break;
        case S:
            nouvelleOrientation = OrientationEnum.W;
            break;
        default:
            throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_POSITION_ORIENTATION_ERREUR);
        }

        return nouvelleOrientation;
    }

    /**
     * Lorsque l'instruction donnée à une tondeuse est {@link InstructionEnum#G}
     * , renvoie sa nouvelle orientation
     * 
     * @param actuelleOrientation
     * @return {@link OrientationEnum}
     */
    private OrientationEnum recupererNouvelleOrientationInstructionGauche(OrientationEnum actuelleOrientation) {

        OrientationEnum nouvelleOrientation;

        switch (actuelleOrientation) {
        case N:
            nouvelleOrientation = OrientationEnum.W;
            break;
        case E:
            nouvelleOrientation = OrientationEnum.N;
            break;
        case W:
            nouvelleOrientation = OrientationEnum.S;
            break;
        case S:
            nouvelleOrientation = OrientationEnum.E;
            break;
        default:
            throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_POSITION_ORIENTATION_ERREUR);
        }

        return nouvelleOrientation;
    }

    /**
     * Récupérer la position de la tondeuse lorsqu'elle a comme instruction
     * {@link InstructionEnum#A}
     * 
     * @param tondeuse
     * @return {@link Position} de la tondeuse (SANS LA DEPLACER) après
     *         {@link InstructionEnum#A}
     */
    private Position recupererPositionAvancer(Tondeuse tondeuse) {

        int x = tondeuse.getPosition().getX();
        int y = tondeuse.getPosition().getY();

        switch (tondeuse.getPosition().getOrientation()) {

        case N:
            y += 1;
            break;

        case E:
            x += 1;
            break;

        case W:
            x -= 1;
            break;

        case S:
            y -= 1;
            break;

        default:
            throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_POSITION_ORIENTATION_ERREUR);

        }

        return new Position(x, y, tondeuse.getPosition().getOrientation());
    }

}
