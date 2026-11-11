package Validacao;

import Framework.Utils.TakeScreenshot;
import PageObjects.ModalPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class ModalValidacao {

    private WebDriver driver;
    private ModalPage modalPage;

    public ModalValidacao (WebDriver driver){

        this.driver = driver;
        modalPage = new ModalPage(this.driver);
    }

    public void validaMensagemContem(String trecho){

        try {

            String mensagem = modalPage.getModalText().getText();
            Assertions.assertTrue(mensagem.contains(trecho),
                    "Mensagem do modal esperada com '" + trecho + "', mas veio: '" + mensagem + "'");
            TakeScreenshot.screenshotPage(driver, "Modal: " + trecho);

        } catch (AssertionError | RuntimeException e) {

            TakeScreenshot.screenshotPage(driver, "Modal: " + trecho);
            throw e;
        }
    }
}
