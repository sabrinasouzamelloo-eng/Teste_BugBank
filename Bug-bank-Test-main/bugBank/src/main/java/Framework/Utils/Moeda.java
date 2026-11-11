package Framework.Utils;

import java.math.BigDecimal;

public class Moeda {

    //Converte "R$ 1.000,00" em 1000.00 (o texto vem com espaco nao separavel entre R$ e o valor).
    //Mantem o sinal de menos, caso o extrato mostre valores negativos.
    public static BigDecimal paraNumero(String texto){

        String limpo = texto.replaceAll("[^0-9,\\-]", "").replace(",", ".");
        return new BigDecimal(limpo);
    }
}
