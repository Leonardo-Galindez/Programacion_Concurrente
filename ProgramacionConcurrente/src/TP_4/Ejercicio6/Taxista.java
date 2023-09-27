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
public class Taxista implements Runnable {

    //lo que comparten es el taxi???
    private Taxi taxi;

    public Taxista(Taxi taxi) {
        this.taxi = taxi;
    }

    public void run() {
        try {
            taxi.esperarProximoPasajero();
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxista.class.getName()).log(Level.SEVERE, null, ex);
        }
        realizandoViaje();
        taxi.avisarDeLlegadaADestino();
    }

    public void realizandoViaje() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("En viaje.....");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
