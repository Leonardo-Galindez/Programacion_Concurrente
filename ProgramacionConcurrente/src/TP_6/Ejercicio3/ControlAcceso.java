/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio3;

/**
 *
 * @author galin
 */
public class ControlAcceso {

    public static void main(String[] args) {
        Sala sala = new Sala(5);
        Thread hiloEstudiantes[] = new Thread[20];

        for (int i = 0; i < hiloEstudiantes.length; i++) {
            hiloEstudiantes[i] = new Thread(new Estudiante(sala));
        }
        
        for(int i=0;i<hiloEstudiantes.length;i++){
            hiloEstudiantes[i].start();
        }

    }

}
