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

public class ControladorActividades {
    private ActionListener vActividadesListener;
    private VistaActividades vActividades;
    private IActividadDAO actividadDAO;


    ControladorActividades(Frame parent, Conexion con, String dataBase){
        if (dataBase.equals("mariadb")) {
            this.actividadDAO = new MariaActividadDAO(con);
        } else {
            this.actividadDAO = new MariaActividadDAO(con); // Cambiar
        }
        this.vActividades = new VistaActividades(parent, true);
        this.vActividadesListener = (ActionEvent e) -> actionPerformedActividad(e);
        this.addListeners();

        this.vActividades.setVisible(true);
    }

    private void addListeners(){
        this.vActividades.SalirButton.addActionListener(this.vActividadesListener);
        this.vActividades.SociosInscritosButton.addActionListener(this.vActividadesListener);
    }

    private void rellenaTabla() throws SQLException {
        ArrayList<Object[]> socios = this.actividadDAO.getSocioByActividad("AC01");
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
}
