/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_2.Ejercicio4;

/**
 *
 * @author galin
 */
public class TestetoRunnable {

    public static void main(String[] args) {
        //Clase Runnable
        ThreadEjemploRunnable p1 = new ThreadEjemploRunnable("Maria Jose");
        Thread Hilop1 = new Thread(p1);
        ThreadEjemploRunnable p2 = new ThreadEjemploRunnable("Jose Maria");
        Thread Hilop2 = new Thread(p2);
        Hilop1.start();
        Hilop2.start();
        System.out.println("Termina thread main");
    }

}
