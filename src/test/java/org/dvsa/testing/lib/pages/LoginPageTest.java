package org.dvsa.testing.lib.pages;

import activesupport.system.Properties;
import org.dvsa.testing.lib.Browser;
import org.dvsa.testing.lib.Environment;
import org.dvsa.testing.lib.URI;
import org.dvsa.testing.lib.utils.ApplicationType;
import org.dvsa.testing.lib.utils.EnvironmentType;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

public class LoginPageTest {
    String endPoint = "auth/login/";

    @BeforeClass
    public static void beforeAll() throws FileNotFoundException {
        Properties.loadConfigPropertiesFromFile();

        if(System.getProperty("browser") == null){
            System.setProperty("browser", "chrome");
        }
        if(System.getProperty("env") == null){
            System.setProperty("env", "da");
        }
    }

    @Test
    public void goToInternalLogonPage() {
        EnvironmentType environmentType = Environment.enumType(System.getProperty("env"));
        String URL = URI.build(ApplicationType.INTERNAL, environmentType, endPoint);

        Browser.open(URL);
        Assert.assertEquals(URL, Browser.getURL());
    }

    @Test
    public void goToExternalLogonPage(){
        EnvironmentType environmentType = Environment.enumType(System.getProperty("env"));
        String URL = URI.build(ApplicationType.EXTERNAL, environmentType, endPoint);

        Browser.open(URL);
        Assert.assertEquals(URL, Browser.getURL());
    }

    @After
    public void tearDown(){
        Browser.quit();
    }

}