package spglisoft.modelo.dao;

import java.sql.SQLException;

public interface ISolicitudCambio {
    void actualizarEstadoSolicitud(String estado, int idSolicitud) throws SQLException;
}
