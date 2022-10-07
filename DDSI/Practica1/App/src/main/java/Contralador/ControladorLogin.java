/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;
import Modelo.*;
import Vista.*;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
/**
 *
 * @author ElPsy
 */
public class ControladorLogin {
    public Conexion conexion;
    public VistaConsola vc;
    
    public ControladorLogin() {
		this.vc = new VistaConsola();
        this.Conectar();
    }

    void Conectar() {
        try {
            // conexion = new Conexion("mariadb", "172.18.1.241:3306", "DDSI_022", "DDSI_022", "DDSI_022");
			conexion = new Conexion("oracle", "172.17.20.39:1521", "etsi", "DDSI_022", "DDSI_022");
        } catch (SQLException se) {
            String mensaje = ("codigo: " + se.getErrorCode() +
                " SQL: " + se.getSQLState()+
                " Texto :" + se.getMessage());
            System.out.println("Atención, se ha producido un error con " + mensaje);
        }   

		this.showMetaData();
    }

    public void Desconectar() {
		try{
		conexion.desconexion(); 
		} catch (SQLException e) {
			System.err.println("Error al desconectar la base de datos: " + e);
		}
    }
	
	DatabaseMetaData getMetaData(){
		return this.conexion.informacionDB();
	}

	void showMetaData(){
		this.vc.mensajeMetadatos(this.getMetaData());
	}
}
