/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.utils;

import spglisoft.modelo.pojo.Desarrollador;
import spglisoft.modelo.pojo.Representante;

/**
 *
 * @author camilo
 */
public class SingletonLogin {
    private static SingletonLogin instance;
    private String tipoUsuario;
    private Desarrollador desarrollador;
    private Representante representante;
    private int idProyectoActual;
    
    public static SingletonLogin getInstance() {
        if (instance == null) {
            instance = new SingletonLogin();
        }
        return instance;
    }
    
    public static void cleanDetails() {
        instance = null;
    }

    public int getIdProyectoActual() {
        return idProyectoActual;
    }

    public void setIdProyectoActual(int idProyectoActual) {
        this.idProyectoActual = idProyectoActual;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public Desarrollador getDesarrollador(){
        return desarrollador;
    }
    
    public void setDesarrollador(Desarrollador desarrollador){
        this.desarrollador = desarrollador;
    }
    
    public Representante getRepresentante(){
        return representante;
    }

    public void setRepresentante(Representante representante){
        this.representante = representante;
    }
}
