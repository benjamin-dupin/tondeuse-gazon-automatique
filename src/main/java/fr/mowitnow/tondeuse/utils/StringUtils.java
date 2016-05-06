package fr.mowitnow.tondeuse.utils;

/**
 * Utilitaire pour les {@link String}
 * 
 * @author Benjamin
 *
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * Déterminer si une chaine de caractère est null ou vide
     * 
     * @param s
     * @return <code>true</code> si la chaine de caractère est null ou vide,
     *         <code>false</code> si non
     */
    public static boolean isNullOrEmpty(String s) {

        return (s == null) || org.apache.commons.lang.StringUtils.isEmpty(s);
    }

}
