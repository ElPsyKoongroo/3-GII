package Clases;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    Nos hemos ahorrado implementar clonable para AFD ya que no se utiliza para absolutamente nada,
    ademas esta considerada un "cacao" y que es mucho mejor implementar constructores de copia.
*/

public class AFD implements Proceso {
    public static class TransicionAFD {
        public int E1, E2;
        public char Simb;
        public TransicionAFD(int _E1, char _Simb, int _E2){
            this.E1 = _E1;
            this.E2 = _E2;
            this.Simb = _Simb;
        }
    }

    public int[] estadosFinales;
    public ArrayList<TransicionAFD> transiciones;
    public List<String> estados;
    
    public AFD(int[] estados){
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
    
    private String lastInput = null;
    private int State = -1;
    private int lastPos = -1;
    public String StepByStep(String cadena) {
        
        if(lastInput == null || !lastInput.equals(cadena) || lastPos == lastInput.length())
        {
            lastInput = cadena;
            State = 0;
            lastPos = 0;
        }
        int inputSize = lastInput.length();
        
        State = transicion(State,cadena.toCharArray()[lastPos]);
        lastPos++;
        
        if (State == -1) return lastPos + " FINAL-RECHAZADO";
        
        if (lastPos == inputSize)
        {
            for(int i = 0; i < estadosFinales.length; ++i)
            {
                if(estadosFinales[i] == State)
                    return lastPos + " FINAL-ACEPTADO";
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

    public static AFD LeerAFD(String path){
        AFD afd;
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
            afd = new AFD(estadosFinales);
            afd.estados = estados;

            while (!"FIN".equals(transicion = reader.readLine().trim())) {
                String[] datos = transicion.split(" ");
                int from = Integer.parseInt(datos[0].substring(1));
                char simbolo = datos[1].charAt(1);
                int to = Integer.parseInt(datos[2].substring(1));
                afd.agregarTransicion(from, simbolo, to);
                
            }

        } catch (Exception _e) {
            afd = new AFD(new int[]{2});
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