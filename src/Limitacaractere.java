


import javax.swing.JFrame;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author herrmann
 */
public class Limitacaractere extends PlainDocument{
    
    public enum TipoEntrada {
        NOME, IDADE, SEXO, ALTURA;
    };
    
    private int qtdCaracteres;
    private TipoEntrada tpEntrada;

    public Limitacaractere(int qtdCaracteres, TipoEntrada tpEntrada) {
        this.qtdCaracteres = qtdCaracteres;
        this.tpEntrada = tpEntrada;
    }

    @Override
    public void insertString(int i, String string, AttributeSet as) throws BadLocationException {
        if (string == null || getLength() == qtdCaracteres){
            return;
        }
        int totalCarac = getLength() + string.length();
        // filtro de caracteres
        String regex = "";
        switch(tpEntrada){
            case IDADE: regex = "[^0-9]"; break;
            case ALTURA: regex = "[^0-9,.]"; break;
            case NOME:          regex = "[^\\p{IsLatin} ]"; break;
            case SEXO:         regex = "[^\\p{IsLatin}]"; break;
        }
        // fazendo a substituição
        string = string.replaceAll(regex, "");
        
        if (totalCarac <= qtdCaracteres){
            super.insertString(i, string, as); //To change body of generated methods, choose Tools | Templates.
        }else{
            String nova = string.substring(0, qtdCaracteres);
            super.insertString(i, nova, as);
        }
    }
     
    }
    
   

