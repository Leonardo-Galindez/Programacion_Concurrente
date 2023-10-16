/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio5;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Tren tren = new Tren();
        
        Thread pasajeros[] = new Thread[20];

        Thread hiloGestor = new Thread(new GestorTren(tren));
        Thread hiloTickets = new Thread(new VendedorTickets(tren));
        for (int i = 0; i < pasajeros.length; i++) {
            pasajeros[i] = new Thread(new Pasajero(tren));
        }

        hiloGestor.start();

        for (int i = 0; i < pasajeros.length; i++) {
            pasajeros[i].start();
        }
    }
}
