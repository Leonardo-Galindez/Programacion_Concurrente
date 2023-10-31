/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio1;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        GestorTrafico gestor = new GestorTrafico();

        Thread hilosAutosSur[] = new Thread[9];
        Thread hilosAutosNorte[] = new Thread[14];

        for (int i = 0; i < hilosAutosSur.length; i++) {
            hilosAutosSur[i] = new Thread(new AutoSur(gestor));
        }

        for (int i = 0; i < hilosAutosSur.length; i++) {
            hilosAutosSur[i].start();
        }

        for (int i = 0; i < hilosAutosNorte.length; i++) {
            hilosAutosNorte[i] = new Thread(new AutoNorte(gestor));
        }

        for (int i = 0; i < hilosAutosNorte.length; i++) {
            hilosAutosNorte[i].start();
        }
    }
}
