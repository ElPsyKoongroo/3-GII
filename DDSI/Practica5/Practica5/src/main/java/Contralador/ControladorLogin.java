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
import javax.swing.*;
import org.hibernate.Session;

/**
 *
 * @author ElPsy
 */
public class ControladorLogin implements ActionListener {

    public Session sesion;
    private String server;
    public VistaConsola vc;
    private VistaLogin vLogin;
    private VistaMensajes vMensajes;

    private String dataBaseType;

    public ControladorLogin() {
        this.vc = new VistaConsola();
        this.vLogin = new VistaLogin();
        this.vMensajes = new VistaMensajes();

        this.addListeners();
        this.vLogin.setLocationRelativeTo(null);
        this.vLogin.setVisible(true);
    }

    private Session Conectar() throws SQLException {
        
        server = ((String) this.vLogin.ServerComboBox.getSelectedItem()).trim();

        if (server.equals("mariadb"))
            return HibernateUtilMaria.getSessionFactory().openSession();
        return HibernateUtilOracle.getSessionFactory().openSession();
    }

    public void Desconectar() {
        sesion.disconnect();
    }


    private void addListeners() {
        vLogin.TwitterButton.addActionListener(this);
        vLogin.AcceptButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Conectar": {
                try {
                    this.sesion = Conectar();
                    
                    if(sesion == null)
                    {
                        vMensajes.ShowMessage("Error en la conexion. No se ha podido crear una sesion", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    
                    this.vc.mensajeConsola("Se ha conectado con exito");
                    this.vMensajes.ShowMessage("conexion correcta con Hibernate", JOptionPane.INFORMATION_MESSAGE);
                    vLogin.dispose();
                    new ControladorPrincipal(this.sesion, this.server);

                } catch (Exception exception) {
                    this.vc.mensajeConsola("Error al conectarse");
                    this.vMensajes.ShowMessage(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "Salir": {
                vc.mensajeConsola("Se ha salido con exito");
                try {
                    this.Desconectar();
                } catch (Exception _unused) {
                }

                vLogin.dispose();
                System.exit(0);
            }
        }
    }
}
