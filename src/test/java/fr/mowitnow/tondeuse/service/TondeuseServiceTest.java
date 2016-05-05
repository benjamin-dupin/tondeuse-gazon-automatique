package fr.mowitnow.tondeuse.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.mowitnow.tondeuse.App;

/**
 * Tests de {@link TondeuseService}
 * 
 * @author Benjamin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class TondeuseServiceTest {

    @Inject
    private TondeuseService tondeuseService;

    private static final String FICHIER_TEST = "/test.txt";

    @Test
    public void test() {

        tondeuseService.demarrer(TondeuseServiceTest.class.getResourceAsStream(FICHIER_TEST));
    }

}
