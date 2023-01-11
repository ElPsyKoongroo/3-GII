package Contralador;

import Modelo.*;
import Vista.VistaActividades;
import Vista.VistaAddMonitor;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorActividades {
    private ActionListener vActividadesListener;
    private VistaActividades vActividades;
    private IActividadDAO actividadDAO;
    private ArrayList<Actividad> actividades;


    ControladorActividades(Frame parent, Conexion con, String dataBase) throws SQLException{
        if (dataBase.equals("mariadb")) {
            this.actividadDAO = new MariaActividadDAO(con);
        } else {
            this.actividadDAO = new OracleActividadDAO(con); // Cambiar
        }
        this.vActividades = new VistaActividades(parent, true);
        this.vActividadesListener = (ActionEvent e) -> actionPerformedActividad(e);
        this.addListeners();
        
        this.PopulateActividades();

        this.vActividades.setVisible(true);
    }

    private void addListeners(){
        this.vActividades.SalirButton.addActionListener(this.vActividadesListener);
        this.vActividades.SociosInscritosButton.addActionListener(this.vActividadesListener);
    }

    private void rellenaTabla() throws SQLException {
        
        int index = vActividades.ActividadesComboBox.getSelectedIndex();
        
        String idActividad = actividades.get(index).getidActividad();
        
        ArrayList<Object[]> socios = this.actividadDAO.getSocioByActividad(idActividad);
        System.out.println("Socios : " + socios);
        for(int i = 0; i<socios.size(); ++i) {
            Object[] fila = new Object[2];
            fila[0] = socios.get(i)[0];
            fila[1] = socios.get(i)[1];
            ((DefaultTableModel)this.vActividades.UsuariosJTable.getModel()).addRow(fila);
        }
    }

    private void vaciaTabla(){
        while (this.vActividades.UsuariosJTable.getRowCount() > 0) {
            ((DefaultTableModel)this.vActividades.UsuariosJTable.getModel()).removeRow(0);
        }
    }
    private void actionPerformedActividad(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Salir": {
                this.vActividades.dispose();
                break;
            }

            case "SociosInscritos": {
                try {
                    vaciaTabla();
                    this.rellenaTabla();
                } catch (Exception _e) {
                }
                break;
            }
            default: {
                System.out.println(e.getActionCommand());
            }
        }
    }

    private void PopulateActividades() throws SQLException {
        actividades = this.actividadDAO.getActividades();
        
        for(var actividad: actividades)
        {
            vActividades.ActividadesComboBox.addItem(actividad.getnombre());
        }
    }
}
