/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio5;

import java.util.Random;

/**
 *
 * @author galin
 */
public class Auto extends Vehiculo implements Runnable {

    private int litrosCombustible;
    private static final int consumoPorKm = 1;
    private Surtidor surtidor;
    private static final int max = 40;

    public Auto(int litrosCombustible, String matricula, String modelo, int km, String marca, Surtidor surtidor) {
        super(matricula, modelo, km, marca);
        this.litrosCombustible = litrosCombustible;
        this.surtidor = surtidor;
    }

    public int getLitrosCombustible() {
        return litrosCombustible;
    }

    public void setLitrosCombustible(int litrosCombustible) {
        this.litrosCombustible = this.litrosCombustible + litrosCombustible;
    }

    public static int getConsumoPorKm() {
        return consumoPorKm;
    }

    //cada 10 km comsume 1 litro de combustible
    public void conducir(int km) {
        if (this.litrosCombustible > km) {
            this.litrosCombustible = this.litrosCombustible - km;
        } else {
            //reserva
            System.out.println("Reserva");
            System.out.println("");
            System.out.println("Debe cargar Combustible");
            this.surtidor.modificarCapacidad(km);
        }
    }

    public void cargarCombustible(int litros) {
        if (max >= litros) {
            this.litrosCombustible = this.litrosCombustible + litros;
            System.out.println("se cargo combustible al auto");
        }
    }

    @Override
    public void run() {
        int cantCom;
        while (true) {
            if (litrosCombustible > 10) {
                conducir((new Random().nextInt(10) + 1));
                System.out.println("Estamos conduciendo" + Thread.currentThread().getName());
            } else {
                cantCom = (new Random().nextInt(10) + 1);
                if (surtidor.modificarCapacidad(cantCom)) {
                    System.out.println("se cargo combustible" + Thread.currentThread().getName());
                    cargarCombustible(cantCom);
                }
            }
        }
    }
}
