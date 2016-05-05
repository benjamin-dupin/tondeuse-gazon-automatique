package fr.mowitnow.tondeuse.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.mowitnow.tondeuse.App;

/**
 * Tests de {@link StringUtils}
 * 
 * @author Benjamin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class StringUtilsTest {

    @Test
    public void test() {

        Assert.assertTrue(StringUtils.isNullOrEmpty(null));

        Assert.assertTrue(StringUtils.isNullOrEmpty(""));

        Assert.assertFalse(StringUtils.isNullOrEmpty(" "));

        Assert.assertFalse(StringUtils.isNullOrEmpty("1337"));
    }
}
