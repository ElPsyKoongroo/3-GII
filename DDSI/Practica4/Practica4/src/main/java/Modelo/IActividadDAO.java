package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IActividadDAO {
    public final int ID_ACTIVIDAD   = 1;
    public final int NOMBRE         = 2;
    public final int DESCRIPCION    = 3;
    public final int PRECIO         = 4;
    public final int ID_MONITOR     = 5;

    Conexion con = null;
    ArrayList<Object[]> getSocioByActividad(String idActividad) throws SQLException;
    ArrayList<Actividad> getActividades() throws SQLException;
}
