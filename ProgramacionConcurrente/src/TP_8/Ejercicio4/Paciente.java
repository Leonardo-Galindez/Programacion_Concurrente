/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_8.Ejercicio4;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Paciente implements Runnable {

    private CentroHometerapia centro;

    public Paciente(CentroHometerapia centro) {
        this.centro = centro;
    }

    public void run() {
        try {
            int tipo;
            String tipo2 = "P";
            tipo = new Random().nextInt(2) + 1;
            switch (tipo) {
                case 1:
                    tipo2 = centro.ingresarSala("S");
                    break;
                case 2:
                    tipo2 = centro.ingresarSala("P");
                    break;
            }
            tipo = new Random().nextInt(2) + 1;
            boolean revista = false;
            switch (tipo) {
                case 1:
                    revista = centro.tomarRevista();
                    break;
            }
            centro.ocuparCamilla(revista);
            atendiendo();
            centro.salirCamilla();
            centro.salirCentro(tipo2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atendiendo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
