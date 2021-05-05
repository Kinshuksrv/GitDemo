import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.Base;

import java.io.IOException;

public class HomePage extends Base {

    public WebDriver driver;

    @Test(dataProvider="getData")
    public void basePageNavigation(String user, String password) throws IOException {
        driver = initializeDriver();
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        LandingPage landing = new LandingPage(driver);
        landing.SignIn().click();
        LoginPage login = new LoginPage(driver);
        login.getEmail().sendKeys(user);
        login.getPassword().sendKeys(password);
        login.getLogin().click();
    }

    @DataProvider
    public Object[][] getData(){
        Object[][] data = new Object[2][2];
        //non-restricted user
        data[0][0] = "restricteduser@qa.com";
        data[0][1] = "abc";
        //restricted user
        data[1][0] = "nonrestricteduser@qa.com";
        data[1][1] = "xyz";
        return data;
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
