/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_7.Ejercicio2;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Proyecto proyecto = new Proyecto(10);
        Thread hiloProgramadores[] = new Thread[30];
        for (int i = 0; i < hiloProgramadores.length; i++) {
            hiloProgramadores[i] = new Thread(new Programador(proyecto));
        }

        for (int i = 0; i < hiloProgramadores.length; i++) {
            hiloProgramadores[i].start();
        }
    }
}
