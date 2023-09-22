/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio5;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {

        Surtidor unSurtidor = new Surtidor(1000);

        Auto auto1 = new Auto(12, "111", "2019", 10, "Renault", unSurtidor);
        Auto auto2 = new Auto(34, "222", "2019", 20, "Renault", unSurtidor);
        Auto auto3 = new Auto(10, "333", "2019", 5, "Renault", unSurtidor);
        Auto auto4 = new Auto(15, "444", "2019", 4, "Renault", unSurtidor);
        Auto auto5 = new Auto(11, "555", "2019", 5, "Renault", unSurtidor);

        Thread threadAuto1 = new Thread(auto1);
        Thread threadAuto2 = new Thread(auto2);
        Thread threadAuto3 = new Thread(auto3);
        Thread threadAuto4 = new Thread(auto4);
        Thread threadAuto5 = new Thread(auto5);

        threadAuto1.start();
        threadAuto2.start();
        threadAuto3.start();
        threadAuto4.start();
        threadAuto5.start();

        try {
            threadAuto1.join();
            threadAuto2.join();
            threadAuto3.join();
            threadAuto4.join();
            threadAuto5.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
}
