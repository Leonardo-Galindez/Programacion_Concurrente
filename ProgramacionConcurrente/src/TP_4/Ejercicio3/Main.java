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
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(0);
        Semaphore sem3 = new Semaphore(0);

        Thread proceso1 = new Thread(new Proceso("P1", sem1, sem3));
        Thread proceso2 = new Thread(new Proceso("P2", sem2, sem1));
        Thread proceso3 = new Thread(new Proceso("P3", sem3, sem2));

        proceso1.start();
        proceso2.start();
        proceso3.start();
    }
}
