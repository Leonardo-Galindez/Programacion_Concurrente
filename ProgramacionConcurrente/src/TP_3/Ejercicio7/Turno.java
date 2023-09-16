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

    private char letra;

    public Turno(char letra) {
        this.letra = letra;
    }

    public int getTurno() {
        return letra;
    }

    public void setTurno(char letra) {
        this.letra = letra;
    }
}
