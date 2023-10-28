/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exposicion;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Museo museo = new Museo(5);

        Thread hiloVisitantes[] = new Thread[5];
        Thread hiloResponsable[] = new Thread[2];
        Thread hiloCriticos[] = new Thread[2];

        for (int i = 0; i < hiloVisitantes.length; i++) {
            hiloVisitantes[i] = new Thread(new Visitante(museo),"Visitante");
        }

        for (int i = 0; i < hiloResponsable.length; i++) {
            hiloResponsable[i] = new Thread(new Responsable(museo),"Responsable");
        }

        for (int i = 0; i < hiloCriticos.length; i++) {
            hiloCriticos[i] = new Thread(new Critico(museo),"Critico");
        }

        for (int i = 0; i < hiloVisitantes.length; i++) {
            hiloVisitantes[i].start();
        }

        for (int i = 0; i < hiloResponsable.length; i++) {
            hiloResponsable[i].start();
        }

        for (int i = 0; i < hiloCriticos.length; i++) {
            hiloCriticos[i].start();
        }

    }
}
