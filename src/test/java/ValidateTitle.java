import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.LandingPage;
import resources.Base;

import java.io.IOException;

public class ValidateTitle extends Base {
    public WebDriver driver;

    @Test
    public void validation() throws IOException, InterruptedException {
        driver = initializeDriver();
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        LandingPage landing = new LandingPage(driver);
        SoftAssert s = new SoftAssert();
        s.assertEquals(landing.getTitle().getText(),"FEATURED COURSES123");
        s.assertTrue(landing.getHeader().isDisplayed());
        s.assertAll();

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
