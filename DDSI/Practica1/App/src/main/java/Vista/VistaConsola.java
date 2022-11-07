/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 *
* @author ElPsy
 */
public class VistaConsola {
    
    public void mensajeConsola (String texto){
        System.out.println(texto);
    }
    
    public void mensajeConsola (String texto, String error){
        System.err.println(texto + ": " + error);
    } 
    
	public void mensajeMetadatos(DatabaseMetaData dataBaseMetaData){
		try {
			this.mensajeConsola("Nombre: " + dataBaseMetaData.getDatabaseProductName());
			this.mensajeConsola("Version: " + dataBaseMetaData.getDatabaseProductVersion());
			this.mensajeConsola("URL: " + dataBaseMetaData.getURL());
			this.mensajeConsola("Driver: " + dataBaseMetaData.getDriverName());
			this.mensajeConsola("Driver Version: " + dataBaseMetaData.getDriverVersion());
			this.mensajeConsola("User name: " + dataBaseMetaData.getUserName());
			this.mensajeConsola("Terminos no SQL: " + dataBaseMetaData.getSQLKeywords());
		} catch(SQLException e){
			System.err.println("Error al printear la MetaData: " + e);
		}
	}
}
