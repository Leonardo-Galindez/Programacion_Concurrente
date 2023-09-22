/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio6;

import java.util.Random;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {
        SumaTotal sumaTotal = new SumaTotal();
        int[] arr = new int[50000];
        Random random = new Random();
        int num;

        for (int i = 0; i < arr.length; ++i) {
            num = random.nextInt(10) + 1;
            arr[i] = num;
        }
        int cantidadSub = arr.length / 10;

        SumaParcial suma1 = new SumaParcial(arr, 0, cantidadSub, sumaTotal);
        SumaParcial suma2 = new SumaParcial(arr, cantidadSub, cantidadSub * 2, sumaTotal);
        SumaParcial suma3 = new SumaParcial(arr, cantidadSub * 2, cantidadSub * 3, sumaTotal);
        SumaParcial suma4 = new SumaParcial(arr, cantidadSub * 3, cantidadSub * 4, sumaTotal);
        SumaParcial suma5 = new SumaParcial(arr, cantidadSub * 4, cantidadSub * 5, sumaTotal);
        SumaParcial suma6 = new SumaParcial(arr, cantidadSub * 5, cantidadSub * 6, sumaTotal);
        SumaParcial suma7 = new SumaParcial(arr, cantidadSub * 6, cantidadSub * 7, sumaTotal);
        SumaParcial suma8 = new SumaParcial(arr, cantidadSub * 7, cantidadSub * 8, sumaTotal);
        SumaParcial suma9 = new SumaParcial(arr, cantidadSub * 8, cantidadSub * 9, sumaTotal);
        SumaParcial suma10 = new SumaParcial(arr, cantidadSub * 9, cantidadSub * 10, sumaTotal);

        Thread threadSuma1 = new Thread(suma1);
        Thread threadSuma2 = new Thread(suma2);
        Thread threadSuma3 = new Thread(suma3);
        Thread threadSuma4 = new Thread(suma4);
        Thread threadSuma5 = new Thread(suma5);
        Thread threadSuma6 = new Thread(suma6);
        Thread threadSuma7 = new Thread(suma7);
        Thread threadSuma8 = new Thread(suma8);
        Thread threadSuma9 = new Thread(suma9);
        Thread threadSuma10 = new Thread(suma10);

        threadSuma1.start();
        threadSuma2.start();
        threadSuma3.start();
        threadSuma4.start();
        threadSuma5.start();
        threadSuma6.start();
        threadSuma7.start();
        threadSuma8.start();
        threadSuma9.start();
        threadSuma10.start();

        try {
            threadSuma1.join();
            threadSuma2.join();
            threadSuma3.join();
            threadSuma4.join();
            threadSuma5.join();
            threadSuma6.join();
            threadSuma7.join();
            threadSuma8.join();
            threadSuma9.join();
            threadSuma10.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("La suma total es:" + sumaTotal.getSumaTotal());
    }

}
