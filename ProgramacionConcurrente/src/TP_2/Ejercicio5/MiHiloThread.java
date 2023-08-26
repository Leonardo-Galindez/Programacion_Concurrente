/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_2.Ejercicio5;

/**
 *
 * @author galin
 */
class MiHiloThread extends Thread {

    MiHiloThread(String nombre) {
        super(nombre);
    }

    public void run() {
        System.out.println("Comenzando " + getName());
        try {
            for (int contar = 0; contar < 10; contar++) {
                Thread.sleep(400);
                System.out.println("En " + getName() + ", el recuento " + contar);
            }
        } catch (InterruptedException exc) {
            System.out.println(getName() + "Interrumpido.");
        }
        System.out.println("Terminado " + getName());
    }

    public static void main(String[] args) {
        System.out.println("Hilo principal iniciado.");
        //Primero, construye un objeto unHilo.
        MiHilo mh = new MiHilo("#1");
        //Luego, construye un hilo de ese objeto.
        Thread nuevoHilo = new Thread(mh);
        //Finalmente, comienza la ejecución del hilo.
        nuevoHilo.start();

        for (int i = 0; i < 50; i++) {
            System.out.println(" .");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido");
        }
        System.out.println("Hilo principal finalizado");
    }
}
