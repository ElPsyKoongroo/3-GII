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
        System.err.println(texto + " " + error);
    } 
    
	public void mensajeMetadatos(DatabaseMetaData dataBaseMetaData){
		try {
			System.out.println(
				"Nombre: " + dataBaseMetaData.getDatabaseProductName() + "\n" 
				+ "Version: " + dataBaseMetaData.getDatabaseProductVersion() + "\n" 
				+ "URL: " + dataBaseMetaData.getURL() + "\n" 
				+ "Driver: " + dataBaseMetaData.getDriverName() + "\n"
				+ "Driver Version: " + dataBaseMetaData.getDriverVersion() + "\n"
				+ "User name: " + dataBaseMetaData.getUserName() + "\n"
				+ "Terminos no SQL: " + dataBaseMetaData.getSQLKeywords()
			);
		} catch(SQLException e){
			System.err.println("Error al printear la MetaData: " + e);
		}
	}
}
