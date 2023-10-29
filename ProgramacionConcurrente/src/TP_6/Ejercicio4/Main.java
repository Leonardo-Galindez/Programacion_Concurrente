/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Buffer almacen = new Buffer(10);

        Thread hiloProductor = new Thread(new Productor(almacen));
        Thread hiloConsumidor = new Thread(new Consumidor(almacen));

        hiloProductor.start();
        hiloConsumidor.start();

        try {
            hiloProductor.join();
            hiloConsumidor.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
