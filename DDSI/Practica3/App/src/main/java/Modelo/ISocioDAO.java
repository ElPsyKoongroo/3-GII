package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISocioDAO {

    public static final int NUMSOCIO = 1;
    public static final int NOMBRE = 2;
    public static final int DNI = 3;
    public static final int FNACIMIENTO = 4;
    public static final int TEL = 5;
    public static final int CORREO = 6;
    public static final int FENTRADA = 7;
    public static final int CATEGORIA = 8;

    void añadeSocio(Socio s) throws SQLException;
    void eliminaSocio(String numSocio) throws SQLException ;
    void updateSocio(Socio s) throws SQLException;
    ArrayList<Socio> listaSocio() throws SQLException;
}
