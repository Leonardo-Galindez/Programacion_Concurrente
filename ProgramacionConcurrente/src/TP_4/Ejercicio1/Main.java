/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio1;

/**
 *
 * @author galin
 */
public class Main {

    public static void main(String[] args) {

        Contador c = new Contador();

        SynchronizedCounter contador1 = new SynchronizedCounter(c);
        SynchronizedCounter contador2 = new SynchronizedCounter(c);
        SynchronizedCounter contador3 = new SynchronizedCounter(c);

        Thread threadCont1 = new Thread(contador1);
        Thread threadCont2 = new Thread(contador2);
        Thread threadCont3 = new Thread(contador3);

        threadCont1.start();
        threadCont2.start();
        threadCont3.start();

        try {
            threadCont1.join();
            threadCont2.join();
            threadCont3.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        /*SynchronizedObjectCounter cont1 = new SynchronizedObjectCounter();
        SynchronizedObjectCounter cont2 = new SynchronizedObjectCounter();
        SynchronizedObjectCounter cont3 = new SynchronizedObjectCounter();
        
        cont1.increment();
        cont2.increment();
        cont3.increment();
        
        System.out.println(cont1.value());
        System.out.println(cont2.value());
        System.out.println(cont3.value());*/
    }
}
