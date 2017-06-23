package test.by.artemyeu.betting;

import by.artemyeu.betting.logic.Validator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Acer on 16.05.2017.
 */
public class ValidationTest {
    private static Validator validator;

    @BeforeClass
    public static void initValidator() {
        validator = new Validator();
    }


    @Test
    public void checkIsTitleLengthValid() {
        String cash = "-1";
        boolean actual = validator.isCashValid(cash);
        Assert.assertFalse(actual);
    }


}