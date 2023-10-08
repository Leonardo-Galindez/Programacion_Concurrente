/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio7;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {

        Thread empleado[] = new Thread[10];
        Silla silla = new Silla();
        Mozo mozo = new Mozo(silla);

        for (int i = 0; i < empleado.length; i++) {
            empleado[i] = new Thread(new Empleado(silla), "" + i);
        }

        Thread hiloMozo = new Thread(mozo);
        hiloMozo.start();

        for (int i = 0; i < empleado.length; i++) {
            empleado[i].start();
        }
    }
}
