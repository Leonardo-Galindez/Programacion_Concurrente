/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class AutoNorte implements Runnable {

    private static final String tipo = "N";
    private GestorTrafico gestor;

    public AutoNorte(GestorTrafico gestor) {
        this.gestor = gestor;
    }

    public void run() {
        try {
            gestor.entrarAutoNorte();
            cruzando();
            gestor.salirAutoNorte();
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoSur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cruzando() {
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoSur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
