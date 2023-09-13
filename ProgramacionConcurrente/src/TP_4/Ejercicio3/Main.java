/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio3;

import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {

        Mensaje unMensaje = new Mensaje();

        P1 p1 = new P1(unMensaje);
        P2 p2 = new P2(unMensaje);
        P3 p3 = new P3(unMensaje);

        Thread threadP1 = new Thread(p1);
        Thread threadP2 = new Thread(p2);
        Thread threadP3 = new Thread(p3);

        threadP1.start();
        threadP2.start();
        threadP3.start();

    }
}
