/**
 *
* @author ElPsy
 */

package Modelo;

public class Actividad {
    private String idActividad;
    private String nombre;
    private String descripcion;
    private String precioBaseMes;


    public Actividad(){
        this.idActividad = null;
        this.nombre = null;
        this.descripcion = null;
        this.precioBaseMes = null;
    }

    public Actividad(String _idActividad, String _nombre, String _descripcion, String _precioBaseMes){
        this.idActividad = _idActividad;
        this.nombre = _nombre;
        this.descripcion = _descripcion;
        this.precioBaseMes = _precioBaseMes;
    }

    public String getidActividad() {
        return this.idActividad;
    }
    public String getnombre() {
        return this.nombre;
    }
    public String getdescripcion() {
        return this.descripcion;
    }
    public String getprecioBaseMes() {
        return this.precioBaseMes;
    }

}
