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
    private int idProyecto;
    private int idDesarrollador;
    private String nombreSolicitud;
    private String descripcion;
    private Date fechaSolicitud;
    private String accionPropuesta;
    private int idImpacto;
    private String razonCambio;
    private int idEstado;
    private Date fechaRevision;
    private int desarrolladorAsignado;
    private int idRepresentante;
    private Button button;

    public SolicitudCambio() {
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
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

    public String getNombreSolicitud() {
        return nombreSolicitud;
    }

    public void setNombreSolicitud(String nombreSolicitud) {
        this.nombreSolicitud = nombreSolicitud;
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

    public int getIdImpacto() {
        return idImpacto;
    }

    public void setIdImpacto(int idImpacto) {
        this.idImpacto = idImpacto;
    }

    public String getRazonCambio() {
        return razonCambio;
    }

    public void setRazonCambio(String razonCambio) {
        this.razonCambio = razonCambio;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public int getDesarrolladorAsignado() {
        return desarrolladorAsignado;
    }

    public void setDesarrolladorAsignado(int desarrolladorAsignado) {
        this.desarrolladorAsignado = desarrolladorAsignado;
    }

    public int getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(int idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
    
}
