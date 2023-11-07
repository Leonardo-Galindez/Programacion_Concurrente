/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio7;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Ferry ferry = new Ferry(4, 10);

        Thread hilosPasajeros[] = new Thread[8];
        Thread hilosAutos[] = new Thread[10];
        Thread hiloFerry = new Thread(new ControlFerry(ferry));
        for (int i = 0; i < hilosPasajeros.length; i++) {
            hilosPasajeros[i] = new Thread(new Pasajero(ferry));
        }

        for (int i = 0; i < hilosPasajeros.length; i++) {
            hilosPasajeros[i].start();
        }
        hiloFerry.start();
        for (int i = 0; i < hilosAutos.length; i++) {
            hilosAutos[i] = new Thread(new Auto(ferry));
        }

        for (int i = 0; i < hilosAutos.length; i++) {
            hilosAutos[i].start();
        }
    }
}
