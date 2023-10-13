/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio1;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Piscina piscina = new Piscina();
        Thread personas[] = new Thread[20];
        for (int i = 0; i < personas.length; i++) {
            personas[i] = new Thread(new Persona(piscina));
        }

        for (int i = 0; i < personas.length; i++) {
            personas[i].start();
        }
    }
}
