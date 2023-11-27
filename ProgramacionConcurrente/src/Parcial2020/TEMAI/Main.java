package Parcial2020.TEMAI;

public class Main {
    public static void main(String[] args) {
        ControlTrasbordador controlTrasbordador = new ControlTrasbordador();

        Thread hiloAutos[] = new Thread[25];
        Thread hiloTransbordador = new Thread(new Transbordador(controlTrasbordador), "Transbordador");

        for (int i = 0; i < hiloAutos.length; i++) {
            hiloAutos[i] = new Thread(new Auto(controlTrasbordador), "Auto " + i);
        }

        hiloTransbordador.start();
        
        for (int i = 0; i < hiloAutos.length; i++) {
            hiloAutos[i].start();
        }

    }
}
