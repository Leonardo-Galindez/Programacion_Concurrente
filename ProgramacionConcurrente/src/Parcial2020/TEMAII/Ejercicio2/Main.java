package Parcial2020.TEMAII.Ejercicio2;

public class Main {
    public static void main(String[] args) {
        GestorCruce gestorCruce = new GestorCruce();

        Thread hiloAutos[] = new Thread[30];
        Thread hiloControl = new Thread(new ControlSemaforos(gestorCruce), "Control");

        for (int i = 0; i < hiloAutos.length; i++) {
            hiloAutos[i] = new Thread(new Auto(gestorCruce), "Auto " + i);
        }

        for (int i = 0; i < hiloAutos.length; i++) {
            hiloAutos[i].start();
            if (i == (hiloAutos.length / 2)) {
                hiloControl.start();
            }
        }

    }
}
