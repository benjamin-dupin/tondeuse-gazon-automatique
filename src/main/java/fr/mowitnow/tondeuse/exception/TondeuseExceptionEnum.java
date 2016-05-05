package fr.mowitnow.tondeuse.exception;

import lombok.Getter;

/**
 * Enum des erreurs que peut rencontrer l'application
 * 
 * @author Benjamin
 *
 */
public enum TondeuseExceptionEnum {

    ERREUR_INCONNUE("Erreur inconnue"),

    FICHIER_ERREUR("Le chemin du fichier d'entrée n'est pas valide ou alors celui-ci n'existe pas"),

    INSTRUCTION_ERREUR_NOMBRE("Il n'y a pas assez d'instruction dans le fichier d'entrée"),

    INSTRUCTION_PELOUSE_ERREUR("La ligne correspondant à la description de la pelouse est incorrecte"),

    INSTRUCTION_POSITION_ERREUR("La ligne correspondant à la position de départ de la tondeuse est incorrecte"),

    INSTRUCTION_POSITION_PELOUSE_ERREUR(
            "La position de départ d'une tondeuse ne peut pas être en dehors de la pelouse"),

    INSTRUCTION_POSITION_PELOUSE_TONDEUSE_ERREUR("Les positions de départ des tondeuses doivent être différentes"),

    INSTRUCTION_POSITION_ORIENTATION_ERREUR("Une des position donnée à une tondeuse est incorrecte"),

    INSTRUCTION_INSTRUCTIONS_ERREUR("La ligne correspondant à la liste des instructions est incorrecte"),

    INSTRUCTION_INSTRUCTIONS_INCONNUE_ERREUR("Une des instruction donnée à une tondeuse est incorrecte");

    @Getter
    private String message;

    private TondeuseExceptionEnum(String message) {
        this.message = message;
    }

}
