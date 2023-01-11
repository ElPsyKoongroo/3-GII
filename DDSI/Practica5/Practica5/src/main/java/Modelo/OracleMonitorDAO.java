package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OracleMonitorDAO implements IMonitorDAO{
    Session sesion = null;
    PreparedStatement ps = null;
    
    public OracleMonitorDAO(Session _con){
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
