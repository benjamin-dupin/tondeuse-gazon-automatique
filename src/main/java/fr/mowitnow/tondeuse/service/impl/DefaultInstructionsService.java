package fr.mowitnow.tondeuse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.mowitnow.tondeuse.enums.InstructionEnum;
import fr.mowitnow.tondeuse.enums.OrientationEnum;
import fr.mowitnow.tondeuse.exception.TondeuseException;
import fr.mowitnow.tondeuse.exception.TondeuseExceptionEnum;
import fr.mowitnow.tondeuse.model.Instruction;
import fr.mowitnow.tondeuse.model.Pelouse;
import fr.mowitnow.tondeuse.model.Position;
import fr.mowitnow.tondeuse.model.Tondeuse;
import fr.mowitnow.tondeuse.service.InstructionsService;
import fr.mowitnow.tondeuse.utils.StringUtils;

/**
 * Implémentation par défaut de {@link InstructionsService}
 * 
 * @author Benjamin
 *
 */
@Service
public class DefaultInstructionsService implements InstructionsService {

    /**
     * Séparateur entre les coordonnées
     */
    private static final String SEPARATEUR = " ";

    /**
     * Nombre minimum d'instructions que doit contenir les entrées
     */
    private static final int NB_MINI_INSTRUCTIONS = 3;

    /**
     * Nombre de String décrivant la pelouse
     */
    private static final int NB_STRING_PELOUSE = 2;

    /**
     * Nombre de String décrivant la position
     */
    private static final int NB_STRING_POSITION = 3;

    @Override
    public List<Tondeuse> creerTondeuses(List<String> entrees) {

        if ((entrees == null) || entrees.isEmpty() || (entrees.size() < NB_MINI_INSTRUCTIONS)) {
            throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_ERREUR_NOMBRE);
        }

        Pelouse pelouse = creerPelouse(entrees.get(0));

        List<Tondeuse> tondeuses = new ArrayList<>();

        // On avance de deux en deux à partir de la deuxième ligne
        for (int i = 1; i < entrees.size(); i += 2) {

            // S'il manque des instructions
            if (i + 2 > entrees.size()) {
                throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_ERREUR_NOMBRE);
            }

            Position position = creerPosition(entrees.get(i));

            verifierPosition(pelouse, position, tondeuses);

            Instruction instructions = creerInstructions(entrees.get(i + 1));

            Tondeuse tondeuse = new Tondeuse(pelouse, position, instructions);

            tondeuses.add(tondeuse);

        }

        return tondeuses;
    }

    /**
     * Si la position est supérieure à la taille de la pelouse, lance
     * {@link TondeuseException}
     * 
     * Si la position de la nouvelle tondeuse est identique à la position d'une
     * autre tondeuse, lance {@link TondeuseException}
     * 
     * @param pelouse
     * @param position
     * @param tondeuses
     */
    private void verifierPosition(Pelouse pelouse, Position position, List<Tondeuse> tondeuses) {

        // Vérifier que la tondeuse n'est pas en dehors de la pelouse
        if ((position.getX() > pelouse.getLongueur()) || (position.getY() > pelouse.getLargeur())) {
            throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_POSITION_PELOUSE_ERREUR);
        }

        // Vérifier que deux tondeuses ne sont pas positionné au même endroit
        for (Tondeuse tondeuse : tondeuses) {
            if (tondeuse.getPosition().equals(position)) {
                throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_POSITION_PELOUSE_TONDEUSE_ERREUR);
            }
        }
    }

    /**
     * Créer une {@link Pelouse} à partir de la commande
     * 
     * "5 5" va créer une pelouse de 5*5 de longueur et de largeur
     * 
     * @param commande
     * @return {@link Pelouse}
     */
    private Pelouse creerPelouse(String commande) {

        verifierCommande(commande, TondeuseExceptionEnum.INSTRUCTION_PELOUSE_ERREUR);

        String[] taille = commande.split(SEPARATEUR);

        verifierCommandeSplited(taille, NB_STRING_PELOUSE, TondeuseExceptionEnum.INSTRUCTION_PELOUSE_ERREUR);

        return new Pelouse(Integer.parseInt(taille[0]), Integer.parseInt(taille[1]));
    }

    /**
     * Créer une {@link Position} de départ pour une {@link Tondeuse}
     * 
     * "1 2 N" va créer une position sur "1, 2, N"
     * 
     * @param commande
     * @return {@link Position}
     */
    private Position creerPosition(String commande) {

        verifierCommande(commande, TondeuseExceptionEnum.INSTRUCTION_POSITION_ERREUR);

        String[] position = commande.split(SEPARATEUR);

        verifierCommandeSplited(position, NB_STRING_POSITION, TondeuseExceptionEnum.INSTRUCTION_POSITION_ERREUR);

        OrientationEnum orientation;

        try {

            orientation = OrientationEnum.valueOf(position[2].toUpperCase());

        } catch (IllegalArgumentException e) {
            throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_POSITION_ORIENTATION_ERREUR, e);
        }

        return new Position(Integer.parseInt(position[0]), Integer.parseInt(position[1]), orientation);
    }

    /**
     * Créer une {@link Instruction} pour une {@link Tondeuse}
     * 
     * @see OrientationEnum
     * @param commande
     * @return {@link Instruction}
     */
    private Instruction creerInstructions(String commande) {

        verifierCommande(commande, TondeuseExceptionEnum.INSTRUCTION_INSTRUCTIONS_ERREUR);

        List<InstructionEnum> instructions = new ArrayList<>();

        for (char c : commande.toCharArray()) {

            InstructionEnum instruction;

            try {

                instruction = InstructionEnum.valueOf(String.valueOf(c).toUpperCase());

            } catch (IllegalArgumentException e) {
                throw new TondeuseException(TondeuseExceptionEnum.INSTRUCTION_INSTRUCTIONS_INCONNUE_ERREUR, e);
            }

            instructions.add(instruction);

        }

        return new Instruction(instructions);
    }

    /**
     * Si la commande est null ou vide, lance {@link TondeuseException}
     * 
     * @param commande
     * @param tondeuseExceptionEnum
     */
    private void verifierCommande(String commande, TondeuseExceptionEnum tondeuseExceptionEnum) {

        if (StringUtils.isNullOrEmpty(commande)) {

            throw new TondeuseException(tondeuseExceptionEnum);
        }
    }

    /**
     * Si la commande splité est null ou de longueur différente de celle
     * espérée, lance {@link TondeuseException}
     * 
     * @param commande
     * @param longueurEsperee
     * @param tondeuseExceptionEnum
     */
    private void verifierCommandeSplited(String[] commande, int longueurEsperee,
            TondeuseExceptionEnum tondeuseExceptionEnum) {

        if (commande == null || commande.length != longueurEsperee) {

            throw new TondeuseException(tondeuseExceptionEnum);
        }
    }

}
