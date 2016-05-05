package fr.mowitnow.tondeuse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.mowitnow.tondeuse.exception.TondeuseException;

/**
 * Tests de {@link App}
 * 
 * @author Benjamin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class AppTest {

    @Test(expected = TondeuseException.class)
    public void testKo() {

        App.main(new String[] {});

        App.main(null);
    }
}
