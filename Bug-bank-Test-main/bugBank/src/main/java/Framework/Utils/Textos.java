package Framework.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Textos {

    private static final Pattern CONTA = Pattern.compile("(\\d+)-(\\d+)");

    //Recebe "A conta 198-9 foi criada com sucesso" e devolve {"198", "9"}
    public static String[] extrairConta(String texto){

        Matcher matcher = CONTA.matcher(texto);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Nao encontrei numero de conta no texto: " + texto);
        }

        return new String[]{matcher.group(1), matcher.group(2)};
    }
}
