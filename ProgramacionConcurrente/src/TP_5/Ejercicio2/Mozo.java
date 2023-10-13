/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio2;

/**
 *
 * @author galin
 */
public class Mozo implements Runnable {

    private Silla silla;

    public Mozo(Silla silla) {
        this.silla = silla;
    }

    public void run() {
        while (true) {
            silla.servirEmpleado();
            silla.esperaProximoEmpleadoMozo();
        }
    }
}
