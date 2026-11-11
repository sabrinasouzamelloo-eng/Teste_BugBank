package Framework.Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    private WebDriver driver;

    public Waits (WebDriver driver){

        this.driver = driver;
    }

    private WebDriverWait novaEspera(){

        return new WebDriverWait(driver, TIMEOUT);
    }


    public WebElement visbilityOfElement(By by){

        return novaEspera().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement presenceOfElement(By by){

        return novaEspera().until(ExpectedConditions.presenceOfElementLocated(by));
    }


    public WebElement clickableElement(By by){

        return novaEspera().until(ExpectedConditions.elementToBeClickable(by));
    }


    public boolean invisibilityOfElement(By by){

        return novaEspera().until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement loadElement(WebElement element){

        try{

            return novaEspera().until(ExpectedConditions.visibilityOf(element));

        }catch (WebDriverException e){

            return element;
        }

    }
}
