package fr.mowitnow.tondeuse.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.mowitnow.tondeuse.App;
import fr.mowitnow.tondeuse.enums.InstructionEnum;
import fr.mowitnow.tondeuse.enums.OrientationEnum;
import fr.mowitnow.tondeuse.exception.TondeuseException;
import fr.mowitnow.tondeuse.model.Pelouse;
import fr.mowitnow.tondeuse.model.Tondeuse;

/**
 * Tests de {@link InstructionsService}
 * 
 * @author Benjamin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class InstructionServiceTest {

    @Inject
    private InstructionsService instructionService;

    @Test
    public void testOk() {

        final String instructionsTondeuse1 = "GAGAGAGAA";
        final String instructionsTondeuse2 = "AADAADADDA";

        List<InstructionEnum> instructions = new ArrayList<InstructionEnum>();

        List<String> entrees = new ArrayList<String>();
        entrees.add("5 5");
        entrees.add("1 2 N");
        entrees.add(instructionsTondeuse1);
        entrees.add("3 3 E");
        entrees.add(instructionsTondeuse2);

        List<Tondeuse> tondeuses = instructionService.creerTondeuses(entrees);

        Assert.assertEquals(2, tondeuses.size());

        Pelouse pelouse = tondeuses.get(0).getPelouse();
        Assert.assertEquals(5, pelouse.getLargeur());
        Assert.assertEquals(5, pelouse.getLongueur());

        Tondeuse tondeuse1 = tondeuses.get(0);
        Assert.assertEquals(1, tondeuse1.getPosition().getX());
        Assert.assertEquals(2, tondeuse1.getPosition().getY());
        Assert.assertEquals(OrientationEnum.N, tondeuse1.getPosition().getOrientation());
        for (char c : instructionsTondeuse1.toCharArray()) {
            instructions.add(InstructionEnum.valueOf(String.valueOf(c)));
        }
        Assert.assertEquals(instructions, tondeuse1.getInstruction().getInstructions());

        instructions.clear();

        Tondeuse tondeuse2 = tondeuses.get(1);
        Assert.assertEquals(3, tondeuse2.getPosition().getX());
        Assert.assertEquals(3, tondeuse2.getPosition().getY());
        Assert.assertEquals(OrientationEnum.E, tondeuse2.getPosition().getOrientation());
        for (char c : instructionsTondeuse2.toCharArray()) {
            instructions.add(InstructionEnum.valueOf(String.valueOf(c)));
        }
        Assert.assertEquals(instructions, tondeuse2.getInstruction().getInstructions());
    }

    @Test(expected = TondeuseException.class)
    public void testKo1() {

        instructionService.creerTondeuses(new ArrayList<String>());
    }

    @Test(expected = TondeuseException.class)
    public void testKo2() {

        List<String> entrees = new ArrayList<String>();
        entrees.add("5 5");

        instructionService.creerTondeuses(entrees);
    }

    @Test(expected = TondeuseException.class)
    public void testKo3() {

        List<String> entrees = new ArrayList<String>();
        entrees.add("5 5");
        entrees.add("1 2 N");

        instructionService.creerTondeuses(entrees);
    }

}
