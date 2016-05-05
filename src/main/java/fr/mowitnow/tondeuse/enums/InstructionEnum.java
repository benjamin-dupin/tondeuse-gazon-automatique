package fr.mowitnow.tondeuse.enums;

/**
 * Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les
 * lettres possibles sont « D », « G » et « A ». « D » et « G » font pivoter la
 * tondeuse de 90° à droite ou à gauche respectivement, sans la déplacer. « A »
 * signifie que l'on avance la tondeuse d'une case dans la direction à laquelle
 * elle fait face, et sans modifier son orientation.
 * 
 * @author Benjamin
 *
 */
public enum InstructionEnum {

    /**
     * Tourner à droite
     */
    D,

    /**
     * Tourner à gauche
     */
    G,

    /**
     * Avancer
     */
    A;

}
