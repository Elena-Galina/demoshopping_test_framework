package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseUiTestTest extends BaseUiTest {

    @Test
    public void testDriverIsCreated(){

        Assert.assertNotNull(getDriver().getCurrentUrl());
    }
}
