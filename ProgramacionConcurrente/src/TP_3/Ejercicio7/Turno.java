/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio7;

/**
 *
 * @author galin
 */
public class Turno {
//agregar diccionario
    private int turno;
    private char letra;

    public Turno() {
        this.turno = 1;
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized void setTurno(int turno) {
        this.turno = turno;
    }
}
