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

public class SocioDAO {
    private static final int NOMBRE = 1;
    private static final int DNI = 2;
    private static final int FNACIMIENTO = 3;
    private static final int TEL = 4;
    private static final int CORREO = 5;
    private static final int FENTRADA = 6;
    private static final int CATEGORIA = 7;

    private Conexion con;
    private PreparedStatement ps = null;

    public SocioDAO(Conexion _con){
        this.con = _con;
    }   
    
    public ArrayList<Socio> listaSocio() throws SQLException{
        ArrayList<Socio> listaSocio = new ArrayList<>();
        
        String consulta = "SELECT * FROM SOCIO";
        ps = this.con.getConexion().prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Socio s = new Socio(
                    rs.getString(NOMBRE),
                    rs.getString(DNI),
                    rs.getString(FNACIMIENTO),
                    rs.getString(TEL),
                    rs.getString(CORREO),
                    rs.getString(FENTRADA),
                    rs.getString(CATEGORIA)
            );
            
            listaSocio.add(s);
        }
        return listaSocio;
    }
    
}
