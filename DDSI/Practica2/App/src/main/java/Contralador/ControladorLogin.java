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
    private VistaMensajes vMensajes;
    
    public ControladorLogin() {
        this.vc = new VistaConsola();
        this.vLogin = new VistaLogin();
        this.vMensajes = new VistaMensajes();
        
        this.addListeners();
        this.vLogin.setLocationRelativeTo(null);
        this.vLogin.setVisible(true);
        //this.Conectar();
    }

    private void Conectar() throws SQLException {
        String ip = this.vLogin.IpTextBox.getText().trim();         
        String server = ((String) this.vLogin.ServerComboBox.getSelectedItem()).trim();
        String server_bd = this.vLogin.BDTextBox.getText().trim();
        String user = this.vLogin.UserTextBox.getText().trim();
        String password = new String (this.vLogin.PassTextBox.getPassword()).trim();
        this.conexion = new Conexion(server, ip, server_bd, user, password);   

	this.showMetaData();
    }

    public void Desconectar() {
        conexion.desconexion();
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
        
        vMensajes.OkButton.addActionListener(this);
    }
    
    /*
    private void () throws Exception {
        // "mariadb", "172.18.1.241:3306", "DDSI_022", "DDSI_022", "DDSI_022"
        String ip = this.vLogin.IpTextBox.getText();         
        String server = (String) this.vLogin.ServerComboBox.getSelectedItem();
        String server_bd = this.vLogin.BDTextBox.getText();
        String user = this.vLogin.UserTextBox.getText();
        String password = new String (this.vLogin.PassTextBox.getPassword());
        this.conexion = new Conexion(server, ip, server_bd, user, password);
    }
    */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Conectar": {
                //this.conexion.desconexion();
                try {
                    Conectar();
                    this.vc.mensajeConsola("Se ha conectado con exito");
                    //this.vLogin.dispose();
                    this.vMensajes.Informasio.setText("Conexión Correcta");
                } catch (SQLException exception) {
                     this.vMensajes.Informasio.setText("Error en la conexión: " + exception);
                } finally {
                     this.vMensajes.setVisible(true);                    
                };
                break;
            }
            case "Salir":{
                vc.mensajeConsola("Se ha salido con exito");
                vLogin.dispose();
                try {
                    this.conexion.desconexion();
                } catch (Exception _unused) {
                }
                System.exit(0);
                break;
            }
            
            case "OK": {
                this.vMensajes.dispose();
                break;
            }
        }
    }
}
