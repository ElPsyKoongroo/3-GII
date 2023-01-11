/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.*;

/**
 *
* @author ElPsy
 */
public class MariaMonitorDAO implements IMonitorDAO {
        
    Session sesion = null;
    PreparedStatement ps = null;
    
    public MariaMonitorDAO(Session _con){
        this.sesion = _con;
    }

    public void añadeMonitor(Monitor m) {
        Transaction t = sesion.beginTransaction();
        sesion.save(m);
        t.commit();
    }
    
    public void eliminaMonitor(String idMonitor) {
        Transaction t = sesion.beginTransaction();
        
        Monitor m = sesion.get(Monitor.class, idMonitor);
        
        sesion.delete(m);
        
        t.commit();
    }
    
    public void updateMonitor(Monitor m) {
        Transaction t = sesion.beginTransaction();
        sesion.save(m);
        t.commit(); 
    }
    
    public ArrayList<Monitor> listaMonitores() {
        
        Transaction trans = sesion.beginTransaction();
        
        var consulta = sesion.createQuery("SELECT m FROM Monitor m", Monitor.class);
        
        ArrayList<Monitor> listaMonitores = (ArrayList<Monitor>)consulta.list();
        
        trans.commit();
        
        return listaMonitores;
    }
    
    
}
