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
        MiHilo mh1 = new MiHilo("#1");
        MiHilo mh2 = new MiHilo("#2");
        MiHilo mh3 = new MiHilo("#3");
        //Luego, construye un hilo de ese objeto.
        Thread nuevoHilo1 = new Thread(mh1);
        Thread nuevoHilo2 = new Thread(mh2);
        Thread nuevoHilo3 = new Thread(mh3);
        //Finalmente, comienza la ejecución del hilo.
        nuevoHilo1.start();
        nuevoHilo2.start();
        nuevoHilo3.start();

        for (int i = 0; i < 50; i++) {
            System.out.println(" .");
        }
        try {
            Thread.sleep(400);
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido");
        }
        System.out.println("Hilo principal finalizado");
    }
}
