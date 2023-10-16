/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio5;

import java.util.Random;

/**
 *
 * @author galin
 */
public class GestorTren implements Runnable {

    private Tren tren;

    public GestorTren(Tren tren) {
        this.tren = tren;
    }

    public void run() {
        while (true) {
            tren.iniciarViaje();
            viajando();
            tren.finalizarViaje();
        }

    }

    public void viajando() {
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
            System.out.println("Viajando...");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
