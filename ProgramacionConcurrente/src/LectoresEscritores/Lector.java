/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LectoresEscritores;

import java.util.Random;

/**
 *
 * @author galin
 */
public class Lector implements Runnable {

    private Libro libro;

    public Lector(Libro libro) {
        this.libro = libro;
    }

    public void run() {

        libro.leer();
        leyendo();
        libro.finalizarLeer();

    }

    public void leyendo() {
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();

        }
    }
}
