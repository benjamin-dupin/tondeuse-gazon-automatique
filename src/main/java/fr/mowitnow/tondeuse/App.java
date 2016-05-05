package fr.mowitnow.tondeuse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.mowitnow.tondeuse.exception.TondeuseException;
import fr.mowitnow.tondeuse.exception.TondeuseExceptionEnum;
import fr.mowitnow.tondeuse.service.TondeuseService;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Main
 * 
 * @author Benjamin
 *
 */
@CommonsLog
@Configuration
@ComponentScan
public class App {

    /**
     * args[0] -> Chemin du fichier d'entrée
     * 
     * @param args
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            throw new TondeuseException(TondeuseExceptionEnum.FICHIER_ERREUR);
        }

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        TondeuseService tondeuseService = context.getBean(TondeuseService.class);

        try {

            tondeuseService.demarrer(args[0]);

            System.exit(0);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TondeuseException(TondeuseExceptionEnum.ERREUR_INCONNUE);
        }
    }
}
