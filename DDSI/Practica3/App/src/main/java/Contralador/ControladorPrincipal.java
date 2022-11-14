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
import Vista.VistaMonitor;
import Vista.VistaPrincipal;
import Vista.VistaSocio;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ElPsy
 */
public class ControladorPrincipal implements ActionListener {

    private Conexion con;
    private VistaPrincipal vPrincipal;
    private VistaMonitor vMonitor;
    private VistaSocio vSocio;
    private JMenuBar barrita;
    private JMenu socios;
    private JMenu monitores;
    private final String actionMonitores = "Gestión de monitores";
    private final String actionSocios = "Gestión de socios";

    private DefaultTableModel modeloTablaMonitores = new DefaultTableModel();
    private DefaultTableModel modeloTablaSocios = new DefaultTableModel();

    private CardLayout crd;

    public ControladorPrincipal(Conexion con) {
        this.con = con;
        this.vPrincipal = new VistaPrincipal();

        this.buildMenu();

        this.vMonitor = new VistaMonitor();
        this.vSocio = new VistaSocio();
        this.vSocio.setVisible(true);
        this.vMonitor.setVisible(true);
        this.vMonitor.jTableMonitores.setVisible(true);

        //this.vPrincipal.jGestionLabel.setText(this.actionMonitores);
        this.vPrincipal.setLocationRelativeTo(null);
        this.vPrincipal.setJMenuBar(this.barrita);

        this.crd = new CardLayout();

        this.vPrincipal.getContentPane().setLayout(crd);

        this.drawSociosTable();
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
        this.vPrincipal.ButtonCerrar.addActionListener(this);
        this.vPrincipal.ButtonCerrar1.addActionListener(this);
        for (int i = 0; i < this.monitores.getItemCount(); i++) {
            this.monitores.getItem(i).addActionListener(this);
        }

        for (int i = 0; i < this.socios.getItemCount(); i++) {
            this.socios.getItem(i).addActionListener(this);
        }
    }

    private void drawMonitoresTable(VistaMonitor vMonitor) {
        String[] columns = {"Codigo", "Nombre", "DNI", "Telefono",
            "Correo", "FechaEntrada", "Nick"};

        this.modeloTablaMonitores.setColumnIdentifiers(columns);

        this.vPrincipal.jTableMonitores.getTableHeader().setReorderingAllowed(false);
        this.vPrincipal.jTableMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        //this.vMonitor.jTableMonitores.getTableHeader().setReorderingAllowed(false);
        //this.vMonitor.jTableMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        int[] column_sizes = {40, 240, 70, 70, 200, 150, 60};

        for (int i = 0; i < column_sizes.length; i++) {
            //this.vMonitor.jTableMonitores.getColumnModel().getColumn(i).setPreferredWidth(column_sizes[i]);
            this.vPrincipal.jTableMonitores.getColumnModel().getColumn(i).setPreferredWidth(column_sizes[i]);
        }
    }

    private void drawSociosTable() {
        String[] columns = {"Num Socio", "Nombre", "DNI", "FechaNacimiento", "Telefono",
            "Correo", "FechaEntrada", "Categoria"};

        this.modeloTablaSocios.setColumnIdentifiers(columns);

        this.vPrincipal.jTableSocios.getTableHeader().setReorderingAllowed(false);
        this.vPrincipal.jTableSocios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        //this.vSocio.jTableSocios.getTableHeader().setReorderingAllowed(false);
        //this.vSocio.jTableSocios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        int[] column_sizes = {40, 240, 70, 70, 200, 150, 60};

        for (int i = 0; i < column_sizes.length; i++) {
            this.vPrincipal.jTableSocios.getColumnModel().getColumn(i).setPreferredWidth(column_sizes[i]);
            //this.vSocio.jTableSocios.getColumnModel().getColumn(i).setPreferredWidth(column_sizes[i]);
        }
    }

    private void fillMonitorTable(DefaultTableModel table, ArrayList<Monitor> content) {
        Object[] fila = new Object[7];
        var lenght = content.size();
        for (int i = 0; i < lenght; i++) {
            fila[0] = content.get(i).getcodigoMonitor();
            fila[1] = content.get(i).getnombre();
            fila[2] = content.get(i).getdni();
            fila[3] = content.get(i).gettelefono();
            fila[4] = content.get(i).getcorreo();
            fila[5] = content.get(i).getfechaEntrada();
            fila[6] = content.get(i).getnick();
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
        ArrayList<Monitor> monitores = new MonitorDAO(this.con).listaMonitores();
        this.clearTable(this.modeloTablaMonitores);
        this.fillMonitorTable(this.modeloTablaMonitores, monitores);
    }

    private void getSocios() throws SQLException {
        ArrayList<Socio> socios = new SocioDAO(this.con).listaSocio();
        this.clearTable(this.modeloTablaSocios);
        this.fillSocioTable(this.modeloTablaSocios, socios);
    }

    private void clearTable(DefaultTableModel table) {
        while (table.getRowCount() > 0) {
            table.removeRow(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "CerrarCommand": {
                this.vPrincipal.dispose();
                System.exit(0);
                break;
            }
            case actionMonitores: {
                //this.vPrincipal.jGestionLabel.setText(this.actionMonitores);
                System.out.println("Cambiando estilo");
                this.drawMonitoresTable(this.vMonitor);

                try {
                    this.getMonitores();
                } catch (SQLException ex) {
                    System.out.println("Error al obtener los monitores");
                }
                this.vPrincipal.jTableMonitores.setModel(this.modeloTablaMonitores);
                this.crd.last(this.vPrincipal.getContentPane());
                break;
            }

            case actionSocios: {
                //this.vPrincipal.jGestionLabel.setText(this.actionSocios);
                this.drawSociosTable();
                try {
                    this.getSocios();
                } catch (SQLException ex) {
                    System.out.println("Error al obtener los socios");
                }
                this.vPrincipal.jTableSocios.setModel(this.modeloTablaSocios);
                this.crd.first(this.vPrincipal.getContentPane());
                break;
            }

            default: {
                System.out.println(e.getActionCommand());
                System.out.println("¿ Qué has hecho para llegar aqui viajero ?");
            }
        }
    }

}
