/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Pasajero implements Runnable {

    private Taxi taxi;

    public Pasajero(Taxi taxi) {
        this.taxi = taxi;
    }

    public void run() {
        taxi.tomarTaxi();
        taxi.solicitarTaxi();
        taxi.realizandoViaje();
        taxi.liberarTaxi();
    }
}
