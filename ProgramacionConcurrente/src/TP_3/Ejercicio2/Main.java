/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Energia energia = new Energia(10);

        CriaturaOscura criaturaOscura = new CriaturaOscura(energia);
        Sanador sanador = new Sanador(energia);

        Thread criaturaThread = new Thread(criaturaOscura);
        Thread sanadorThread = new Thread(sanador);

        criaturaThread.start();
        sanadorThread.start();
        
        try {
            criaturaThread.join();
            sanadorThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Termina main con energia: " + energia.ontenerEnergia());

    }

}
