/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio7;

/**
 *
 * @author galin
 */
public class Empleado implements Runnable {

    private Silla silla;

    public Empleado(Silla silla) {
        this.silla = silla;
    }

    public void run() {
        silla.tomarLugar();
        silla.solicitarAtencion();
        silla.comer();
        silla.liberarLugar();
    }
}
