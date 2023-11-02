/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Pasajero implements Runnable {

    private Ferry ferry;

    public Pasajero(Ferry ferry) {
        this.ferry = ferry;
    }

    public void run() {
        try {
            ferry.ingresarPasajero();
            ferry.salirPasajero();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
