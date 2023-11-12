package spglisoft.modelo.dao;

import spglisoft.modelo.pojo.Actividad;

import java.sql.SQLException;
import java.util.List;

public interface IActividad {
    List<Actividad> obtenerActividadesAsignadasPorNombreProyecto(String nombreProyecto) throws SQLException;
    List<Actividad> obtenerActividadesNoAsignadasPorNombreProyecto(String nombreProyecto) throws SQLException;
    List<Actividad> obtenerActividadesAsignadasPorDesarrollador(int idDesarrollador) throws SQLException;
}
