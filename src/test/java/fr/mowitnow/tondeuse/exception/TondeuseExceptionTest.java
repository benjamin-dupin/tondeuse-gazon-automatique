package fr.mowitnow.tondeuse.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.mowitnow.tondeuse.App;

/**
 * Tests de {@link TondeuseException}
 * 
 * @author Benjamin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class TondeuseExceptionTest {

    @Test
    public void test() {

        for (TondeuseExceptionEnum tondeuseExceptionEnum : TondeuseExceptionEnum.values()) {

            try {

                throw new TondeuseException(tondeuseExceptionEnum);

            } catch (TondeuseException e) {

                Assert.assertEquals(tondeuseExceptionEnum.getMessage(), e.getMessage());

            }
        }
    }
}
