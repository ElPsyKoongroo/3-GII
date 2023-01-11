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
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Adrian
 */
public class OracleActividadDAO implements IActividadDAO{
    Session sesion = null;
    PreparedStatement ps = null;
    
    public OracleActividadDAO(Session _con){
        this.sesion = _con;
    }

    //TODO! Esto deberia devolver una ArrayList<String[]> no un ArrayList<Object[]>
    public ArrayList<Object[]> getSocioByActividad(String idActividad){
        
        Transaction t = sesion.beginTransaction();
        
        StoredProcedureQuery call = sesion.createStoredProcedureCall("get_socios_by_actividad")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Class.class, ParameterMode.REF_CURSOR)
                .setParameter(1, idActividad);
        
        call.execute();
        
        ArrayList<Object[]> socios = (ArrayList<Object[]>)call.getResultList();
        
        t.commit();
        
        return socios;
        
        
        /*ArrayList<Object[]> socios = new ArrayList<>();
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
        return socios;*/
    }

    public ArrayList<Actividad> getActividades(){
        ArrayList<Actividad> listaActividades;
        
        Transaction trans = sesion.beginTransaction();
        
        var consulta = sesion.createQuery("SELECT a FROM Actividad a", Actividad.class);
        
        listaActividades = (ArrayList<Actividad>)consulta.list();
        
        trans.commit();
        
        return listaActividades;
    }
}
