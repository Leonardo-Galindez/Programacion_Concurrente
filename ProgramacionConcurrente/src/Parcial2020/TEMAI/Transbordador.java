package Parcial2020.TEMAI;

import java.util.Random;

public class Transbordador implements Runnable{
    private ControlTrasbordador controlTrasbordador;

    public Transbordador(ControlTrasbordador controlTrasbordador) {
        this.controlTrasbordador = controlTrasbordador;
    }

    public void run() {
        try {
            while (true) {
                controlTrasbordador.ir();
                viajando();
                controlTrasbordador.volver();
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
