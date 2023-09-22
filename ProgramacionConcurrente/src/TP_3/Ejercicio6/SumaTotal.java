/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio6;

/**
 *
 * @author galin
 */
public class SumaTotal {

    private int total = 0;

    public synchronized void sumar(int num) {
        total = total + num;
    }

    public synchronized int getSumaTotal() {
        return total;
    }
}
