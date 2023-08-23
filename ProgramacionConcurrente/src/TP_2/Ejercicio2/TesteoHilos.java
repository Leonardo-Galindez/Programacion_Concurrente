/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_2.Ejercicio2;

/**
 *
 * @author galin
 */
class TesteoHilos {

    public static void main(String[] args) {
        Thread miHilo = new MiEjecucion();
        miHilo.start();
        try {
            miHilo.join();
            // Espera a que el hilo miHilo termine antes de continuar
        } catch (InterruptedException e) {
            /*   
            Sin embargo, durante esta espera, si otro hilo
            (como el hilo principal) llama al método interrupt()
            en el hilo miHilo, se lanzará la excepción InterruptedException
            en el hilo que está esperando (main).
            */
            e.printStackTrace();
        }
        System.out.println("En el main");
    }
}
