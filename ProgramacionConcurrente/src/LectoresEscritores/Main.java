/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LectoresEscritores;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        Libro libro = new Libro();
        Thread lectores[] = new Thread[4];
        Thread escritores[] = new Thread[3];

        for (int i = 0; i < lectores.length; i++) {
            lectores[i] = new Thread(new Lector(libro));
        }

        for (int i = 0; i < escritores.length; i++) {
            escritores[i] = new Thread(new Escritor(libro));
        }
        for (int i = 0; i < escritores.length; i++) {
            escritores[i].start();
        }
        for (int i = 0; i < lectores.length; i++) {
            lectores[i].start();
        }

    }

}
