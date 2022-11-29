/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
* @author ElPsy
 */
public class MonitorDAO {
    
    public static final int CODIGO = 1;
    public static final int NOMBRE = 2;
    public static final int DNI = 3;
    public static final int TEL = 4;
    public static final int CORREO = 5;
    public static final int FENTRADA = 6;
    public static final int NICK = 7;
    
    
    Conexion con = null;
    PreparedStatement ps = null;
    
    public MonitorDAO(Conexion _con){
        this.con = _con;
    }

    public void añadeMonitor(Monitor m) throws SQLException {
        String consulta = String.format(
            "insert into MONITOR VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
            m.getCodigoMonitor(),
            m.getNombre(),
            m.getDni(),
            m.getTelefono(),
            m.getCorreo(),
            m.getFechaEntrada(),
            m.getNick() 
        );
        ps = this.con.getConexion().prepareStatement(consulta);
        ps.executeQuery(); 
    }
    
    public void eliminaMonitor(String idMonitor) throws SQLException {
        String consulta = String.format(
            "DELETE FROM MONITOR WHERE MONITOR.CODMONITOR = '%s'",
            idMonitor
        );
        ps = this.con.getConexion().prepareStatement(consulta);
        ps.executeUpdate();
    }
    
    public void updateMonitor(Monitor m) throws SQLException {
        String consulta = String.format(
            "UPDATE MONITOR " +
            "SET MONITOR.nombre = '%s', " + 
            "MONITOR.dni = '%s', " + 
            "MONITOR.telefono = '%s', " + 
            "MONITOR.correo = '%s', " + 
            "MONITOR.fechaEntrada = '%s', " + 
            "MONITOR.nick = '%s' " +
            "WHERE MONITOR.codMonitor = '%s'",
            m.getNombre(),
            m.getDni(),
            m.getTelefono(),
            m.getCorreo(),
            m.getFechaEntrada(),
            m.getNick(),
            m.getCodigoMonitor()
        );

        System.out.println(consulta);
        ps = this.con.getConexion().prepareStatement(consulta);
        ps.executeQuery(); 
    }
    
    public ArrayList<Monitor> listaMonitores() throws SQLException {
        ArrayList<Monitor> listaMonitores = new ArrayList<Monitor>();
        String consulta = "SELECT * FROM MONITOR";
        ps = this.con.getConexion().prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Monitor monitor = new Monitor(
                    rs.getString(CODIGO),
                    rs.getString(NOMBRE),
                    rs.getString(DNI),
                    rs.getString(TEL),
                    rs.getString(CORREO),
                    rs.getString(FENTRADA),
                    rs.getString(NICK)
            );
            listaMonitores.add(monitor);
        }
        
        return listaMonitores;
    }
    
    
}
