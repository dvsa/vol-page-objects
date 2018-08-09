package org.dvsa.testing.lib.pages;

import activesupport.system.Properties;
import org.dvsa.testing.lib.browser.Browser;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.dvsa.testing.lib.url.webapp.URL;
import org.dvsa.testing.lib.url.webapp.utils.ApplicationType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

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
    public void goToInternalLogonPage() throws UninitialisedDriverException, MalformedURLException {
        EnvironmentType environmentType = EnvironmentType.getEnum(System.getProperty("env"));
        java.net.URL myURL = URL.build(ApplicationType.INTERNAL, environmentType, endPoint);

        Browser.open(myURL);
        Assert.assertEquals(myURL, Browser.getURL());
    }

    @Test
    public void goToExternalLogonPage() throws UninitialisedDriverException, MalformedURLException {
        EnvironmentType environmentType = EnvironmentType.getEnum(System.getProperty("env"));
        java.net.URL myURL = URL.build(ApplicationType.EXTERNAL, environmentType, LoginPage.getResourcePath());

        Browser.open(myURL);
        Assert.assertEquals(myURL, Browser.getURL());
    }

}
