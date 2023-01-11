/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;

import Modelo.IMonitorDAO;
import Modelo.MariaMonitorDAO;
import Modelo.MariaSocioDAO;
import Modelo.Monitor;
import Modelo.Socio;
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
import org.hibernate.Session;

/**
 *
 * @author ElPsy
 */
public class ControladorPrincipal {

    private Session sesion;
    private VistaPrincipal vPrincipal;

    private MonitorPanelLayout monitorPanel;
    private SocioPanelLayout socioPanel;

    private JMenuBar barrita;
    private JMenu socios;
    private JMenu monitores;
    private JMenu actividades;

    private final String actionMonitores = "Gestión de monitores";
    private final String actionSocios = "Gestión de socios";
    private final String actionActividades = "Gestión de actividades";

    private ActionListener vPrinListener;
    private ActionListener vSocioListener;
    private ActionListener vMonitorListener;
    private DefaultTableModel modeloTablaMonitores = new DefaultTableModel();
    private DefaultTableModel modeloTablaSocios = new DefaultTableModel();

    private CardLayout crd;

    private String dataBase;

    public ControladorPrincipal(Session sesion, String server) {
        this.vMonitorListener = (ActionEvent e) -> actionPerformedMonitor(e);
        this.vSocioListener = (ActionEvent e) -> actionPerformedSocio(e);
        this.vPrinListener = (ActionEvent e) -> actionPerformedPrincipal(e);
        this.vPrincipal = new VistaPrincipal();
        this.sesion = sesion;
        this.dataBase = server;

        this.buildMenu();
        this.setCardsLayout();
        this.addListeners();

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

        this.vPrincipal.setVisible(true);

        this.vPrincipal.setLocationRelativeTo(null);

        this.setMonitoresCard();

    }

    private void setCardsLayout() {
        // TODO! Remove this panels from Vista Principal
        this.vPrincipal.jSociosPanel.setVisible(false);
        this.vPrincipal.jMonitoresPanel.setVisible(false);

        this.monitorPanel = new MonitorPanelLayout();
        this.socioPanel = new SocioPanelLayout();

        this.monitorPanel.setVisible(true);
        this.socioPanel.setVisible(true);

        this.crd = new CardLayout();
        this.crd.addLayoutComponent(this.monitorPanel, actionMonitores);
        this.crd.addLayoutComponent(this.socioPanel, actionSocios);

        this.vPrincipal.add(this.monitorPanel);
        this.vPrincipal.add(this.socioPanel);
        this.vPrincipal.getContentPane().setLayout(crd);
    }

    private void buildMenu() {
        this.barrita = new JMenuBar();
        this.monitores = new JMenu("Monitores");
        this.monitores.add(actionMonitores);

        this.socios = new JMenu("Socios");
        this.socios.add(actionSocios);

        this.actividades = new JMenu("Actividades");
        this.actividades.add(actionActividades);

        this.barrita.add(this.monitores);
        this.barrita.add(this.socios);
        this.barrita.add(this.actividades);

        this.vPrincipal.setJMenuBar(this.barrita);
    }

    private void addListeners() {
        //TODO! Add Update Monitor/Socio
        this.monitorPanel.jButtonNewMonitor.addActionListener(vMonitorListener);
        this.monitorPanel.jButtonDeleteMonitor.addActionListener(vMonitorListener);
        this.monitorPanel.jButtonUpdateMonitor.addActionListener(vMonitorListener);

        this.socioPanel.jButtonNewSocio.addActionListener(vSocioListener);
        this.socioPanel.jButtonDeleteSocio.addActionListener(vSocioListener);
        this.socioPanel.jButtonUpdateSocio.addActionListener(vSocioListener);
        this.socioPanel.jButtonConfigureSocio.addActionListener(vSocioListener);

        this.monitorPanel.ButtonCerrar.addActionListener(vPrinListener);
        this.socioPanel.ButtonCerrar1.addActionListener(vPrinListener);

        for (int i = 0; i < this.monitores.getItemCount(); i++) {
            this.monitores.getItem(i).addActionListener(vPrinListener);
        }

        for (int i = 0; i < this.socios.getItemCount(); i++) {
            this.socios.getItem(i).addActionListener(vPrinListener);
        }

        for (int i = 0; i < this.actividades.getItemCount(); i++) {
            this.actividades.getItem(i).addActionListener(vPrinListener);
        }
    }

    private void clearTable(DefaultTableModel table) {
        while (table.getRowCount() > 0) {
            table.removeRow(0);
        }
    }

    //
    //  /=====================================\
    //  |   ACTION LISTENERS ABOUT METHODS    |
    //  \=====================================/
    //
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

            case actionActividades: {
                System.out.println("Actividades");
                
                new ControladorActividades(this.vPrincipal, this.sesion, this.dataBase);
                

                break;
            }

            default: {
                System.out.println(e.getActionCommand());
                System.out.println("¿ Qué has hecho para llegar aqui viajero ?");
            }
        }
    }

    private void actionPerformedMonitor(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ButtonNewMonitor": {
                new ControladorAddMonitor(this.vPrincipal, this.sesion);
                break;
            }

            case "ButtonRemoveMonitor": {
                int selected_row = this.vPrincipal.jTableSocios.getSelectedRow();

                VistaMensajes v = new VistaMensajes();
                if (selected_row == -1) {
                    v.ShowMessage("Seria recomendable seleccionar uno", JOptionPane.ERROR_MESSAGE);
                    break;
                } else {
                    Integer status = v.ShowConfirm("Seguro ?", JOptionPane.ERROR_MESSAGE);
                    // 0 Es que ha confirmado
                    if (status == 0) this.eliminaMonitor();
                }

                break;
            }

            case "ButtonUpdateMonitor": {
                System.out.println("Actualizando Monitor");
                Monitor m = getMonitorFromSelectedRow();
                if (m == null){
                    new VistaMensajes().ShowMessage(
                            "Entre tú y yo, para actualizar un monitor deberia haber alguno seleccionado",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    new ControladorAddMonitor(this.vPrincipal, this.sesion, m);
                }
                break;
            }

            default: {
                this.defaultCase();
            }
        }
        this.setMonitoresCard();
    }

    private void actionPerformedSocio(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ButtonNewSocio": {
                new ControladorAddSocio(this.vPrincipal, this.sesion);
                break;
            }

            case "ButtonRemoveSocio": {
                int selected_row = this.vPrincipal.jTableSocios.getSelectedRow();
                VistaMensajes v = new VistaMensajes();

                if (selected_row == -1) {
                    v.ShowMessage("Seria recomendable seleccionar uno", JOptionPane.ERROR_MESSAGE);
                    break;
                } else {
                    Integer status = v.ShowConfirm("Seguro ?", JOptionPane.ERROR_MESSAGE);
                    // 0 Es que ha confirmado
                    if (status == 0) this.eliminaSocio();
                }

                break;
            }
            case "ButtonUpdateSocio": {
                System.out.println("Actualizando Socio");
                Socio s = getSocioFromSelectedRow();
                if (s == null){
                    new VistaMensajes().ShowMessage(
                            "Entre tú y yo, para actualizar un socio deberia haber alguno seleccionado",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    new ControladorAddSocio(this.vPrincipal, this.sesion, s);
                }
                break;
            }
            case "ButtonConfigureActivity": {
                System.out.println("Configurando Socio");
                Socio s = getSocioFromSelectedRow();
                if (s == null){
                    new VistaMensajes().ShowMessage(
                            "Entre tú y yo, para actualizar un socio deberia haber alguno seleccionado",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    new ControladorConfiguracionActividad(this.vPrincipal, this.sesion, s, this.dataBase); //Controlador GestionActividad
                }
                break;
            }

            default: {
                this.defaultCase();
            }
        }
        this.setSociosCard();
    }

    private void defaultCase() {
        VistaMensajes v = new VistaMensajes();
        v.ShowMessage("Que has hecho para llegar hasta aqui viajero ?", JOptionPane.INFORMATION_MESSAGE);
    }

    //
    //  /=====================================\
    //  |       MONITOR ABOUT METHODS         |
    //  \=====================================/
    //
    private void fillMonitorTable(DefaultTableModel table, ArrayList<Monitor> content) {
        Object[] fila = new Object[7];
        var lenght = content.size();
        for (int i = 0; i < lenght; i++) {
            fila[0] = content.get(i).getCodmonitor();
            fila[1] = content.get(i).getNombre();
            fila[2] = content.get(i).getDni();
            fila[3] = content.get(i).getTelefono();
            fila[4] = content.get(i).getCorreo();
            fila[5] = content.get(i).getFechaentrada();
            fila[6] = content.get(i).getNick();
            table.addRow(fila);
        }
    }

    private void drawMonitoresTable() {

        int[] column_sizes = {40, 240, 70, 70, 200, 150, 60};

        for (int i = 0; i < column_sizes.length; i++) {
            this.monitorPanel.jTableMonitores.getColumnModel().getColumn(i).setPreferredWidth(column_sizes[i]);
        }

        String[] columns = {"Codigo", "Nombre", "DNI", "Telefono",
            "Correo", "FechaEntrada", "Nick"};

        this.modeloTablaMonitores.setColumnIdentifiers(columns);
        this.monitorPanel.jTableMonitores.getTableHeader().setReorderingAllowed(false);
        this.monitorPanel.jTableMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

    }

    private void getMonitores() throws SQLException {
        ArrayList<Monitor> m = new MariaMonitorDAO(this.sesion).listaMonitores();
        this.clearTable(this.modeloTablaMonitores);
        this.fillMonitorTable(this.modeloTablaMonitores, m);
    }

    private void setMonitoresCard() {
        System.out.println("Cambiando estilo");
        this.drawMonitoresTable();

        try {
            this.getMonitores();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los monitores");
        }
        this.monitorPanel.jTableMonitores.setModel(this.modeloTablaMonitores);
        this.crd.show(this.vPrincipal.getContentPane(), actionMonitores);
        //this.crd.last(this.vPrincipal.getContentPane());
    }

    private void eliminaMonitor() {
        int selected_row = this.monitorPanel.jTableMonitores.getSelectedRow();
        String id_monitor = (String) this.monitorPanel.jTableMonitores.getValueAt(selected_row, 0);
        MariaMonitorDAO temp_dao = new MariaMonitorDAO(this.sesion);
        
        System.out.println("Eliminando monitor con codigo: " + id_monitor);
        temp_dao.eliminaMonitor(id_monitor);
        
    }

    private Monitor getMonitorFromSelectedRow() {
        int selected_row = this.monitorPanel.jTableMonitores.getSelectedRow();
        return selected_row == -1 ? null : new Monitor(
                (String) this.monitorPanel.jTableMonitores.getValueAt(selected_row, IMonitorDAO.CODIGO - 1),
                (String) this.monitorPanel.jTableMonitores.getValueAt(selected_row, IMonitorDAO.NOMBRE - 1),
                (String) this.monitorPanel.jTableMonitores.getValueAt(selected_row, IMonitorDAO.DNI - 1),
                (String) this.monitorPanel.jTableMonitores.getValueAt(selected_row, IMonitorDAO.TEL - 1),
                (String) this.monitorPanel.jTableMonitores.getValueAt(selected_row, IMonitorDAO.CORREO - 1),
                (String) this.monitorPanel.jTableMonitores.getValueAt(selected_row, IMonitorDAO.FENTRADA - 1),
                (String) this.monitorPanel.jTableMonitores.getValueAt(selected_row, IMonitorDAO.NICK - 1)
        );
    }

    //
    //  /=====================================\
    //  |         SOCIO ABOUT METHODS         |
    //  \=====================================/
    //
    private void fillSocioTable(DefaultTableModel table, ArrayList<Socio> content) {
        Object[] fila = new Object[8];
        var lenght = content.size();
        for (int i = 0; i < lenght; i++) {
            fila[0] = content.get(i).getNumerosocio();
            fila[1] = content.get(i).getNombre();
            fila[2] = content.get(i).getDni();
            fila[3] = content.get(i).getFechanacimiento();
            fila[4] = content.get(i).getTelefono();
            fila[5] = content.get(i).getCorreo();
            fila[6] = content.get(i).getFechaentrada();
            fila[7] = content.get(i).getCategoria();
            table.addRow(fila);
        }
    }

    private void getSocios() throws SQLException {
        ArrayList<Socio> s = new MariaSocioDAO(this.sesion).listaSocio();
        this.clearTable(this.modeloTablaSocios);
        this.fillSocioTable(this.modeloTablaSocios, s);
    }

    private void setSociosCard() {
        this.drawSociosTable();
        try {
            this.getSocios();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los socios");
        }
        this.socioPanel.jTableSocios.setModel(this.modeloTablaSocios);
        //this.crd.show(this.vPrincipal.getContentPane(), "jSociosPanel");
        this.crd.show(this.vPrincipal.getContentPane(), actionSocios);
    }

    private void drawSociosTable() {
        int[] column_sizes = {40, 240, 70, 70, 200, 150, 60};

        for (int i = 0; i < column_sizes.length; i++) {
            this.socioPanel.jTableSocios.getColumnModel().getColumn(i).setPreferredWidth(column_sizes[i]);
        }

        String[] columns = {"Num Socio", "Nombre", "DNI", "FechaNacimiento", "Telefono",
            "Correo", "FechaEntrada", "Categoria"};

        this.modeloTablaSocios.setColumnIdentifiers(columns);

        this.socioPanel.jTableSocios.getTableHeader().setReorderingAllowed(false);
        this.socioPanel.jTableSocios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    private boolean eliminaSocio() {
        int selected_row = this.vPrincipal.jTableSocios.getSelectedRow();
        if (selected_row == -1) return false;
        String id_monitor = (String) this.vPrincipal.jTableSocios.getValueAt(selected_row, 0);

        MariaSocioDAO temp_dao = new MariaSocioDAO(this.sesion);

        
        System.out.println("Eliminando socio con codigo: " + id_monitor);
        temp_dao.eliminaSocio(id_monitor);
        return true;
    }

    /// Si no hay nada seleccionado devuelve null, maybe deveriamos cambiarlo
    private Socio getSocioFromSelectedRow() {
        int selected_row = this.socioPanel.jTableSocios.getSelectedRow();
        return selected_row == -1 ? null : new Socio(
                (String) this.socioPanel.jTableSocios.getValueAt(selected_row, MariaSocioDAO.NUMSOCIO - 1),
                (String) this.socioPanel.jTableSocios.getValueAt(selected_row, MariaSocioDAO.NOMBRE - 1),
                (String) this.socioPanel.jTableSocios.getValueAt(selected_row, MariaSocioDAO.DNI - 1),
                (String) this.socioPanel.jTableSocios.getValueAt(selected_row, MariaSocioDAO.FNACIMIENTO - 1),
                (String) this.socioPanel.jTableSocios.getValueAt(selected_row, MariaSocioDAO.TEL - 1),
                (String) this.socioPanel.jTableSocios.getValueAt(selected_row, MariaSocioDAO.CORREO - 1),
                (String) this.socioPanel.jTableSocios.getValueAt(selected_row, MariaSocioDAO.FENTRADA - 1),
                (Character)this.socioPanel.jTableSocios.getValueAt(selected_row, MariaSocioDAO.CATEGORIA - 1)
        );
    }
}
