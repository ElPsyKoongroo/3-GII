/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;

import Modelo.Conexion;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ElPsy
 */
public class ControladorPrincipal implements ActionListener {
    private Conexion con;
    private VistaPrincipal vPrincipal;
    
    public ControladorPrincipal(Conexion con){
        this.con = con;
        this.vPrincipal = new VistaPrincipal();
        addListeners();
        
        this.vPrincipal.setLocationRelativeTo(null);
        this.vPrincipal.setVisible(true);
        
    }
    
    private void addListeners() {
        this.vPrincipal.ButtonCerrar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "CerrarCommand": {
                this.vPrincipal.dispose();
                System.exit(0);
                break;
            }
            default : {
                System.out.println("¿ Qué has hecho para llegar aqui viajero ?");
            }
        }
    }
    
    
}
