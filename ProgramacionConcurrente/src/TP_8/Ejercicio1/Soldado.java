/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_8.Ejercicio1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Soldado implements Runnable {

    private Comedor comedor;

    public Soldado(Comedor comedor) {
        this.comedor = comedor;
    }

    public void run() {
        try {
            comedor.ingresarComedor();

        } catch (InterruptedException ex) {
            Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comiendo() {
        try {
            Thread.sleep((new Random().nextInt(1000) + 1000));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
