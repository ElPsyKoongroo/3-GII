/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
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

    private String dataBaseType;

    public ControladorLogin() {
        this.vc = new VistaConsola();
        this.vLogin = new VistaLogin();
        this.vMensajes = new VistaMensajes();

        this.addListeners();
        this.vLogin.setLocationRelativeTo(null);
        FillStandardInputs(true);
        this.vLogin.setVisible(true);
    }

    private void Conectar() throws SQLException {
        String ip = this.vLogin.IpTextBox.getText().trim();
        String server = ((String) this.vLogin.ServerComboBox.getSelectedItem()).trim();
        String server_bd = this.vLogin.BDTextBox.getText().trim();
        String user = this.vLogin.UserTextBox.getText().trim();
        String password = new String(this.vLogin.PassTextBox.getPassword()).trim();
        this.conexion = new Conexion(server, ip, server_bd, user, password);

        if (server.equals("mariadb")) {
            this.dataBaseType = "mariadb";
        } else {
            this.dataBaseType = "oracle";
        }
        // this.showMetaData();
    }

    public void Desconectar() {
        conexion.desconexion();
    }

    DatabaseMetaData getMetaData() {
        return this.conexion.informacionDB();
    }

    void showMetaData() {
        this.vc.mensajeMetadatos(this.getMetaData());
    }

    private void addListeners() {
        vLogin.TwitterButton.addActionListener(this);
        vLogin.AcceptButton.addActionListener(this);
        vLogin.ServerComboBox.addActionListener(this);
    }

    private void FillStandardInputs(boolean initializate) {
        if (!initializate) {
            int result = JOptionPane.showConfirmDialog(
                    vLogin,
                    "¿Rellenar con datos predeterminados?",
                    "Elige una opcion",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.OK_OPTION) {
                return;
            }
        }

        switch ((String) vLogin.ServerComboBox.getSelectedItem()) {
            case "mariadb": {
                vLogin.IpTextBox.setText("172.18.1.241:3306");
                vLogin.BDTextBox.setText("DDSI_022");
                vLogin.UserTextBox.setText("DDSI_022");
                vLogin.PassTextBox.setText("95AS3E");
                break;
            }
            case "oracle": {
                vLogin.IpTextBox.setText("172.17.20.39:1521");
                vLogin.BDTextBox.setText("etsi");
                vLogin.UserTextBox.setText("DDSI_");
                break;
            }
        }
        vLogin.PassTextBox.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Conectar": {
                try {
                    Conectar();
                    this.vc.mensajeConsola("Se ha conectado con exito");
                    this.vMensajes.ShowMessage("Conectado con exito", JOptionPane.INFORMATION_MESSAGE);
                    vLogin.dispose();
                    new ControladorPrincipal(this.conexion, this.dataBaseType);

                } catch (SQLException exception) {
                    this.vc.mensajeConsola("Error al conectarse");
                    this.vMensajes.ShowMessage(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "Salir": {
                vc.mensajeConsola("Se ha salido con exito");
                try {
                    this.conexion.desconexion();
                } catch (Exception _unused) {
                }

                vLogin.dispose();
                System.exit(0);
            }
            case "comboBoxChanged": {
                FillStandardInputs(false);
            }
        }
    }
}
