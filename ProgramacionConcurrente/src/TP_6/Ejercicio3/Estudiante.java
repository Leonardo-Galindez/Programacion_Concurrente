/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Estudiante implements Runnable {

    private Sala sala;

    public Estudiante(Sala sala) {
        this.sala = sala;
    }

    public void run() {
        //arreglar concurrencuia a la hora de que salgan y entren nuevos a la cola
        try {
            sala.ingresarSala();
            estudiando();
            sala.salirSala();
        } catch (InterruptedException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void estudiando() {

        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
