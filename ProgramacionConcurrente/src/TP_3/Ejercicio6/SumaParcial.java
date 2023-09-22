/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio6;

/**
 *
 * @author galin
 */
public class SumaParcial implements Runnable {

    private SumaTotal sumaTotal;
    private int[] arreglo;
    private int inicio;
    private int fin;

    public SumaParcial(int[] arreglo, int inicio, int fin, SumaTotal sumaTotal) {
        this.sumaTotal = sumaTotal;
        this.arreglo = arreglo;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        //Sumamos los elementos del arreglo
        System.out.println();
        for (int i = inicio; i < fin; i++) {
            this.sumaTotal.sumar(this.arreglo[i]);
        }
        System.out.println("Suma parcial:" + this.sumaTotal.getSumaTotal());
    }

}
