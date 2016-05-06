package fr.mowitnow.tondeuse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.mowitnow.tondeuse.exception.TondeuseException;
import fr.mowitnow.tondeuse.exception.TondeuseExceptionEnum;
import fr.mowitnow.tondeuse.service.TondeuseService;

/**
 * Main
 * 
 * @author Benjamin
 *
 */
@Configuration
@ComponentScan
public class App {

    /**
     * args[0] -> Chemin du fichier d'entrée
     * 
     * @param args
     */
    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            throw new TondeuseException(TondeuseExceptionEnum.FICHIER_ERREUR);
        }

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        TondeuseService tondeuseService = context.getBean(TondeuseService.class);

        try {

            tondeuseService.demarrer(args[0]);

            context.close();

            System.exit(0);

        } catch (Exception e) {
            throw new TondeuseException(TondeuseExceptionEnum.ERREUR_INCONNUE, e);
        }
    }
}
