/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exposicion;

/**
 *
 * @author galin
 */
public class Museo {

    private int capacidad;
    private int cantVisitantes;
    private boolean criticoEsperando, critico, responsable;

    public Museo(int capacidad) {
        this.capacidad = capacidad;//capacidad el museo
        this.cantVisitantes = 0;//visitantes en el museo
        this.criticoEsperando = false;//variable de control si hay critico esperando para entrar al museo
        this.critico = false;//variable de control si hay un critico en el museo
        this.responsable = false;//variable de control 
    }

    public void entrarVisitante() throws InterruptedException {
        while (this.criticoEsperando || this.critico || cantVisitantes >= capacidad) {
            System.out.println(Thread.currentThread().getName() + " TIENE QUE ESPERAR...");
            //ponemos al visitante en espera
            //this.notify(); //opcion 2
            this.wait();
        }
        //incremento
        this.cantVisitantes++;
    }

    public void entrarResponsable() throws InterruptedException {
        while (this.criticoEsperando || this.critico || this.responsable) {
            System.out.println(Thread.currentThread().getName() + " TIENE QUE ESPERAR...");
            //ponemos al responsable en espera 
            //this.notify(); //opcion 2
            this.wait();
        }
        this.responsable = true;
    }

    public void entrarCritico() throws InterruptedException {

        while (this.criticoEsperando || this.critico || this.responsable || this.cantVisitantes != 0) {
            System.out.println(Thread.currentThread().getName() + " TIENE QUE ESPERAR...");
            //ponemos al critico en espera
            //this.notify(); //opcion 2
            this.wait();
            this.criticoEsperando = true;
        }
        this.critico = true;
        this.criticoEsperando = true;
    }

    public void salirVisitante() {
        this.cantVisitantes--;
        //this.notify();//opcion 2
        this.notifyAll();
    }

    public void salirResponsable() {
        this.responsable = false;
        //this.notify();//opcion 2
        this.notifyAll();
    }

    public void salirCritico() {
        this.critico = false;
        //this.notify();//opcion 2
        this.notifyAll();
    }
}
