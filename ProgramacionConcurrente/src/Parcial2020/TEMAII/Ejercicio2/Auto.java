package Parcial2020.TEMAII.Ejercicio2;

import java.util.Random;

public class Auto implements Runnable {
    private GestorCruceSemaforo gestorCruce;

    public Auto(GestorCruceSemaforo gestorCruce) {
        this.gestorCruce = gestorCruce;
    }

    public void run() {
        int num = new Random().nextInt(2) + 1;
        switch (num) {
            case 1:
                try {
                    gestorCruce.llegaNorte();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cruzando();
                gestorCruce.sale();
                break;
            case 2:
                try {
                    gestorCruce.llegaOeste();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cruzando();
                gestorCruce.sale();
                break;
        }
    }

    public void cruzando() {
        try {
            //System.out.println("Cruzando...");
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
