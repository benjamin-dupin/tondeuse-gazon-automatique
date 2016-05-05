package fr.mowitnow.tondeuse.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.mowitnow.tondeuse.App;
import fr.mowitnow.tondeuse.enums.InstructionEnum;
import fr.mowitnow.tondeuse.enums.OrientationEnum;
import fr.mowitnow.tondeuse.model.Instruction;
import fr.mowitnow.tondeuse.model.Pelouse;
import fr.mowitnow.tondeuse.model.Position;
import fr.mowitnow.tondeuse.model.Tondeuse;

/**
 * Tests de {@link DeplacementService}
 * 
 * @author Benjamin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class DeplacementServiceTest {

    @Inject
    private DeplacementService deplacementService;

    private Pelouse pelouse;

    @Before
    public void before() {

        pelouse = new Pelouse(5, 5);
    }

    @Test
    public void test1() {

        Position position = new Position(1, 2, OrientationEnum.N);

        List<InstructionEnum> instructions = new ArrayList<InstructionEnum>();

        for (char c : "GAGAGAGAA".toCharArray()) {

            instructions.add(InstructionEnum.valueOf(String.valueOf(c)));
        }

        Instruction instruction = new Instruction(instructions);

        Tondeuse tondeuse = new Tondeuse(pelouse, position, instruction);

        Position positionFinale = deplacementService.deplacerTondeuse(tondeuse);

        Assert.assertEquals(1, positionFinale.getX());
        Assert.assertEquals(3, positionFinale.getY());
        Assert.assertEquals(OrientationEnum.N, positionFinale.getOrientation());
    }

    @Test
    public void test2() {

        Position position = new Position(3, 3, OrientationEnum.E);

        List<InstructionEnum> instructions = new ArrayList<InstructionEnum>();

        for (char c : "AADAADADDA".toCharArray()) {

            instructions.add(InstructionEnum.valueOf(String.valueOf(c)));
        }

        Instruction instruction = new Instruction(instructions);

        Tondeuse tondeuse = new Tondeuse(pelouse, position, instruction);

        Position positionFinale = deplacementService.deplacerTondeuse(tondeuse);

        Assert.assertEquals(5, positionFinale.getX());
        Assert.assertEquals(1, positionFinale.getY());
        Assert.assertEquals(OrientationEnum.E, positionFinale.getOrientation());
    }

    @Test
    public void test3() {

        Position position = new Position(3, 3, OrientationEnum.E);

        Tondeuse tondeuse = new Tondeuse(pelouse, position, null);

        Position positionFinale = deplacementService.deplacerTondeuse(tondeuse);

        Assert.assertEquals(position, positionFinale);
    }

}
