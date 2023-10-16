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
public class Escritor implements Runnable {

    private Libro libro;

    public Escritor(Libro libro) {
        this.libro = libro;
    }

    public void run() {

        libro.escribir();
        escribiendo();
        libro.finalizarEscribir();

    }

    public void escribiendo() {
        try {
            Thread.sleep((new Random().nextInt(1000) + 1000));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
