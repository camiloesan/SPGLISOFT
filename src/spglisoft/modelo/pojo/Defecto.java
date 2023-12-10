/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.modelo.pojo;

import java.sql.Date;

/**
 *
 * @author lecap
 */
public class Defecto {
    
    private int idDefecto;
    private int idProyecto;
    private int idDesarrollador;
    private String nombreDefectoString;
    private String descripcion;
    private Date fechaReporte;
    private int tipoDefecto;
    private int esfuerzoEstimado;
    private int estadoDefecto;

    public Defecto() {
    }

    public int getIdDefecto() {
        return idDefecto;
    }

    public void setIdDefecto(int idDefecto) {
        this.idDefecto = idDefecto;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public String getNombreDefectoString() {
        return nombreDefectoString;
    }

    public void setNombreDefectoString(String nombreDefectoString) {
        this.nombreDefectoString = nombreDefectoString;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public int getTipoDefecto() {
        return tipoDefecto;
    }

    public void setTipoDefecto(int tipoDefecto) {
        this.tipoDefecto = tipoDefecto;
    }

    public int getEsfuerzoEstimado() {
        return esfuerzoEstimado;
    }

    public void setEsfuerzoEstimado(int esfuerzoEstimado) {
        this.esfuerzoEstimado = esfuerzoEstimado;
    }

    public int getEstadoDefecto() {
        return estadoDefecto;
    }

    public void setEstadoDefecto(int estadoDefecto) {
        this.estadoDefecto = estadoDefecto;
    }
    
}
