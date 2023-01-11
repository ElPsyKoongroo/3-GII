/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ElPsy
 */
public class MariaSocioDAO implements ISocioDAO{

    private Session sesion;
    private PreparedStatement ps = null;

    public MariaSocioDAO(Session _con) {
        this.sesion = _con;
    }

    public void añadeSocio(Socio s) {
        Transaction t = sesion.beginTransaction();
        sesion.save(s);
        t.commit(); 
    }
    
    public void updateSocio(Socio s) {
        Transaction t = sesion.beginTransaction();
        sesion.save(s);
        t.commit(); 
    }

    public void eliminaSocio(String numSocio) {
        Transaction t = sesion.beginTransaction();
        
        Socio s = sesion.get(Socio.class, numSocio);
        
        sesion.delete(s);
        
        t.commit();
    }

    public ArrayList<Socio> listaSocio() 
    {
         Transaction trans = sesion.beginTransaction();
        
        var consulta = sesion.createQuery("SELECT s FROM Socio s", Socio.class);
        
        ArrayList<Socio> listaSocio = (ArrayList<Socio>)consulta.list();
        
        trans.commit();
        
        return listaSocio;
        
        
    }

}
