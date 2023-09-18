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
        int[] arr = new int[10];
        Random random = new Random();
        int num;
        int sumaTotal = 0;
        Arreglo arreglo = new Arreglo(arr);

        for (int i = 0; i < arr.length; ++i) {
            num = random.nextInt(10) + 1;
            arr[i] = num;
        }
        int cantidadSub = arr.length / 10;

        SumaParcial suma1 = new SumaParcial(arreglo, 0, cantidadSub);
        SumaParcial suma2 = new SumaParcial(arreglo, cantidadSub, cantidadSub * 2);
        SumaParcial suma3 = new SumaParcial(arreglo, cantidadSub * 2, cantidadSub * 3);
        SumaParcial suma4 = new SumaParcial(arreglo, cantidadSub * 3, cantidadSub * 4);
        SumaParcial suma5 = new SumaParcial(arreglo, cantidadSub * 4, cantidadSub * 5);
        SumaParcial suma6 = new SumaParcial(arreglo, cantidadSub * 5, cantidadSub * 6);
        SumaParcial suma7 = new SumaParcial(arreglo, cantidadSub * 6, cantidadSub * 7);
        SumaParcial suma8 = new SumaParcial(arreglo, cantidadSub * 7, cantidadSub * 8);
        SumaParcial suma9 = new SumaParcial(arreglo, cantidadSub * 8, cantidadSub * 9);
        SumaParcial suma10 = new SumaParcial(arreglo, cantidadSub * 9, cantidadSub * 10);

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

        sumaTotal = suma1.getResultado() + suma2.getResultado() + suma3.getResultado() + suma4.getResultado() + suma5.getResultado() + suma6.getResultado() + suma7.getResultado() + suma8.getResultado() + suma9.getResultado() + suma10.getResultado();

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

        

    }

}
