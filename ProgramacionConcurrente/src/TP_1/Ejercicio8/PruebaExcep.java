/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_1.Ejercicio8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author galin
 */
public class PruebaExcep {

    public static void validarEdad(int edad) throws EdadException {
        if (edad < 18) {
            throw new EdadException("La persona es menor de edad.");
        }
    }

    public static void ruleta(int numero) throws NumeroException {
        int min = 0; // Valor mínimo del intervalo
        int max = 30; // Valor máximo del intervalo

        int numeroAleatorio = ThreadLocalRandom.current().nextInt(min, max + 1);
        if (numeroAleatorio != numero) {
            throw new NumeroException("Mala suerte");
        }
    }

    public static void ingresoNumeros() throws RuntimeException {
        Scanner sc = new Scanner(System.in);
        int[] numeros = new int[5];
        int num;
        for (int i = 0; i < 5; i++) {
            System.out.println("Ingrese numero");
            num = sc.nextInt();
            numeros[i] = num;
        }

        for (int j = 0; j < 7; j++) {
            System.out.println(numeros[j]);
        }

    }

    public static void main(String[] args) {
        try {
            ingresoNumeros();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            ruleta(10);
        } catch (NumeroException e) {
            System.out.println(e.getMessage());
        }
        try {
            validarEdad(14);
        } catch (EdadException e) {
            System.err.println(e.getMessage());
        }*/
    }
}
