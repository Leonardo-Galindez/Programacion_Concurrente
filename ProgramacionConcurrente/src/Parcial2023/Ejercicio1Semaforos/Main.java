package Parcial2023.Ejercicio1Semaforos;

public class Main {
    public static void main(String[] args) {
        Planta planta = new Planta();
        String tipo;
        int num, i = 0;

        Thread hiloEmbotellador[] = new Thread[10];
        Thread hiloTransportador = new Thread(new Transportador(planta), "Transportador");
        Thread hiloEmpaquetador = new Thread(new Empaquetador(planta), "Empaquetador");

        for (i = 0; i < hiloEmbotellador.length / 2; i++) {
            hiloEmbotellador[i] = new Thread(new Embotellador(planta, "V"), "Embotellador-V " + i);
        }

        for (int j = 0; j < hiloEmbotellador.length / 2; j++) {
            hiloEmbotellador[i] = new Thread(new Embotellador(planta, "A"), "Embotellador-A " + j);
            i++;
        }

        for (i = 0; i < hiloEmbotellador.length; i++) {
            hiloEmbotellador[i].start();
        }

        hiloEmpaquetador.start();
        hiloTransportador.start();

    }
}
