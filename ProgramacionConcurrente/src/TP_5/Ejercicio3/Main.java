/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio3;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {

        Thread hiloPerros[] = new Thread[7];
        Thread hiloGatos[] = new Thread[5];
        Comedor comedor = new Comedor();
        for (int i = 0; i < hiloPerros.length; i++) {
            hiloPerros[i] = new Thread(new Perro(comedor));
        }

        for (int i = 0; i < hiloGatos.length; i++) {
            hiloGatos[i] = new Thread(new Gato(comedor));
        }
        //Start
        //empezar primero con gatos y luego con perros ERROR
        for (int i = 0; i < hiloPerros.length; i++) {
            hiloPerros[i].start();
        }

        for (int i = 0; i < hiloGatos.length; i++) {
            hiloGatos[i].start();
        }

    }
}
