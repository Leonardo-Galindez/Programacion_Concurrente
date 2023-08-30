/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio2;

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

       //los join son necesarios
       //en que afecta no poner join es estos casos
       //cuando es necesario
        System.out.println("Termina main");

    }

}
