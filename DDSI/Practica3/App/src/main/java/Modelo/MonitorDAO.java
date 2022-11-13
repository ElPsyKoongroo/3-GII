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
    
    private static final int CODIGO = 1;
    private static final int NOMBRE = 2;
    private static final int DNI = 3;
    private static final int TEL = 4;
    private static final int CORREO = 5;
    private static final int FENTRADA = 6;
    private static final int NICK = 7;
    
    
    Conexion con = null;
    PreparedStatement ps = null;
    
    public MonitorDAO(Conexion _con){
        this.con = _con;
    }

    public void añadeMonitor(Monitor m) throws SQLException {
        String consulta = String.format(
            "insert into MONITOR VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
            m.getcodigoMonitor(),
            m.getnombre(),
            m.getdni(),
            m.gettelefono(),
            m.getcorreo(),
            m.getfechaEntrada(),
            m.getnick() 
        );
        ps = this.con.getConexion().prepareStatement(consulta);
        ResultSet rs = ps.executeQuery(); 
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
