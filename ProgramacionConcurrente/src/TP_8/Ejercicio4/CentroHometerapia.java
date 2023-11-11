/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_8.Ejercicio4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class CentroHometerapia {

    private Semaphore sala;
    private Semaphore sentados;
    private Semaphore parados;
    private Semaphore camilla;
    private Semaphore revistas;
    private Semaphore permisoSalida;
    private Queue colaEspera;
    private Semaphore mutex;
    private int contSillas = 0;
    private int contParados = 0;
    private int contCamillas = 4;

    public CentroHometerapia() {
        this.sala = new Semaphore(10, true);
        this.sentados = new Semaphore(5, true);
        this.parados = new Semaphore(5, true);
        this.camilla = new Semaphore(0, true);
        this.revistas = new Semaphore(9, true);
        this.mutex = new Semaphore(1);
        this.permisoSalida = new Semaphore(0);
        this.colaEspera = new LinkedList();
    }

    public String ingresarSala(String tipo) throws InterruptedException {
        mutex.acquire();
        sala.acquire();
        System.out.println("El paciente " + Thread.currentThread().getName() + " ingreso a la sala");
        if (tipo.equals("S")) {
            System.out.println("El paciente " + Thread.currentThread().getName() + " Ingreso a la sala");
            contSillas++;
        } else {
            contParados++;
        }
        mutex.release();
        return tipo;
    }

    public boolean tomarRevista() throws InterruptedException {
        revistas.acquire();
        System.out.println("El paciente " + Thread.currentThread().getName() + " tomo una revista");
        return true;
    }

    public void salirSala(String tipo) throws InterruptedException {
        mutex.acquire();
        System.out.println("Libera a paciente");
        sala.release();
        contCamillas++;//aumenta la cantidad de camillas
        mutex.release();
    }

    public void ocuparCamilla(boolean revista) throws InterruptedException {
        mutex.acquire();
        if (revista) {
            revistas.release();
        }
        mutex.release();
        camilla.acquire();
        System.out.println("El paciente " + Thread.currentThread().getName() + " tomo una camilla");
        System.out.println("EL paciente " + Thread.currentThread().getName() + " libero la sala");
        sala.release();//libera la sala
        mutex.acquire();//restar sentados y parados
        contCamillas++;
        mutex.release();
    }

    public void salirCamilla() throws InterruptedException {
        mutex.acquire();
        System.out.println("El paciente " + Thread.currentThread().getName() + " libero la camilla");
        camilla.release();
        contCamillas--;
        mutex.release();

    }
}
