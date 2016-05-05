package fr.mowitnow.tondeuse.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import fr.mowitnow.tondeuse.model.Position;
import fr.mowitnow.tondeuse.model.Tondeuse;
import fr.mowitnow.tondeuse.service.DeplacementService;
import fr.mowitnow.tondeuse.service.InstructionsService;
import fr.mowitnow.tondeuse.service.TondeuseService;
import fr.mowitnow.tondeuse.utils.FileUtils;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Implémentation par défaut de {@link TondeuseService}
 * 
 * @author Benjamin
 *
 */
@Service
@CommonsLog
public class DefaultTondeuseService implements TondeuseService {

    @Inject
    private InstructionsService instructionService;

    @Inject
    private DeplacementService deplacementService;

    @Override
    public void demarrer(String cheminFichier) {

        demarrer(FileUtils.lireFichier(cheminFichier));
    }

    @Override
    public void demarrer(File fichier) {

        demarrer(FileUtils.lireFichier(fichier));
    }

    @Override
    public void demarrer(InputStream fichier) {

        demarrer(FileUtils.lireFichier(fichier));
    }

    @Override
    public void demarrer(List<String> fichier) {

        List<Tondeuse> tondeuses = instructionService.creerTondeuses(fichier);

        for (Tondeuse tondeuse : tondeuses) {

            Position positionFinale = deplacementService.deplacerTondeuse(tondeuse);

            log.info(positionFinale.getX() + " " + positionFinale.getY() + " " + positionFinale.getOrientation());
        }
    }

}
