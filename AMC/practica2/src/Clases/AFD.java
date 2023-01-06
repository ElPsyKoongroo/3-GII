package Clases;
import java.util.ArrayList;
import java.util.Arrays;

/*

          _____             +-----------------+
          |. .| -------->   |  Hola Fran !!!  |
          |___|             +-----------------+
         \  |
          \ |
           \|\
            | \
            |  \
           / \
          /   \

*/

public class AFD implements Proceso {
    private static class TransicionAFD {
        public int E1, E2;
        public char Simb;
        public TransicionAFD(int _E1, char _Simb, int _E2){
            this.E1 = _E1;
            this.E2 = _E2;
            this.Simb = _Simb;
        }
    }

    private int[] estadosFinales;
    private ArrayList<TransicionAFD> transiciones;

    private AFD(int[] estados){
        estadosFinales = estados;
        transiciones = new ArrayList<>();
    }

    public void agregarTransicion(int e1, char simbolo, int e2){
        transiciones.add(new TransicionAFD(e1, simbolo, e2));
    }

    public void agregarTransicion(TransicionAFD transicionAFD){
        transiciones.add(transicionAFD);
    }


    public int transicion(int estado, char simbolo){
        return transiciones
                .stream()
                .filter(value -> value.E1 == estado && value.Simb == simbolo)
                .map(value -> value.E2)
                .findFirst()
                .orElse(-1);
    }

    public boolean esFinal(int estado){
        return Arrays.stream(estadosFinales).anyMatch(value -> value == estado);
    }

    public boolean Match(String cadena) {
        int estado = 0 ;
        for (char symbol: cadena.toCharArray()) {
            estado = transicion(estado,symbol);
            if (estado == -1) return false;
        }
        return esFinal(estado);
    }

    public static AFD LeerAFD(){
        /*
            ESTADOS: q0 q1 q2
            INICIAL: q0
            FINALES: q2
            TRANSICIONES:
            q0 '0' q1
            q0 '1' q2
            q2 '0' q1
            FIN
        * */

        AFD afd = new AFD(new int[]{2});

        TransicionAFD[] transiciones = new TransicionAFD[] {
            new TransicionAFD(0, '0', 1),
            new TransicionAFD(0, '1', 2),
            new TransicionAFD(2, '0', 1),
            new TransicionAFD(2, '1', 2),
        };

        for (TransicionAFD trans : transiciones){
            afd.agregarTransicion(trans);
        }
        return afd;
    }
    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();

        bld.append("Transicion(es): \n");

        for (TransicionAFD trans : transiciones){
            bld.append("E1: ")
                .append(trans.E1)
                .append("Simb: ")
                .append(trans.Simb)
                .append("E2: ")
                .append(trans.E2)
                .append('\n');
        }

        bld.append("\n\nEstado(s) final(es):\n");

        for(int estadoFinal : estadosFinales){
            bld.append("Estado: ")
                .append(estadoFinal)
                .append("\n");
        }

        return bld.toString();
    }
}