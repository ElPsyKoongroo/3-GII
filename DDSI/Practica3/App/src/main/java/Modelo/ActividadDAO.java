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
 * @author Usuario
 */
public class ActividadDAO {
    public final int ID_ACTIVIDAD   = 1;
    public final int NOMBRE         = 2;
    public final int DESCRIPCION    = 3;
    public final int PRECIO         = 4;
    public final int ID_MONITOR     = 5;
    
    Conexion con = null;
    PreparedStatement ps = null;
    
    public ActividadDAO(Conexion _con){
        this.con = _con;
    }
    
    public ArrayList<Object[]> getSocioByActividad(String idActividad) throws SQLException {
        ArrayList<Object[]> socios = new ArrayList<>();
        String consulta = "SELECT * FROM MONITOR";
        ps = this.con.getConexion().prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Object[] nombre_correo = new Object[2];
            nombre_correo[0] = rs.getString(0);
            nombre_correo[1] = rs.getString(1);
            socios.add(nombre_correo);
        }
        
        return socios;
    }
}
