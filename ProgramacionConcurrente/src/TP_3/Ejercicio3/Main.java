/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio3;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {

        PlatoComida comida = new PlatoComida();
        Hamaca hamaca = new Hamaca();
        Rueda rueda = new Rueda();

        Jaula unaJaula = new Jaula(hamaca, rueda, comida);

        Hamster ham1 = new Hamster(unaJaula, "ham1");
        Hamster ham2 = new Hamster(unaJaula, "ham2");
        Hamster ham3 = new Hamster(unaJaula, "ham3");

        Thread ham1Thread = new Thread(ham1);
        Thread ham2Thread = new Thread(ham2);
        Thread ham3Thread = new Thread(ham3);

        ham1Thread.start();
        ham2Thread.start();
        ham3Thread.start();

        try {
            ham1Thread.join();
            ham2Thread.join();
            ham3Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Termina uso de jaula");
    }
}
