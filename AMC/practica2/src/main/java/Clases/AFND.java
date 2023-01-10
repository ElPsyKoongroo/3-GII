package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AFND implements Proceso{
    public static class TransicionAFND {
        public int E1, E2;
        public char Simb;
        public TransicionAFND(int _E1, char _Simb, int _E2){
            this.E1 = _E1;
            this.E2 = _E2;
            this.Simb = _Simb;
        }
    }
    public static class TransicionL {
        public int E1, E2;
        public TransicionL(int _E1, int _E2){
            this.E1 = _E1;
            this.E2 = _E2;
        }
    }

    public int[] estadosFinales;
    public ArrayList<AFND.TransicionAFND> transiciones;
    public ArrayList<TransicionL> transicionesL; //indica la lista de transiciones λ del AFND
    public List<String> estados;

    public AFND(int[] estados){
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
        Arrays.stream(macroEstado)
        .forEach(estado ->
              Arrays.stream(transicion(estado, simbolo))
              .forEach(estadosFin -> transiciones.add(estadosFin))
        );

        /*
        for (int estado : macroEstado){
            for (int estadosFin : transicion(estado, simbolo)) {
                transiciones.add(estadosFin);
            }
        }
        */

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
    public int[] clausuraL(int[] macroestado){
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
    
    private String lastInput = null;
    private int State[];
    private int lastPos = -1;
    public String StepByStep(String cadena) {
        
        if(lastInput == null || !lastInput.equals(cadena) || lastPos == lastInput.length())
        {
            lastInput = cadena;
            State = new int[]{0};
            lastPos = 0;
        }
        int inputSize = lastInput.length();
        
        State = transicion(State,cadena.toCharArray()[lastPos]);
        lastPos++;
        
        if (State.length == 0) return lastPos + " FINAL-RECHAZADO";
        
        if (lastPos == inputSize)
        {
            for(int i = 0; i < estadosFinales.length; ++i)
            {
                for(int j = 0; j < State.length; j++)
                {
                    if(estadosFinales[i] == State[j])
                        return lastPos + " FINAL-ACEPTADO";
                }
            }
            return lastPos + " FINAL-RECHAZADO";
        }
        boolean estado = esFinal(State);
        
        if(estado)
        {
            return lastPos + " EN_PROGRESO-ACEPTADO";
        }
        return lastPos + " EN_PROGRESO-RECHAZADO";
    }


    public static AFND LeerAFND(String path){
        AFND afnd;
        try {
            var reader = new BufferedReader(new FileReader(path));
            var estados = Arrays.stream(reader.readLine().trim().split(" "))
                    .skip(1)
                    .toList();
            

            int estadoInicial = Integer.parseInt(
                    Arrays.stream(reader.readLine().trim().split(" "))
                        .skip(1)
                        .findFirst()
                        .get()
                        .substring(1)
            );
            
            int[] estadosFinales = Arrays.stream(reader.readLine().trim().split(" "))
                    .skip(1)
                    .mapToInt(estadoFinal -> Integer.parseInt(estadoFinal.substring(1)))
                    .toArray();


            String transicion = reader.readLine(); // Esta de aqui no sirve pa nah
            afnd = new AFND(estadosFinales);
            afnd.estados = estados;

            while (!"FIN".equals(transicion = reader.readLine().trim())) {
                String[] datos = transicion.split(" ");
                int from = Integer.parseInt(datos[0].substring(1));
                char simbolo = datos[1].charAt(1);
                int to = Integer.parseInt(datos[2].substring(1));
                if (simbolo == 'L') {
                    afnd.agregarTransicionL(from, to);
                } else {
                    afnd.agregarTransicion(from, simbolo, to);
                }
            }

        } catch (Exception _e) {
            afnd = new AFND(new int[]{2});
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

