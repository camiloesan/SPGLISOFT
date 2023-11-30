/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.modelo.pojo;

import java.sql.Date;
import javafx.scene.control.Button;

/**
 *
 * @author lecap
 */
public class SolicitudCambio {
    private int idSolicitud;
    private String nombreProyecto;
    private int idProponente;
    private String titulo;
    private String descripcion;
    private Date fechaSolicitud;
    private String accionPropuesta;
    private String impacto;
    private String razonCambio;
    private String estado;
    private Button button;

    public SolicitudCambio() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
    
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public int getIdProponente() {
        return idProponente;
    }

    public void setIdProponente(int idProponente) {
        this.idProponente = idProponente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getAccionPropuesta() {
        return accionPropuesta;
    }

    public void setAccionPropuesta(String accionPropuesta) {
        this.accionPropuesta = accionPropuesta;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public String getRazonCambio() {
        return razonCambio;
    }

    public void setRazonCambio(String razonCambio) {
        this.razonCambio = razonCambio;
    }
    
}
