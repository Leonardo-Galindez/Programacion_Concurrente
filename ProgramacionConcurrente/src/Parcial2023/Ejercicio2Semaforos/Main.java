package Parcial2023.Ejercicio2Semaforos;

public class Main {
    public static void main(String[] args) {
        Agua agua = new Agua(4);

        Thread hiloHidrogeno[] = new Thread[20];
        Thread hiloOxigeno[] = new Thread[10];

        for (int i = 0; i < hiloHidrogeno.length; i++) {
            hiloHidrogeno[i] = new Thread(new Hidrogeno(agua), "Hidrogeno " + i);
        }

        for (int i = 0; i < hiloOxigeno.length; i++) {
            hiloOxigeno[i] = new Thread(new Oxigeno(agua), "Oxigeno " + i);
        }

        for (int i = 0; i < hiloOxigeno.length; i++) {
            hiloOxigeno[i].start();
        }

        for (int i = 0; i < hiloHidrogeno.length; i++) {
            hiloHidrogeno[i].start();
        }

    }
}
