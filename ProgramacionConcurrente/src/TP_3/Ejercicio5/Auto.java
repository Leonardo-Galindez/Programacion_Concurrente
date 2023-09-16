/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio5;

/**
 *
 * @author galin
 */
public class Auto extends Vehiculo implements Runnable {

    private int litrosCombustible;
    private static final int consumoPorKm = 1;
    private Surtidor surtidor;

    public Auto(int litrosCombustible, String matricula, String modelo, int km, String marca) {
        super(matricula, modelo, km, marca);
        this.litrosCombustible = litrosCombustible;
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

    public void run() {
        
    }
}
