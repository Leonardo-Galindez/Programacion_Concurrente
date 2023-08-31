/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio2;

/**
 *
 * @author galin
 */
public class Energia {

    private int valor;

    public Energia(int valor) {
        this.valor = valor;
    }

    //Exclusion mutua
    //lock de sincrinizado
    public int ontenerEnergia() {
        return this.valor;
    }
    
    public  void modificarEnergia(int modificacion) {
        this.valor = this.valor + modificacion;
    }

    public  void setEnergia(int nuevaEnergia) {
        this.valor = nuevaEnergia;
    }
}
