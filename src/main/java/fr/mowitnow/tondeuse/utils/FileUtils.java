package fr.mowitnow.tondeuse.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;

import fr.mowitnow.tondeuse.exception.TondeuseException;
import fr.mowitnow.tondeuse.exception.TondeuseExceptionEnum;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Utiliraire pour les fichiers
 * 
 * @author Benjamin
 *
 */
@CommonsLog
public class FileUtils {

    private FileUtils() {
    }

    /**
     * Lire un fichier depuis un chemin
     * 
     * @param cheminFichier
     * @return contenu du fichier
     */
    public static List<String> lireFichier(String cheminFichier) {

        return lireFichier(org.apache.commons.io.FileUtils.getFile(cheminFichier));
    }

    /**
     * Lire un fichier depuis un {@link File}
     * 
     * @param fichier
     * @return contenu du fichier
     */
    public static List<String> lireFichier(File fichier) {

        try {

            return org.apache.commons.io.FileUtils.readLines(fichier, StandardCharsets.UTF_8);

        } catch (IOException e) {

            log.error(e.getMessage());

            throw new TondeuseException(TondeuseExceptionEnum.FICHIER_ERREUR);

        }
    }

    /**
     * Lire un fichier depuis un {@link InputStream}
     * 
     * @param fichier
     * @return contenu du fichier
     */
    public static List<String> lireFichier(InputStream fichier) {

        try {

            return IOUtils.readLines(fichier, StandardCharsets.UTF_8);

        } catch (IOException e) {

            log.error(e.getMessage());

            throw new TondeuseException(TondeuseExceptionEnum.FICHIER_ERREUR);

        }
    }

}
