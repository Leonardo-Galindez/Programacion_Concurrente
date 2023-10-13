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
public class Empleado implements Runnable {

    private Silla silla;

    public Empleado(Silla silla) {
        this.silla = silla;
    }

    public void run() {
        int rta;

        rta = (new Random()).nextInt(3) + 1;
        switch (rta) {
            case 1:
                //tomar
                silla.tomarSilla();
                silla.solicitarAtencionMozo();
                silla.tomar();
                simulacion();
                silla.liberarMozo();
                break;
            case 2:
                //comer
                silla.tomarSilla();
                silla.solicitarAtencionCocinero();
                silla.comer();
                simulacion();
                silla.liberarCocinero();
                break;
            case 3:
                //comer y tomar
                silla.tomarSilla();
                silla.solicitarAtencionMozo();
                silla.tomar();
                simulacion();
                silla.solicitarAtencionCocinero();
                silla.comer();
                simulacion();
                silla.liberarMozo();
                silla.liberarCocinero();
                break;
        }
    }

    public void simulacion() {
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
            System.out.println("Simulando....");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
