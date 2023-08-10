package TP_0;


import TP_0.Guitarra;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author galin
 */
//extends palabra reservada para subClases
public class Ukelele extends Guitarra{
    public void tocar(){
        System.out.println("Ukelele.tocar()");
    }
    
    public String tipo(){
        return "Ukelele";
    }
}
