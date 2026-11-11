package Framework.Utils;

import net.datafaker.Faker;

import java.text.Normalizer;
import java.util.Locale;

public class FakersGeneration {

    private Faker faker;

    public FakersGeneration (){

        faker = new Faker(Locale.forLanguageTag("pt-BR"));
    }

    public static String removerAcentos(String texto) {
        return Normalizer
                .normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    public String getFirstName(){

        return removerAcentos(faker.name().firstName());
    }

    //E-mail unico a cada execucao (as contas ficam so em memoria local, entao nao pode repetir)
    public String getEmailUnico(String prefixo){

        return prefixo + System.currentTimeMillis() + "@email.com";
    }

}
