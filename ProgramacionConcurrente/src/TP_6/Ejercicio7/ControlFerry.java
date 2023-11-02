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
public class ControlFerry implements Runnable {

    private Ferry ferry;

    public ControlFerry(Ferry ferry) {
        this.ferry = ferry;
    }

    public void run() {
        while (true) {
            try {
                ferry.iniciarViaje();
                viajando();
                ferry.finalizarViaje();
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlFerry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void viajando() {
        try {
            System.out.println("viajando...");
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
