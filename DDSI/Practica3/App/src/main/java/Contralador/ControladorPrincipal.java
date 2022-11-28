/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;

import Modelo.Conexion;
import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ElPsy
 */
public class ControladorPrincipal {

    private Conexion con;
    private VistaPrincipal vPrincipal;
    private VistaMonitor vMonitor;
    private JMenuBar barrita;
    private JMenu socios;
    private JMenu monitores;
    private final String actionMonitores = "Gestión de monitores";
    private final String actionSocios = "Gestión de socios";
    private ActionListener vPrinListener;
    private ActionListener vSocioListener;
    private ActionListener vMonitorListener;

    private DefaultTableModel modeloTablaMonitores = new DefaultTableModel();
    private DefaultTableModel modeloTablaSocios = new DefaultTableModel();

    private CardLayout crd;

    public ControladorPrincipal(Conexion con) {
        this.vMonitorListener = (ActionEvent e) -> actionPerformedMonitor(e);
        this.vSocioListener = (ActionEvent e) -> actionPerformedSocio(e);
        this.vPrinListener = (ActionEvent e) -> actionPerformedPrincipal(e);
        this.con = con;
        this.vPrincipal = new VistaPrincipal();
        this.buildMenu();

        this.vMonitor = new VistaMonitor();
        this.vMonitor.setVisible(true);
        this.vMonitor.jTableMonitores.setVisible(true);

        //this.vPrincipal.jGestionLabel.setText(this.actionMonitores);
        this.vPrincipal.setLocationRelativeTo(null);
        this.vPrincipal.setJMenuBar(this.barrita);

        this.crd = new CardLayout();

        this.vPrincipal.getContentPane().setLayout(crd);

        this.modeloTablaMonitores = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.modeloTablaSocios = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        addListeners();
        this.vPrincipal.setVisible(true);

        this.setMonitoresCard();

    }

    private void buildMenu() {
        this.barrita = new JMenuBar();
        this.monitores = new JMenu("Monitores");
        this.monitores.add(actionMonitores);
        this.socios = new JMenu("Socios");
        this.socios.add(actionSocios);
        this.barrita.add(this.monitores);
        this.barrita.add(this.socios);
    }

    private void addListeners() {
        //TODO! Add Update Monitor/Socio
        this.vPrincipal.jButtonNewMonitor.addActionListener(vMonitorListener);
        this.vPrincipal.jButtonDeleteMonitor.addActionListener(vMonitorListener);

        this.vPrincipal.jButtonNewSocio.addActionListener(vSocioListener);
        this.vPrincipal.jButtonDeleteSocio.addActionListener(vSocioListener);

        this.vPrincipal.ButtonCerrar.addActionListener(vPrinListener);
        this.vPrincipal.ButtonCerrar1.addActionListener(vPrinListener);

        for (int i = 0; i < this.monitores.getItemCount(); i++) {
            this.monitores.getItem(i).addActionListener(vPrinListener);
        }

        for (int i = 0; i < this.socios.getItemCount(); i++) {
            this.socios.getItem(i).addActionListener(vPrinListener);
        }
    }

    private void drawMonitoresTable() {
        String[] columns = {"Codigo", "Nombre", "DNI", "Telefono",
            "Correo", "FechaEntrada", "Nick"};

        this.modeloTablaMonitores.setColumnIdentifiers(columns);

        this.vPrincipal.jTableMonitores.getTableHeader().setReorderingAllowed(false);
        this.vPrincipal.jTableMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        int[] column_sizes = {40, 240, 70, 70, 200, 150, 60};

        for (int i = 0; i < column_sizes.length; i++) {
            this.vPrincipal.jTableMonitores.getColumnModel().getColumn(i).setPreferredWidth(column_sizes[i]);
        }
    }

    private void drawSociosTable() {
        String[] columns = {"Num Socio", "Nombre", "DNI", "FechaNacimiento", "Telefono",
            "Correo", "FechaEntrada", "Categoria"};

        this.modeloTablaSocios.setColumnIdentifiers(columns);

        this.vPrincipal.jTableSocios.getTableHeader().setReorderingAllowed(false);
        this.vPrincipal.jTableSocios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        int[] column_sizes = {40, 240, 70, 70, 200, 150, 60};

        for (int i = 0; i < column_sizes.length; i++) {
            this.vPrincipal.jTableSocios.getColumnModel().getColumn(i).setPreferredWidth(column_sizes[i]);
        }
    }

    private void fillMonitorTable(DefaultTableModel table, ArrayList<Monitor> content) {
        Object[] fila = new Object[7];
        var lenght = content.size();
        for (int i = 0; i < lenght; i++) {
            fila[0] = content.get(i).getCodigoMonitor();
            fila[1] = content.get(i).getNombre();
            fila[2] = content.get(i).getDni();
            fila[3] = content.get(i).getTelefono();
            fila[4] = content.get(i).getCorreo();
            fila[5] = content.get(i).getFechaEntrada();
            fila[6] = content.get(i).getNick();
            table.addRow(fila);
        }
    }

    private void fillSocioTable(DefaultTableModel table, ArrayList<Socio> content) {
        Object[] fila = new Object[8];
        var lenght = content.size();
        for (int i = 0; i < lenght; i++) {
            fila[0] = content.get(i).getNumeroSocio();
            fila[1] = content.get(i).getNombre();
            fila[2] = content.get(i).getDni();
            fila[3] = content.get(i).getFechaNacimiento();
            fila[4] = content.get(i).getTelefono();
            fila[5] = content.get(i).getCorreo();
            fila[6] = content.get(i).getFechaEntrada();
            fila[7] = content.get(i).getCategoria();
            table.addRow(fila);
        }
    }

    private void getMonitores() throws SQLException {
        ArrayList<Monitor> m = new MonitorDAO(this.con).listaMonitores();
        this.clearTable(this.modeloTablaMonitores);
        this.fillMonitorTable(this.modeloTablaMonitores, m);
    }

    private void getSocios() throws SQLException {
        ArrayList<Socio> s = new SocioDAO(this.con).listaSocio();
        this.clearTable(this.modeloTablaSocios);
        this.fillSocioTable(this.modeloTablaSocios, s);
    }

    private void clearTable(DefaultTableModel table) {
        while (table.getRowCount() > 0) {
            table.removeRow(0);
        }
    }

    private void setMonitoresCard() {
        System.out.println("Cambiando estilo");
        this.drawMonitoresTable();

        try {
            this.getMonitores();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los monitores");
        }
        this.vPrincipal.jTableMonitores.setModel(this.modeloTablaMonitores);
        this.crd.last(this.vPrincipal.getContentPane());
    }

    private void setSociosCard() {
        this.drawSociosTable();
        try {
            this.getSocios();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los socios");
        }
        this.vPrincipal.jTableSocios.setModel(this.modeloTablaSocios);
        this.crd.first(this.vPrincipal.getContentPane());
    }

    private void actionPerformedPrincipal(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "CerrarCommand": {
                this.vPrincipal.dispose();
                System.exit(0);
                break;
            }
            case actionMonitores: {
                this.setMonitoresCard();
                break;
            }

            case actionSocios: {
                this.setSociosCard();
                break;
            }
            default: {
                System.out.println(e.getActionCommand());
                System.out.println("¿ Qué has hecho para llegar aqui viajero ?");
            }
        }
    }
    
    private void eliminaMonitor(){
        int selected_row = this.vPrincipal.jTableMonitores.getSelectedRow();
        String id_monitor = (String) this.vPrincipal.jTableMonitores.getValueAt(selected_row, 0);
        MonitorDAO temp_dao = new MonitorDAO(this.con);
        try {
            System.out.println("Eliminando monitor con codigo: " + id_monitor);
            temp_dao.eliminaMonitor(id_monitor);
        } catch (SQLException err) {
            VistaMensajes v = new VistaMensajes();
            v.ShowConectionMessage(
                "Error al eliminar el monitor seleccionado", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actionPerformedMonitor(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ButtonNewMonitor": {
                new ControladorAddMonitor(this.vPrincipal, this.con);
                break;
            }

            case "ButtonRemoveMonitor": {
                System.out.println("Eliminando Monitor");
                this.eliminaMonitor();
                break;
            }

            case "ButtonUpdateMonitor": {
                System.out.println("Actualizando Monitor");
                new ControladorAddMonitor(this.vPrincipal, this.con);
            }

            default: {
                this.defaultCase();
            }
        }
    }

    private void eliminaSocio(){
        int selected_row = this.vPrincipal.jTableSocios.getSelectedRow();
        String id_monitor = (String) this.vPrincipal.jTableSocios.getValueAt(selected_row, 0);
        SocioDAO temp_dao = new SocioDAO(this.con);
        try {
            System.out.println("Eliminando socio con codigo: " + id_monitor);
            temp_dao.eliminaSocio(id_monitor);
        } catch (SQLException err) {
            VistaMensajes v = new VistaMensajes();
            v.ShowConectionMessage(
                "Error al eliminar el socio seleccionado", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actionPerformedSocio(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ButtonNewSocio": {
                new ControladorAddSocio(this.vPrincipal, this.con);
                break;
            }

            case "ButtonRemoveMonitor": {
                System.out.println("Eliminando Monitor");
                this.eliminaSocio();
                break;
            }

            default: {
                this.defaultCase();
            }
        }
    }

    private void defaultCase() {
        VistaMensajes v = new VistaMensajes();
        v.ShowConectionMessage("Que has hecho para llegar hasta aqui viajero ?", JOptionPane.INFORMATION_MESSAGE);
    }

}
