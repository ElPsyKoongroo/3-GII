/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static Modelo.IActividadDAO.DESCRIPCION;
import static Modelo.IActividadDAO.ID_ACTIVIDAD;
import static Modelo.IActividadDAO.NOMBRE;
import static Modelo.IActividadDAO.PRECIO;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Adrian
 */
public class OracleActividadDAO implements IActividadDAO{
    Conexion con = null;
    PreparedStatement ps = null;
    
    public OracleActividadDAO(Conexion _con){
        this.con = _con;
    }

    //TODO! Esto deberia devolver una ArrayList<String[]> no un ArrayList<Object[]>
    public ArrayList<Object[]> getSocioByActividad(String idActividad) throws SQLException {
        ArrayList<Object[]> socios = new ArrayList<>();
        String sql = "{call get_socios_by_actividad(?, ?)}";
        CallableStatement stmt = this.con.getConexion().prepareCall(sql);
        stmt.setString(1, idActividad);
        stmt.registerOutParameter(2, OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet)stmt.getObject(2);
        
        while (rs.next()) {
            Object[] nombre_correo = new Object[2];
            nombre_correo[0] = rs.getString(1);
            nombre_correo[1] = rs.getString(2);
            socios.add(nombre_correo);
        }
        stmt.close();
        return socios;
    }

    public ArrayList<Actividad> getActividades() throws SQLException {
        ArrayList<Actividad> listaActividades = new ArrayList<>();
        String consulta = "SELECT * FROM ACTIVIDAD";
        
        PreparedStatement ps = this.con.getConexion().prepareStatement(consulta);
        
        ResultSet rs = ps.executeQuery();
        
        while (rs.next())
        {
            listaActividades.add(
                new Actividad(
                    rs.getString(ID_ACTIVIDAD),
                    rs.getString(NOMBRE),
                    rs.getString(DESCRIPCION),
                    rs.getString(PRECIO)
                )
            );
        }
        return listaActividades;
    }
}
