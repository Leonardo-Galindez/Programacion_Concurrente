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
        while (true) {
            rta = (new Random()).nextInt(3) + 1;
            switch (1) {
                case 1:
                    //tomar
                    silla.tomarSilla();
                    silla.solicitarAtencionMozo();
                    silla.tomar();
                    break;
                case 2:
                    //comer
                    silla.tomarSilla();
                    silla.solicitarAtencionCocinero();
                    silla.comer();
                    break;
                case 3:
                    //comer y tomar
                    silla.tomarSilla();
                    silla.solicitarAtencionMozo();
                    silla.tomar();
                    silla.solicitarAtencionCocinero();
                    silla.comer();
                    silla.liberarMozo();
                    silla.liberarCocinero();
                    break;
            }
        }
    }

}
