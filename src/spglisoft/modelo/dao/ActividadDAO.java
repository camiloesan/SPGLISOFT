package spglisoft.modelo.dao;

import spglisoft.modelo.pojo.Actividad;

import java.sql.SQLException;
import java.util.List;

public class ActividadDAO implements IActividad {
    @Override
    public List<Actividad> obtenerActividadesAsignadasPorNombreProyecto(String nombreProyecto) throws SQLException {
        String query = "SELECT * FROM actividades " +
                "WHERE nombre_proyecto = ? AND id_desarrollador IS NOT NULL";
        return null;
    }

    @Override
    public List<Actividad> obtenerActividadesNoAsignadasPorNombreProyecto(String nombreProyecto) throws SQLException {
        String query = "SELECT * FROM actividades " +
                "WHERE nombre_proyecto = ? AND id_desarrollador IS NULL";
        return null;
    }

    @Override
    public List<Actividad> obtenerActividadesAsignadasPorDesarrollador(String nombreProyecto) throws SQLException {
        return null;
    }
}
