/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio7;

/**
 *
 * @author galin
 */
public class Letra implements Runnable {

    private char letra;
    private int repeticiones;
    private Turno turno;

    public Letra(char letra, int repeticiones, Turno turno) {
        this.letra = letra;
        this.repeticiones = repeticiones;
        this.turno = turno;
    }

    public char getLetra() {
        return letra;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    @Override
    public void run() {
        while (true) {
            //consutlar 
            System.out.println(Thread.currentThread().getName());
            if (this.turno.getTurno() == letra) {

                for (int i = 0; i < this.repeticiones; i++) {
                    System.out.println(this.letra);
                }
                switch (this.letra) {
                    case 'A':
                        this.turno.setTurno('B');
                        break;
                    case 'B':
                        this.turno.setTurno('C');
                        break;
                    case 'C':
                        this.turno.setTurno('A');
                        break;
                }
            }
        }

    }

}
