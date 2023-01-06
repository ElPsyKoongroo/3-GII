package Clases;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AFND implements Proceso{
    private static class TransicionAFND {
        public int E1, E2;
        public char Simb;
        public TransicionAFND(int _E1, char _Simb, int _E2){
            this.E1 = _E1;
            this.E2 = _E2;
            this.Simb = _Simb;
        }
    }
    private static class TransicionL {
        public int E1, E2;
        public TransicionL(int _E1, int _E2){
            this.E1 = _E1;
            this.E2 = _E2;
        }
    }

    private int[] estadosFinales;
    private ArrayList<AFND.TransicionAFND> transiciones;
    private ArrayList<TransicionL> transicionesL; //indica la lista de transiciones λ del AFND

    private AFND(int[] estados){
        estadosFinales = estados;
        transiciones = new ArrayList<>();
        transicionesL = new ArrayList<>();
    }

    public void agregarTransicion(int e1, char simbolo, int e2){
        transiciones.add(new TransicionAFND(e1, simbolo, e2));
    }
    public void agregarTransicionL(int e1, int e2){
        transicionesL.add(new TransicionL(e1, e2));
    }

    public void agregarTransicion(TransicionAFND transicionAFND){
        transiciones.add(transicionAFND);
    }
    public void agregarTransicionL(TransicionL transicionL){
        transicionesL.add(transicionL);
    }

    public int[] transicion(int estado, char simbolo){
        return Stream.concat(
                transiciones
                .stream()
                .filter(value -> value.E1 == estado && value.Simb == simbolo)
                .map(value -> value.E2),
                Arrays.stream(clausuraL(new int[]{estado})).boxed()
            )
            .mapToInt(Integer::intValue)
            .distinct()
            .toArray();
    }

    public int[] transicion(int[] macroEstado, char simbolo){
        ArrayList<Integer> transiciones = new ArrayList<>();

        for (int estado : macroEstado){
            for (int estadosFin : transicion(estado, simbolo)) {
                transiciones.add(estadosFin);
            }
        }

        return transiciones
                .stream()
                .distinct()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int[] transicionL (int estado){
        return transicionesL
                .stream()
                .filter(value -> value.E1 == estado)
                .mapToInt(value -> value.E2)
                .toArray();
    }

    public boolean esFinal(int estado){
        return Arrays.stream(estadosFinales).anyMatch(value -> value == estado);
    }

    public boolean esFinal(int[] macroEstado){
        return Arrays
            .stream(macroEstado)
            .anyMatch(estado -> esFinal(estado));
    }
    private int[] clausuraL(int[] macroestado){
        ArrayList<Integer> nextEstadosPorL = new ArrayList<>();



        for (int estado : macroestado){
            // Suerte con esta linea
            // Basicamente coge los estados salientes del que estamos mirando y los mete en la Queue
            Queue<Integer> saltosL = new LinkedList<>(transicionesL.stream().filter(v -> v.E1 == estado).mapToInt(x -> x.E2).boxed().collect(Collectors.toList()));

            while (!saltosL.isEmpty()) {
                int estadoActual = saltosL.poll();
                var nextEstados = transicionesL.stream().filter(v -> v.E1 == estadoActual).mapToInt(x -> x.E2).toArray();
                if (nextEstados.length == 0) {
                    nextEstadosPorL.add(estadoActual);
                } else {
                    Arrays.stream(nextEstados).forEach(estadoL -> saltosL.add(estadoL));
                }
            }
        }
        return nextEstadosPorL.stream().mapToInt(Integer::intValue).toArray();
    }


    public boolean Match(String cadena) {
        int[] macroestado = clausuraL(new int[]{0});
        if(macroestado.length == 0){
            macroestado = new int[]{0};
        }
        for(char simbolo : cadena.toCharArray()) {
            macroestado = transicion(macroestado, simbolo);
            if(macroestado.length == 0) return false;
        }
        return esFinal(macroestado);
    }


    public static AFND LeerAFND(){
        /*
            ESTADOS: q0 q1 q2 q3
            INICIAL: q0
            FINALES: q3
            TRANSICIONES:
            q0 '0' q0
            q0 '1' q0
            q0 '0' q1

            q1 '1' q2
            q1 '0' q2

            q2 '1' q3
            q2 '0' q3
            FIN
        * */

        AFND afnd = new AFND(new int[]{2,4});

        TransicionAFND[] transiciones = new TransicionAFND[] {
                new TransicionAFND(1, 'a', 2),
                new TransicionAFND(3, 'b', 4),

                new TransicionAFND(2, 'a', 2),
                new TransicionAFND(4, 'b', 4),
        };

        TransicionL[] transicionesL = new TransicionL[] {
                new TransicionL(0, 1),
                new TransicionL(0, 3),
        };

        for (TransicionAFND trans : transiciones){
            afnd.agregarTransicion(trans);
        }
        for (TransicionL trans : transicionesL){
            afnd.agregarTransicionL(trans);
        }

        return afnd;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();

        bld.append("Transicion(es): \n");

        for (TransicionAFND trans : transiciones){
            bld.append("E1: ")
                    .append(trans.E1)
                    .append("Simb: ")
                    .append(trans.Simb)
                    .append("E2: ")
                    .append(trans.E2)
                    .append('\n');
        }

        bld.append("\n\nTransicion(es) lambda: \n");

        for (TransicionL trans : transicionesL){
            bld.append("E1: ")
                    .append(trans.E1)
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

/*
public class AFND {
 private int [ ] estadosFinales; //indica cuales son los estados Finales
 private List<TransicionAFND> transiciones; //indica la lista de transiciones del AFND
 private List<Transicionλ> transicionesλ; //indica la lista de transiciones λ del AFND

 public AFND();
 public agregarTransicion(int e1, char simbolo, int [ ] e2);
 public agregarTransicionλ(int e1, int [ ] e2);
 private int [ ] transicion(int estado, char simbolo);
 public int [ ] transicion(int [ ] macroestado, char simbolo);
 public int [ ] transicionλ (int estado) ;
 private boolean esFinal(int estado);
 public boolean esFinal(int [ ] macroestado);
 private int[ ] λ_clausura(int[ ] macroestado);
 public boolean reconocer(String cadena) {
 char[ ] simbolo = cadena.toCharArray();
 int [ ] estado = { 0 }; //El estado inicial es el 0
 int[ ] macroestado = λ_clausura(estado);
 for(int i=0; i<simbolo.length; i++) {
 macroestado = transicion(macroestado, simbolo[i]);
 }
 return esFinal(macroestado);
 }

 public static AFND pedir():
 }

* */