/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio4;

/**
 *
 * @author galin
 */
public class Impresora {

    private int num;
    private Estado estado;

    public Impresora(Estado estado, int num) {
        this.estado = estado;
        this.num = num;
    }

    public String toString() {
        return "Impresora " + this.num;
    }
}
