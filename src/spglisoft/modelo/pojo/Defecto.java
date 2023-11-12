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
    private String nombreProyecto;
    private String titulo;
    private String descripcion;
    private Date fechaReporte;
    private String tipo;

    public Defecto() {
    }

    public Defecto(int idDefecto, String nombreProyecto, String titulo, String descripcion, String estado, int esfuerzoEstimado, Date fechaReporte, String tipo) {
        this.idDefecto = idDefecto;
        this.nombreProyecto = nombreProyecto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaReporte = fechaReporte;
        this.tipo = tipo;
    }

    public int getIdDefecto() {
        return idDefecto;
    }

    public void setIdDefecto(int idDefecto) {
        this.idDefecto = idDefecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
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
    
    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
