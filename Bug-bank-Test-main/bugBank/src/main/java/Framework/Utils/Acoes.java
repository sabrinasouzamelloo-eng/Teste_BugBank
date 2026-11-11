package Framework.Utils;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Acoes {

    //Limpa o campo antes de digitar, para nao acumular texto de um cadastro anterior
    public static void preencher(WebElement elemento, String texto){

        elemento.clear();
        elemento.sendKeys(texto);
    }

    //Clique via JavaScript, para elementos que o Selenium nao considera clicaveis
    public static void clicarViaJs(WebDriver driver, WebElement elemento){

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
    }

    //Tenta o clique normal; se outro elemento estiver por cima (ex.: fundo do formulario do BugBank), clica via JavaScript
    public static void clicar(WebDriver driver, WebElement elemento){

        try {
            elemento.click();
        } catch (ElementClickInterceptedException e) {
            clicarViaJs(driver, elemento);
        }
    }
}
