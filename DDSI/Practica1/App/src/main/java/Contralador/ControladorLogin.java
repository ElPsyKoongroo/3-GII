/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;
import Modelo.*;
import Vista.*;
import java.sql.SQLException;
/**
 *
 * @author ElPsy
 */
public class ControladorLogin {
    public Conexion conexion;
    public VistaConsola vc;
    
    public ControladorLogin() {
        this.Conectar();
    }

    void Conectar() {
        try {
            conexion = new Conexion("mariadb", "172.18.1.241", "DDSI_024", "DDSI_024", "DDSI_024");
        } catch (SQLException se) {
            String mensaje = ("codigo: " + se.getErrorCode() +
                " SQL: " + se.getSQLState()+
                " Texto :" + se.getMessage());
            System.out.println("Atención, se ha producido un error con " + mensaje);
        }   
    }
    void Desconectar() throws SQLException{
        
    }
}
