/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio5;

/**
 *
 * @author galin
 */
public class Pasajero implements Runnable {

    private Tren tren;

    public Pasajero(Tren tren) {
        this.tren = tren;
    }

    public void run() {

        tren.subirTren();
        tren.bajarTren();

    }
}
