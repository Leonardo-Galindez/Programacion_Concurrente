/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio6;

/**
 *
 * @author galin
 */
public class Taxista implements Runnable {

    private Taxi taxi;

    public Taxista(Taxi taxi) {
        this.taxi = taxi;
    }

    public void run() {
        while (true) {
            taxi.esperarProximoPasajero();
            viajando();
            taxi.finalizarViaje();
        }
    }

    public void viajando() {
        try {
            System.out.println("Viajando.....");
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
