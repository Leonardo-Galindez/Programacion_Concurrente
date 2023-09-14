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
        System.out.println(letra);
    }

}
