package external;

import org.dvsa.testing.lib.Browser;
import org.dvsa.testing.lib.URI;
import org.dvsa.testing.lib.utils.ApplicationType;
import org.dvsa.testing.lib.utils.Environment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginPageTest {
    private static String url = URI.build(ApplicationType.EXTERNAL, Environment.DAILY_ASSURANCE, "auth/login/");

    @Before
    public void setUp(){
        Browser.open(url);
    }

    @Test
    public void goToLogonPage(){
        Browser.open(url);
        Assert.assertEquals(url, Browser.getURL());
    }

    @After
    public void tearDown(){
        Browser.close();
    }

}
