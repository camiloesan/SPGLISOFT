/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.utils;

import spglisoft.modelo.pojo.Desarrollador;
import spglisoft.modelo.pojo.Representante;
import spglisoft.modelo.pojo.Usuario;

/**
 *
 * @author camilo
 */
public class SingletonLogin {
    private static SingletonLogin instance;
    private Usuario user;
    private Desarrollador desarrollador;
    private Representante representante;
    private String nombreProyectoActual;
    
    public static SingletonLogin getInstance() {
        if (instance == null) {
            instance = new SingletonLogin();
        }
        return instance;
    }
    
    public static void cleanDetails() {
        instance = null;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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
    public String getNombreProyectoActual() {
        return nombreProyectoActual;
    }

    public void setNombreProyectoActual(String nombreProyectoActual) {
        this.nombreProyectoActual = nombreProyectoActual;
    }
}
