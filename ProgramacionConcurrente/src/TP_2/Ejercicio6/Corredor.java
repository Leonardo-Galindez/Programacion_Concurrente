/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_2.Ejercicio6;

import java.util.Random;

/**
 *
 * @author galin
 */
class Corredor implements Runnable {

    String nombre;
    int distanciaRecorrida = 0;

    Corredor(String nombre) {
        this.nombre = nombre;
    }

    public int getDistanciaRecorrida() {
        return this.distanciaRecorrida;
    }

    public String getNombre() {
        return this.nombre;
    }

    public static void main(String[] args) {
        Corredor[] corredores = new Corredor[5];
        corredores[0] = new Corredor("Leonardo");
        corredores[1] = new Corredor("Fernando");
        corredores[2] = new Corredor("Rodrigo");
        corredores[3] = new Corredor("Lucas");
        corredores[4] = new Corredor("Juan");

        for (int i = 0; i < corredores.length; i++) {
            Thread unHilo = new Thread(corredores[i]);
            unHilo.start();
            try {
                unHilo.join();// Espera a que todos los corredores terminen
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Corredor ganador = corredores[0];
        for (int i = 1; i < corredores.length; i++) {
            if (corredores[i].getDistanciaRecorrida() > ganador.getDistanciaRecorrida()) {
                ganador = corredores[i];
            }
        }

        System.out.println("El ganador es " + ganador.getNombre() + " con una distancia de " + ganador.getDistanciaRecorrida());
    }

    public void run() {
        Random random = new Random();
        int avance;
        for (int i = 0; i < 100; i++) {
            avance = random.nextInt(10) + 1;
            distanciaRecorrida += avance;
            System.out.println(nombre + " ha avanzado " + avance + " unidades. Distancia total: " + distanciaRecorrida);
            try {
                Thread.sleep(10);//simula tiempo entre pasos
            } catch (InterruptedException exc) {
                System.out.println(nombre + " Interrumpido");
            }
        }
        System.out.println(nombre + " ha terminado la carrera!");

    }
}
