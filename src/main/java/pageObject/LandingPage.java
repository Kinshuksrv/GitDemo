package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        this.driver = driver;
    }

    By login = By.cssSelector("[href*='sign_in']");
    By title = By.cssSelector("[class='text-center']");
    By header = By.xpath("//*[text()='Contact']");

    public WebElement SignIn(){
        return driver.findElement(login);
    }

    public WebElement getTitle(){
        return driver.findElement(title);
    }

    public WebElement getHeader(){
        return driver.findElement(header);
    }

}
