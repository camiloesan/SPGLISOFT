/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.utils;

import spglisoft.modelo.pojo.Usuario;

/**
 *
 * @author camilo
 */
public class SingletonLogin {
    private static SingletonLogin instance;
    private Usuario user;
    
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
}
