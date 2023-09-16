/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio7;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        //Recurso compartido
        Turno turno = new Turno('A');

        Letra a = new Letra('A', 1, turno);
        Letra b = new Letra('B', 2, turno);
        Letra c = new Letra('C', 3, turno);

        Thread hiloA = new Thread(a);
        Thread hiloB = new Thread(b);
        Thread hiloC = new Thread(c);

        hiloA.start();
        hiloB.start();
        hiloC.start();

        try {
            hiloA.join();
            hiloB.join();
            hiloC.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
}
