/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExposicionLocks;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Museo museo = new Museo(10);

        Thread hiloVisitante[] = new Thread[30];
        Thread hiloResponsable[] = new Thread[4];
        Thread hiloCritico[] = new Thread[3];

        for (int i = 0; i < hiloVisitante.length; i++) {
            hiloVisitante[i] = new Thread(new Visitante(museo));
            if (i < hiloResponsable.length) {
                hiloResponsable[i] = new Thread(new Responsable(museo));
            }
            if (i < hiloCritico.length) {
                hiloCritico[i] = new Thread(new Critico(museo));
            }
        }

        for (int i = 0; i < hiloVisitante.length; i++) {
            hiloVisitante[i].start();
        }

        for (int i = 0; i < hiloCritico.length; i++) {
            hiloCritico[i].start();
        }

        for (int i = 0; i < hiloResponsable.length; i++) {
            hiloResponsable[i].start();
        }

    }
}
