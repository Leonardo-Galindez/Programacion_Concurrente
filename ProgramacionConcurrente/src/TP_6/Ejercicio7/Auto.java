/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio7;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Auto implements Runnable {

    private Ferry ferry;

    public Auto(Ferry ferry) {
        this.ferry = ferry;
    }

    public void run() {
        //int espacio = (new Random()).nextInt(3) + 3;
        int espacio=2;
        try {
            ferry.subirAuto(espacio);
            ferry.bajarAuto(espacio);
        } catch (InterruptedException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
