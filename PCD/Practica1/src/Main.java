/**
* @author ElPsy
 */
public class Main {
    public static void main(String[] args) {

        Cola cola = new Cola(10);

        try{
            for (int i = 0; i < 10; i++){
                cola.Acola(i);
                System.out.println("Metido " + i);
            }
        }
        catch (Exception e){
            System.out.println("Error al introducir elemento: " + e.getMessage());
        }

        try{
            for (int i = 0; i < 9; i++){
                int num = (int)cola.Desacola();
                System.out.println("Eliminado elemento:" + num);
            }
        }
        catch (Exception e){
            System.out.println("Error al eliminar elemento: " + e.getMessage());
        }

        try {
            int num = (int) cola.Primero();
            System.out.println("Numero: " + num);
        }
        catch (Exception e){
            System.out.println("Error al leer primer elemento: " + e.getMessage());
        }
    }
}
