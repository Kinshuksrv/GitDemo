package resources;

//import com.sun.jna.platform.FileUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    public Properties prop;
    FileInputStream fis;
    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        fis = new FileInputStream("C:\\Users\\Admin\\IdeaProjects\\AutomationE2EProject\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        System.out.println(browserName);
        if(browserName.equalsIgnoreCase("Chrome")){
            //invoke chrome browser
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Kinshuk\\chromedriver.exe");
            this.driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("Firefox")){
            //invoke Firefox browser
        }
        else if(browserName.equalsIgnoreCase("Internet Explorer")){
            //invoke IE browser
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;


    }

    public void takeScreenshot(String testcaseName, WebDriver driver) throws IOException {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir")+"\\reports\\"+testcaseName+".jpg";
        FileUtils.copyFile(src, new File(dest));
    }
}
