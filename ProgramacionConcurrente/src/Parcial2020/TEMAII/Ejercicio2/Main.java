package Parcial2020.TEMAII.Ejercicio2;

public class Main {
    public static void main(String[] args) {
        GestorCruceSemaforo gestorCruce = new GestorCruceSemaforo();

        Thread hiloAutos[] = new Thread[100];
        Thread hiloControl = new Thread(new ControlSemaforos(gestorCruce), "Control");

        for (int i = 0; i < hiloAutos.length; i++) {
            hiloAutos[i] = new Thread(new Auto(gestorCruce), "Auto " + i);
        }
        hiloControl.start();
        for (int i = 0; i < hiloAutos.length; i++) {
            hiloAutos[i].start();
        }

    }
}
