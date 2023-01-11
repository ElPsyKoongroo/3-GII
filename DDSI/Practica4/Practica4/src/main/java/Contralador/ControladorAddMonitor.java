/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;

import Modelo.Conexion;
import Modelo.Monitor;
import Modelo.MariaMonitorDAO;
import Modelo.IMonitorDAO;
import Vista.VistaAddMonitor;
import Vista.VistaMensajes;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author sergio
 */
public class ControladorAddMonitor {

    private ActionListener vAddMonitorListener;
    private VistaAddMonitor vaddMonitor;
    private IMonitorDAO mDAO;

    // Because Java enums **** lets agree on: 
    // dataBase = true (MARIADB)
    // dataBase = false (ORACLE)
    private boolean dataBase;



    // If placeHolder is not null that means that this GUI was called
    // from Update Monitor which means that we will do mDAO.updateMonitor()
    // instead of mDAO.addMonitor()
    private Monitor placeHolder = null;

    /**  
    * 
    *  <h3> Add Monitor constructor ! </h3>
    * 
    */
    public ControladorAddMonitor(Frame parent, Conexion con) {
        this.mDAO = new MariaMonitorDAO(con);
        this.vaddMonitor = new VistaAddMonitor(parent, true);
        this.vAddMonitorListener = (ActionEvent e) -> actionPerformedMonitor(e);

        this.addListeners();

        this.vaddMonitor.setVisible(true);
    }
    

    /**  
    * 
    *  <h3> Update Monitor constructor ! </h3>
    * 
    */
    public ControladorAddMonitor(Frame parent, Conexion con, Monitor m) {
        this.mDAO = new MariaMonitorDAO(con);
        this.vaddMonitor = new VistaAddMonitor(parent, true);
        this.vAddMonitorListener = (ActionEvent e) -> actionPerformedMonitor(e);
        this.placeHolder = m;
        this.setPlaceHolder();
        this.addListeners();

        
        // Cuando se cambia el texto tambien se cambia el Action Command !!!
        this.vaddMonitor.InsertButton.setText("Actualizar");

        this.vaddMonitor.setVisible(true);
    }
    
    public void setPlaceHolder(){
        this.vaddMonitor.CodeTextBox.setText(this.placeHolder.getCodigoMonitor());
        this.vaddMonitor.CodeTextBox.setEnabled(false);
        this.vaddMonitor.DniTextBox.setText(this.placeHolder.getDni());
        this.vaddMonitor.TelTextBox.setText(this.placeHolder.getTelefono());
        this.vaddMonitor.EmailTextBox.setText(this.placeHolder.getCorreo());
        
        this.vaddMonitor.DateChoose.setDate(new Date(this.placeHolder.getFechaEntrada()));
        
        
        this.vaddMonitor.NickTextBox.setText(this.placeHolder.getNick());
        this.vaddMonitor.NameTextBox.setText(this.placeHolder.getNombre());
    }
    
    private void addListeners() {
        this.vaddMonitor.CancelButton.addActionListener(vAddMonitorListener);
        this.vaddMonitor.InsertButton.addActionListener(vAddMonitorListener);
    }

    private void getMonitorData() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
        String args[] = new String[7];
        args[MariaMonitorDAO.CODIGO - 1] = this.vaddMonitor.CodeTextBox.getText();
        args[MariaMonitorDAO.NOMBRE - 1] = this.vaddMonitor.NameTextBox.getText();
        args[MariaMonitorDAO.DNI - 1] = this.vaddMonitor.DniTextBox.getText();
        args[MariaMonitorDAO.TEL - 1] = this.vaddMonitor.TelTextBox.getText();
        args[MariaMonitorDAO.CORREO - 1] = this.vaddMonitor.EmailTextBox.getText();
        args[MariaMonitorDAO.FENTRADA - 1] = formatoFecha.format(this.vaddMonitor.DateChoose.getDate());
        args[MariaMonitorDAO.NICK - 1] = this.vaddMonitor.NickTextBox.getText();

        for (String value : args) {
            if (value == null) {
                return;
            }
        }

        Monitor m = new Monitor(
                args[MariaMonitorDAO.CODIGO - 1],
                args[MariaMonitorDAO.NOMBRE - 1],
                args[MariaMonitorDAO.DNI - 1],
                args[MariaMonitorDAO.TEL - 1],
                args[MariaMonitorDAO.CORREO - 1],
                args[MariaMonitorDAO.FENTRADA - 1],
                args[MariaMonitorDAO.NICK - 1]
        );

        VistaMensajes v = new VistaMensajes();
        
        try {

            // If there is a place holder that means we are updating 
            // not adding.
            if (placeHolder != null)
                this.mDAO.updateMonitor(m);
            else 
                this.mDAO.añadeMonitor(m);

            v.ShowMessage("Monitor añadido/actualizado correctamente", JOptionPane.INFORMATION_MESSAGE);
            this.vaddMonitor.dispose();
        } catch (SQLException error) {
            v.ShowMessage("Error al añadir/actualizar monitor", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionPerformedMonitor(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Cancelar": {
                System.out.println("Cerrando");
                this.vaddMonitor.dispose();
                break;
            }

            case "Actualizar": {
                System.out.println("Dropping !");
            }
            
            case "Insertar": {
                this.getMonitorData();
                break;
            }

            default: {
                System.out.println("Que has hecho para llegar aqui viajero ?");
            }

        }
    }
}
