package Framework.Utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TakeScreenshot {

   //Pasta onde ficam as copias dos prints em PNG (apagada a cada "mvn clean")
   private static final Path PASTA_PRINTS = Paths.get(System.getProperty("user.dir"), "target", "prints");

   private static int contador = 0;

   public static void screenshotPage(WebDriver webDriver, String name){

       byte[] screenshotBytes = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES);

       //Anexa no relatorio do Allure como imagem PNG
       Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshotBytes), "png");

       salvarArquivo(name, screenshotBytes);
   }

   //Salva uma copia numerada do print, ex.: target/prints/03_Modal_foi_criada_com_sucesso.png
   private static void salvarArquivo(String name, byte[] screenshotBytes){

       try {

           Files.createDirectories(PASTA_PRINTS);
           contador++;
           String nomeArquivo = String.format("%02d_%s.png", contador, name.replaceAll("[^a-zA-Z0-9]+", "_"));
           Files.write(PASTA_PRINTS.resolve(nomeArquivo), screenshotBytes);

       } catch (IOException e) {

           System.out.println("Nao foi possivel salvar o print: " + name);
       }
   }
}
