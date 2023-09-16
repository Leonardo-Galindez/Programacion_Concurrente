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

    private int resultado;
    private Arreglo arreglo;
    private int inicio;
    private int fin;

    public SumaParcial(Arreglo arreglo, int inicio, int fin) {
        this.resultado = 0;
        this.arreglo = arreglo;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int num) {
        this.resultado = this.resultado + num;
    }

    @Override
    public void run() {
        //Sumamos los elementos del arreglo
        for (int i = inicio; i < fin; i++) {
            this.setResultado(this.arreglo.getArreglo()[i]);
        }
    }

}
