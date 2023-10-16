/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio5;

/**
 *
 * @author galin
 */
public class VendedorTickets implements Runnable {

    private int cantPasajerosTotal;
    private Tren tren;

    public VendedorTickets(Tren tren) {
        this.cantPasajerosTotal = 0;
        this.tren = tren;
    }

    public synchronized void contarPasajero(int cantTickets) {
        cantPasajerosTotal = cantPasajerosTotal + cantTickets;
    }

    public void run() {

    }
}
