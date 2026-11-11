package Framework.Utils;

import java.io.*;
import java.util.Properties;

public class FileOperations {

    private static final String DIR_PATH_PROPERTIES =
            System.getProperty("user.dir") + File.separator + "src" + File.separator +
                    "main" + File.separator + "resources" + File.separator +
                    "Properties" + File.separator;


    //Metodo para pegar dados dos arquivos de properties
    public static Properties getProperties(String fileName){

        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(DIR_PATH_PROPERTIES + fileName + ".properties")) {

            prop.load(input);
            return prop;

        } catch (Exception e) {

            System.out.println("Nao foi possivel carregar o arquivo de propriedade: " + fileName);
        }

        return prop;
    }

    //Metodo para salvar os dados em arquivos de properties
    public static void setProperties(String fileName, String propKey, String propValue){

        Properties prop = getProperties(fileName);

        try (OutputStream output = new FileOutputStream(DIR_PATH_PROPERTIES + fileName + ".properties")) {

            prop.setProperty(propKey, propValue);
            prop.store(output, null);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
