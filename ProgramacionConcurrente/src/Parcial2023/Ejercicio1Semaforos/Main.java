package Parcial2023.Ejercicio1Semaforos;

public class Main {
    public static void main(String[] args) {
        Planta planta = new Planta();
        String tipo;
        int num;

        Thread hiloEmbotellador[] = new Thread[10];
        Thread hiloTransportador = new Thread(new Transportador(planta), "Embotellador");
        Thread hiloEmpaquetador = new Thread(new Transportador(planta), "Embotellador");

        for (int i = 0; i < hiloEmbotellador.length / 2; i++) {
            hiloEmbotellador[i] = new Thread(new Embotellador(planta, "V"), "Embotellador " + i);
        }

        for (int i = hiloEmbotellador.length / 2; i < hiloEmbotellador.length; i++) {
            hiloEmbotellador[i] = new Thread(new Embotellador(planta, "A"), "Embotellador " + i);
        }
        
        hiloEmpaquetador.start();
        hiloTransportador.start();

        for (int i = 0; i < hiloEmbotellador.length; i++) {
            hiloEmbotellador[i].start();
        }

    }
}
