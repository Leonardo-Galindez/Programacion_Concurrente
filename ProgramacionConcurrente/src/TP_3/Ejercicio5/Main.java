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

        Auto auto1 = new Auto(30, "111", "2019", 100, "Renault");
        Auto auto2 = new Auto(10, "222", "2019", 200, "Renault");
        Auto auto3 = new Auto(80, "333", "2019", 300, "Renault");
        Auto auto4 = new Auto(100, "444", "2019", 400, "Renault");
        Auto auto5 = new Auto(50, "555", "2019", 500, "Renault");

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
