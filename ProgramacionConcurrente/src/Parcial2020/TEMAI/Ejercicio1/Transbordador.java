package Parcial2020.TEMAI.Ejercicio1;

import java.util.Random;

public class Transbordador implements Runnable{
    private ControlTrasnbordadorLock controlTrasbordador;

    public Transbordador(ControlTrasnbordadorLock controlTrasbordador) {
        this.controlTrasbordador = controlTrasbordador;
    }

    public void run() {
        try {
            while (true) {
                controlTrasbordador.irLock();
                Thread.sleep((new Random()).nextInt(1000) + 1000);
                viajando();
                controlTrasbordador.destinoLock();
                controlTrasbordador.volverLock();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void viajando() {
        try {
            System.out.println("Viajando...");
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
