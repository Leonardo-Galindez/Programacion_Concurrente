/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio4;

/**
 *
 * @author galin
 */
public class Producto {

    private static int i = 0;
    private int id;

    public Producto() {
        this.id = nextId();
    }

    private int nextId() {
        return i++;
    }

    public int getId() {
        return this.i;
    }
}
