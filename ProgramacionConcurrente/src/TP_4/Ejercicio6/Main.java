/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio6;
/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Thread[] pasajeros = new Thread[3];
        Taxi taxi = new Taxi();
        
        Thread hiloTaxista = new Thread(new Taxista(taxi));
        hiloTaxista.start();
        for (int i = 0; i < pasajeros.length; i++) {
            pasajeros[i] = new Thread(new Pasajero(taxi));
        }
        
        for (int i = 0; i < pasajeros.length; i++) {
            pasajeros[i].start();
        }

    }
}
