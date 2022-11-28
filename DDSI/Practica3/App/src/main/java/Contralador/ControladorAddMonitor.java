/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;

import Modelo.Conexion;
import Modelo.Monitor;
import Modelo.MonitorDAO;
import Vista.VistaAddMonitor;
import Vista.VistaMensajes;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sergio
 */
public class ControladorAddMonitor {

    private ActionListener vAddMonitorListener;
    private VistaAddMonitor vaddMonitor;
    private MonitorDAO mDAO;
    private Monitor placeHolder;
    public boolean success = false;

    public ControladorAddMonitor(Frame parent, Conexion con) {
        this.mDAO = new MonitorDAO(con);
        this.vaddMonitor = new VistaAddMonitor(parent, true);
        this.vAddMonitorListener = (ActionEvent e) -> actionPerformedMonitor(e);

        this.addListeners();

        this.vaddMonitor.setVisible(true);
        placeHolder = null;
    }
    
    public ControladorAddMonitor(Frame parent, Conexion con, Monitor m) {
        this.mDAO = new MonitorDAO(con);
        this.vaddMonitor = new VistaAddMonitor(parent, true);
        this.vAddMonitorListener = (ActionEvent e) -> actionPerformedMonitor(e);
        this.placeHolder = m;
        this.setPlaceHolder();
        this.vaddMonitor.InsertButton.setText("Actualizar");
        this.addListeners();

        this.vaddMonitor.setVisible(true);
    }
    
    public void setPlaceHolder(){
        this.vaddMonitor.CodeTextBox.setText(this.placeHolder.getCodigoMonitor());
        this.vaddMonitor.DniTextBox.setText(this.placeHolder.getDni());
        this.vaddMonitor.TelTextBox.setText(this.placeHolder.getTelefono());
        this.vaddMonitor.EmailTextBox.setText(this.placeHolder.getCorreo());
        this.vaddMonitor.DateTextBox.setText(this.placeHolder.getFechaEntrada());
        this.vaddMonitor.NickTextBox.setText(this.placeHolder.getNick());
        this.vaddMonitor.NameTextBox.setText(this.placeHolder.getNombre());
    }
    
    private void addListeners() {
        this.vaddMonitor.CancelButton.addActionListener(vAddMonitorListener);
        this.vaddMonitor.InsertButton.addActionListener(vAddMonitorListener);
    }

    private void getMonitorData() {
        String args[] = new String[7];
        args[MonitorDAO.CODIGO - 1] = this.vaddMonitor.CodeTextBox.getText();
        args[MonitorDAO.NOMBRE - 1] = this.vaddMonitor.NameTextBox.getText();
        args[MonitorDAO.DNI - 1] = this.vaddMonitor.DniTextBox.getText();
        args[MonitorDAO.TEL - 1] = this.vaddMonitor.TelTextBox.getText();
        args[MonitorDAO.CORREO - 1] = this.vaddMonitor.EmailTextBox.getText();
        args[MonitorDAO.FENTRADA - 1] = this.vaddMonitor.DateTextBox.getText();
        args[MonitorDAO.NICK - 1] = this.vaddMonitor.NickTextBox.getText();

        for (String value : args) {
            if (value == null) {
                return;
            }
        }

        Monitor m = new Monitor(
                args[MonitorDAO.CODIGO - 1],
                args[MonitorDAO.NOMBRE - 1],
                args[MonitorDAO.DNI - 1],
                args[MonitorDAO.TEL - 1],
                args[MonitorDAO.CORREO - 1],
                args[MonitorDAO.FENTRADA - 1],
                args[MonitorDAO.NICK - 1]
        );

        VistaMensajes v = new VistaMensajes();
        
        if (placeHolder != null){
            try{
                this.mDAO.eliminaMonitor(placeHolder.getCodigoMonitor());
            } catch (SQLException err){
            }
        }
        
        try {
            this.mDAO.añadeMonitor(m);
            v.ShowConectionMessage("Monitor añadido/actualizado correctamente", JOptionPane.INFORMATION_MESSAGE);
            this.vaddMonitor.dispose();
        } catch (SQLException error) {
            v.ShowConectionMessage("Error al añadir/actualizar monitor", JOptionPane.ERROR_MESSAGE);
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
