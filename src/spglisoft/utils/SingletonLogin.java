/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.utils;

import spglisoft.modelo.pojo.User;

/**
 *
 * @author camilo
 */
public class SingletonLogin {
    private static SingletonLogin instance;
    private User user;
    
    public static SingletonLogin getInstance() {
        if (instance == null) {
            instance = new SingletonLogin();
        }
        return instance;
    }
    
    public static void cleanDetails() {
        instance = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
