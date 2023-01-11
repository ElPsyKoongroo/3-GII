/**
 *
* @author ElPsy
 */

package Modelo;

public class Monitor {
    private String codigoMonitor;
    private String nombre;
    private String dni;
    private String telefono;
    private String correo;
    private String fechaEntrada;
    private String nick;

    public Monitor() {
        this.codigoMonitor = null;
        this.nombre = null;
        this.dni = null;
        this.telefono = null;
        this.correo = null;
        this.fechaEntrada = null;
        this.nick = null;
    }

    public Monitor(String _codigoMonitor, String _nombre, String _dni, String _telefono, String _correo, String _fechaEntrada, String _nick) {
        this.codigoMonitor = _codigoMonitor;
        this.nombre = _nombre;
        this.dni = _dni;
        this.telefono = _telefono;
        this.correo = _correo;
        this.fechaEntrada = _fechaEntrada;
        this.nick = _nick;
    }

    public String getCodigoMonitor(){
        return this.codigoMonitor;
    }
       
    public String getNombre(){
        return this.nombre;
    }
       
    public String getDni(){
        return this.dni;
    }
       
    public String getTelefono(){
        return this.telefono;
    }
       
    public String getCorreo(){
        return this.correo;
    }
       
    public String getFechaEntrada(){
        return this.fechaEntrada;
    }
       
    public String getNick(){
        return this.nick;
    }
       
}
