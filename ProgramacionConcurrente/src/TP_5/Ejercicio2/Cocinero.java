/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio2;

import java.util.Random;

/**
 *
 * @author galin
 */
public class Cocinero implements Runnable {

    private Silla silla;

    public Cocinero(Silla silla) {
        this.silla = silla;
    }

    public void run() {
        while (true) {
            silla.cocinarEmpleado();
            silla.esperaProximoEmpleadoCocinero();
        }
    }

    public void cocinando() {
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
            System.out.println("cocinando...");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
