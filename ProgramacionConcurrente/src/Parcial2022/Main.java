/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parcial2022;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Parque parque = new Parque(10);

        Thread hiloVisitante[] = new Thread[15];
        Thread hiloVisitanteLocal[] = new Thread[15];

        for (int i = 0; i < hiloVisitante.length; i++) {
            hiloVisitante[i] = new Thread(new Visitante(parque));
        }

        for (int i = 0; i < hiloVisitanteLocal.length; i++) {
            hiloVisitanteLocal[i] = new Thread(new VisitanteLocal(parque));
        }
        for (int i = 0; i < hiloVisitanteLocal.length; i++) {
            hiloVisitanteLocal[i].start();
            hiloVisitante[i].start();
        }
        

    }
}
