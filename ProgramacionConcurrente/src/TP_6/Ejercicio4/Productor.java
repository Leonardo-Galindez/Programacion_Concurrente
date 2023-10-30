/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio4;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Productor implements Runnable {

    private Buffer almacen;

    public Productor(Buffer almacen) {
        this.almacen = almacen;
    }

    public void run() {
        while(true){
            try {
                almacen.ponerProducto();
                //espera();
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void espera(){
        try {
            Thread.sleep((new Random()).nextInt(1000)+1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
