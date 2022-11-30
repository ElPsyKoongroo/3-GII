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

 public class OracleSocioDAO implements ISocioDAO {

    private Conexion con;
    private PreparedStatement ps = null;

    public OracleSocioDAO(Conexion _con) {
        this.con = _con;
    }

    public void añadeSocio(Socio s) throws SQLException {
        String consulta = String.format(
            "insert into SOCIO VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
            s.getNumeroSocio(),
            s.getNombre(),
            s.getDni(),
            s.getFechaNacimiento(),
            s.getTelefono(),
            s.getCorreo(),
            s.getFechaEntrada(),
            s.getCategoria() 
        );
        ps = this.con.getConexion().prepareStatement(consulta);
        ps.executeQuery(); 
    }
    
    public void updateSocio(Socio s) throws SQLException {
        String consulta = String.format(
            "UPDATE SOCIO " +
            "SET SOCIO.nombre = '%s', " + 
            "SOCIO.dni = '%s', " + 
            "SOCIO.fechaNacimiento = '%s', " +
            "SOCIO.telefono = '%s', " + 
            "SOCIO.correo = '%s', " + 
            "SOCIO.fechaEntrada = '%s', " + 
            "SOCIO.categoria = '%s' " +
            "WHERE SOCIO.numeroSocio = '%s'",
            s.getNombre(),
            s.getDni(),
            s.getFechaNacimiento(),
            s.getTelefono(),
            s.getCorreo(),
            s.getFechaEntrada(),
            s.getCategoria(),
            s.getNumeroSocio()
        );

        System.out.println(consulta);
        ps = this.con.getConexion().prepareStatement(consulta);
        ps.executeQuery(); 
    }

    public void eliminaSocio(String numSocio) throws SQLException {
        String consulta = String.format(
            "DELETE FROM SOCIO WHERE SOCIO.numeroSocio = '%s'",
            numSocio
        );
        ps = this.con.getConexion().prepareStatement(consulta);
        ps.executeUpdate();
    }

    public ArrayList<Socio> listaSocio() throws SQLException {
        ArrayList<Socio> listaSocio = new ArrayList<>();

        String consulta = "SELECT * FROM SOCIO";
        ps = this.con.getConexion().prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Socio s = new Socio(
                    rs.getString(NUMSOCIO),
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
