import Clases.AFD;
import Clases.AFND;

public class Main {
    public static void main(String[] args) {
        //AFD afd = AFD.LeerAFD();
        AFND afnd = AFND.LeerAFND();

        String[] cadenas = new String[]{
                "aaa",
                "bbbb",
                "aaababa",
                "aaab",
                "bba",
                "a",
                "b",
                "ab"
        };

        int bien = 0;
        for(String cadena : cadenas){
            if(afnd.Match(cadena)) {
                System.out.println(cadena + ": Si");
                bien++;
            }
        }
        for(String cadena : cadenas){
            if(!afnd.Match(cadena)) {
                System.out.println(cadena + ": No");
            }
        }
        System.out.println("Bien: " + bien);
        System.out.println("Total: " + cadenas.length);
    }
}