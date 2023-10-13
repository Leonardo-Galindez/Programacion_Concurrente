/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio2;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Silla silla = new Silla();

        Thread hilosEmpleados[] = new Thread[10];

        Thread hiloCocineto = new Thread(new Cocinero(silla));
        Thread hiloMozo = new Thread(new Mozo(silla));

        for (int i = 0; i < hilosEmpleados.length; i++) {
            hilosEmpleados[i] = new Thread(new Empleado(silla));
        }

        for (int i = 0; i < hilosEmpleados.length; i++) {
            hilosEmpleados[i].start();
        }

        hiloCocineto.start();
        hiloMozo.start();
    }
}
