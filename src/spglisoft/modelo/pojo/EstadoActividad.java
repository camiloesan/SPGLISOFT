package spglisoft.modelo.pojo;

public class EstadoActividad {
    private int idEstado;
    private String estado;

    public EstadoActividad(int idEstado, String estado) {
        this.idEstado = idEstado;
        this.estado = estado;
    }

    public EstadoActividad() {
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
