/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlsoftware;

/**
 *
 * @author Usuario
 */
public class Item {

    private String id;
    private String nombre;

    public Item(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre; // Aquí indicamos que se mostrará el nombre en el JComboBox.
    }

}
