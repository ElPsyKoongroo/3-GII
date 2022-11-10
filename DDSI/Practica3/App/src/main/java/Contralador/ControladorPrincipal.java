/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;

import Modelo.Conexion;
import Modelo.Monitor;
import Vista.VistaMonitor;
import Vista.VistaPrincipal;
import java.awt.CardLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
    private JMenuBar barrita;
    private JMenu socios;
    private JMenu monitores;
    private final String actionMonitores = "Gestión de monitores";
    private final String actionSocios = "Gestión de socios";
    
    public ControladorPrincipal(Conexion con){
        this.con = con;
        this.vPrincipal = new VistaPrincipal();
        
        
        this.buildMenu();
        
        this.vMonitor = new VistaMonitor();
        this.vPrincipal.setLocationRelativeTo(null);
        this.vPrincipal.getContentPane().setLayout(new CardLayout());
        this.vPrincipal.add(this.vMonitor);
        this.vPrincipal.setJMenuBar(this.barrita);
        
        addListeners();
        this.vPrincipal.setVisible(true);
        
    }
    
    private void buildMenu(){
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
        for(int i = 0; i<this.monitores.getItemCount(); i++){
            this.monitores.getItem(i).addActionListener(this);
        }
        
        for(int i = 0; i<this.socios.getItemCount(); i++){
            this.socios.getItem(i).addActionListener(this);
        }
    }
    
    public void drawMonitoresTable(DefaultTableModel tableMonitor){
        String[] columns = {"Codigo", "Nombre", "DNI", "Telefono", 
            "Correo", "FechaEntrada", "Nick"};
        
        tableMonitor.setColumnIdentifiers(columns);
        
        this.vMonitor.jTableMonitores.getTableHeader().setReorderingAllowed(false);
        this.vMonitor.jTableMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        int[] column_sizes = {40,240,70,70,200,150,60};
        
        for(int i = 0; i<column_sizes.length; i++){
            this.vMonitor.jTableMonitores.getColumn(i).setPreferredWidth(column_sizes[i]);
        }
    }
    
    public void fillMonitorTable(DefaultTableModel tableMonitor, ArrayList<Monitor> monitores){
        Object[] fila = new Object[7];
        int numMonitores = monitores.size();
        for(int i = 0; i<numMonitores; i++){
            fila[0] = monitores.get(i).getcodigoMonitor();
            fila[1] = monitores.get(i).getnombre();
            fila[2] = monitores.get(i).getdni();
            fila[3] = monitores.get(i).gettelefono();
            fila[4] = monitores.get(i).getcorreo();
            fila[5] = monitores.get(i).getfechaEntrada();
            fila[6] = monitores.get(i).getnick();
            tableMonitor.addRow(fila);
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
                DefaultTableModel modelo = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
                JTable tabla = new JTable();
                tabla.setModel(modelo);
            }
            default : {
                System.out.println(e.getActionCommand());
                System.out.println("¿ Qué has hecho para llegar aqui viajero ?");
            }
        }
    }
    
    
}
