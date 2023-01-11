/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author ElPsy
 */
public class MariaActividadDAO implements IActividadDAO {

    Conexion con = null;
    PreparedStatement ps = null;
    
    public MariaActividadDAO(Conexion _con){
        this.con = _con;
    }

    //TODO! Esto deberia devolver una ArrayList<String[]> no un ArrayList<Object[]>
    public ArrayList<Object[]> getSocioByActividad(String idActividad) throws SQLException {
        ArrayList<Object[]> socios = new ArrayList<>();
        String sql = "{call get_socios_by_actividad(?)}";
        CallableStatement stmt = this.con.getConexion().prepareCall(sql);
        stmt.setString(1, idActividad);
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        
        while (rs.next()) {
            Object[] nombre_correo = new Object[2];
            nombre_correo[0] = rs.getString(1);
            nombre_correo[1] = rs.getString(2);
            socios.add(nombre_correo);
        }
        stmt.close();
        return socios;
    }
}
