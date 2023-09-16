/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio4;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {

        Area area = new Area(3);
        
        Visitante visitante1 = new Visitante("Leonardo", area);
        Visitante visitante2 = new Visitante("David", area);
        Visitante visitante3 = new Visitante("Fernando", area);

        Thread visitante1Thread = new Thread(visitante1);
        Thread visitante2Thread = new Thread(visitante2);
        Thread visitante3Thread = new Thread(visitante3);

        visitante1Thread.start();
        visitante2Thread.start();
        visitante3Thread.start();

        try {
            visitante1Thread.join();
            visitante2Thread.join();
            visitante3Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Termino el Main");

    }
}
