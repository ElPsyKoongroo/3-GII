package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMonitorDAO {
    public static final int CODIGO = 1;
    public static final int NOMBRE = 2;
    public static final int DNI = 3;
    public static final int TEL = 4;
    public static final int CORREO = 5;
    public static final int FENTRADA = 6;
    public static final int NICK = 7;

    void añadeMonitor(Monitor m) throws SQLException;
    void eliminaMonitor(String idMonitor) throws SQLException ;
    void updateMonitor(Monitor m) throws SQLException;
    ArrayList<Monitor> listaMonitores() throws SQLException;
}
