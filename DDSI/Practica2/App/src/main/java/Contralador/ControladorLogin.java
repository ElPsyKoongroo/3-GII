/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;
import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import javax.swing.*;
/**
 *
 * @author ElPsy
 */
public class ControladorLogin implements ActionListener {
    public Conexion conexion;
    public VistaConsola vc;
    private VistaLogin vLogin;
    
    public ControladorLogin() {
        this.vc = new VistaConsola();
        this.vLogin = new VistaLogin();
        
        this.addListeners();
        this.vLogin.setLocationRelativeTo(null);
        this.vLogin.setVisible(true);
        //this.Conectar();
    }

    void Conectar() {
        try {
            // conexion = new Conexion("mariadb", "172.18.1.241:3306", "DDSI_022", "DDSI_022", "DDSI_022");
            conexion = new Conexion("oracle", "172.17.20.39:1521", "etsi", "DDSI_022", "DDSI_022");
        } catch (SQLException se) {
            String mensaje = ("codigo: " + se.getErrorCode() +
                " SQL: " + se.getSQLState()+
                " Texto :" + se.getMessage());
            vc.mensajeConsola("Atención, se ha producido un error con ", mensaje);
        }   

	this.showMetaData();
    }

    public void Desconectar() {
        try{
        conexion.desconexion(); 
        } catch (SQLException e) {
                vc.mensajeConsola("Error al desconectar la base de datos: ", e.toString());
        }
    }
	
    DatabaseMetaData getMetaData(){
            return this.conexion.informacionDB();
    }

    void showMetaData(){
        this.vc.mensajeMetadatos(this.getMetaData());
    }
    
    private void addListeners() {
        vLogin.TwitterButton.addActionListener(this);
        vLogin.AcceptButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Conectar": {
                Conectar();
                if (conexion != null){
                    vc.mensajeConsola("Se ha conectado con exito");
                    vLogin.dispose();
                    break;
                }
            }
            case "Salir":{
                vc.mensajeConsola("Se ha salido con exito");
                vLogin.dispose();
                System.exit(0);
                break;
            }
        }
    }
}
