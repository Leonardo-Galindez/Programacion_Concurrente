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

        Semaphore semP1 = new Semaphore(1);
        Semaphore semP2 = new Semaphore(0);
        Semaphore semP3 = new Semaphore(0);

        Thread p1 = new Thread(new Proceso(semP1, semP3, "P1"));
        Thread p2 = new Thread(new Proceso(semP3, semP2, "P3"));
        Thread p3 = new Thread(new Proceso(semP2, semP1, "P2"));

        p1.start();
        p2.start();
        p3.start();

    }
}
