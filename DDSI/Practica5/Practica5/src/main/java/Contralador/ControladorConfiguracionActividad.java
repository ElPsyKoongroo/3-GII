/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contralador;
import Modelo.Actividad;
import Modelo.IActividadDAO;
import Modelo.MariaActividadDAO;
import Modelo.MariaMonitorDAO;
import Modelo.OracleActividadDAO;
import java.awt.event.ActionListener;
import Vista.VistaActividades;
import Vista.VistaAddMonitor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import org.hibernate.Session;
import Modelo.Socio;
import Vista.VistaConfiguracionActividad;
import java.util.ArrayList;
import org.hibernate.Transaction;
/** 
*
 * @author eduar
 */
public class ControladorConfiguracionActividad {
    private ActionListener vAddListenerConfActividad;
    private VistaConfiguracionActividad vConfActividad;
    private IActividadDAO actividadDAO;
    private ArrayList<Actividad> actividades;
    private Socio socio;
    private Session sesion;
    
    
    public ControladorConfiguracionActividad(Frame parent, Session con, Socio s, String dataBase) {
        sesion = con;
        if (dataBase.equals("mariadb")) {
            this.actividadDAO = new MariaActividadDAO(con);
        } else {
            this.actividadDAO = new OracleActividadDAO(con); // Cambiar
        }
        this.vConfActividad = new VistaConfiguracionActividad(parent, true);
        this.vAddListenerConfActividad = (ActionEvent e) -> actionPerformedConfiguracionActividad(e);
        this.addListeners();
        socio = s;
        this.vConfActividad.NombreSocio.setText(socio.getNombre());
        this.PopulateActividades();

        this.vConfActividad.setVisible(true);
    }
    
    private void EstadoActividad(){
        int index = vConfActividad.JComboBoxActividades.getSelectedIndex();
        
        String idActividad = actividades.get(index).getIdactividad();
        
        ArrayList<Object[]> socios = this.actividadDAO.getSocioByActividad(idActividad);
        
        for(int i = 0; i<socios.size(); ++i) {
            String NombreSocio = (String)socios.get(i)[0];
            if(NombreSocio.equals(socio.getNombre())){
                this.vConfActividad.EstadoActividad.setText("Estado: ALTA");
                this.vConfActividad.jButtonDeBaja.setEnabled(true);
                this.vConfActividad.jButtonAlta.setEnabled(false);
                
                return;
            }
        }
        this.vConfActividad.EstadoActividad.setText("Estado: BAJA");
        this.vConfActividad.jButtonDeBaja.setEnabled(false);
        this.vConfActividad.jButtonAlta.setEnabled(true);
    }
    
    
    private void addListeners(){
        this.vConfActividad.JComboBoxActividades.addActionListener(vAddListenerConfActividad);
        this.vConfActividad.jButtonCancelar.addActionListener(vAddListenerConfActividad);
        this.vConfActividad.jButtonAlta.addActionListener(vAddListenerConfActividad);
        this.vConfActividad.jButtonDeBaja.addActionListener(vAddListenerConfActividad);
    }
    
    private void actionPerformedConfiguracionActividad(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Cancelar": {
                this.vConfActividad.dispose();
                break;
            }
            case "ComboBoxActividades":{
                this.EstadoActividad();
                break;
            }
            case "DarDeAlta":{
                DarDeAlta();
                this.EstadoActividad();
                break;
            }
            
            case "DarDeBaja":{
                DarDeBaja();
                this.EstadoActividad();
                break;
            }

            
            default: {
                System.out.println(e.getActionCommand());
            }
        }
    }
    
    private void DarDeAlta(){
        var index = vConfActividad.JComboBoxActividades.getSelectedIndex();
        Actividad act = actividades.get(index);
        Transaction t = sesion.beginTransaction();
        System.out.println(socio.getNombre());
        act.addSocio(socio);
        sesion.save(act);
        t.commit();
    }
    
    private void DarDeBaja(){
        var index = vConfActividad.JComboBoxActividades.getSelectedIndex();
        System.out.println(index);
        Actividad act = actividades.get(index);
        Transaction t = sesion.beginTransaction();
        act.deleteSocio(socio);
        sesion.save(act);
        t.commit();
    }
    
    private void PopulateActividades(){
        actividades = this.actividadDAO.getActividades();
        
        for(var actividad: actividades)
        {
            vConfActividad.JComboBoxActividades.addItem(actividad.getNombre());
        }
    }
}
