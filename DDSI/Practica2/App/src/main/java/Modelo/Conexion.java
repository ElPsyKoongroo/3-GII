/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;

/**
 *
 * @author ElPsy
 */
public class Conexion {
    public Connection conexion;
    
    public Conexion(String sgbd, String ip, String servicio_bd, String usuario, String password) throws SQLException {
        String url = "";
        if("mariadb".equals(sgbd)){
                url = "jdbc:" + sgbd + "://" + ip + "/" + servicio_bd;
            }
            else{
                url = "jdbc:" + sgbd + ":thin:@" + ip + ":" + servicio_bd; 
            }
            System.out.println("URL: " + url + "\nUser: " + usuario + "\nPassword: " + password);
            conexion = DriverManager.getConnection(url, usuario, password);
        /*
        try{
            if("mariadb".equals(sgbd)){
                url = "jdbc:" + sgbd + "://" + ip + "/" + servicio_bd;
            }
            else{
                url = "jdbc:" + sgbd + ":thin:@" + ip + ":" + servicio_bd; 
            }
            System.out.println("URL: " + url + "\nUser: " + usuario + "\nPassword: " + password);
            conexion = DriverManager.getConnection(url, usuario, password);
        }
        catch(SQLException e){
            System.out.println("Error catastrofico: " + e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }  */       
    }
    
    public void desconexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(" -- ");
        }
    }
	
    public DatabaseMetaData informacionDB() {
        try{
                return this.conexion.getMetaData();
        } catch(SQLException e) {
                System.err.println("Error al obtener la MetaData: " + e);
                return null;
        }
    }
    
}
