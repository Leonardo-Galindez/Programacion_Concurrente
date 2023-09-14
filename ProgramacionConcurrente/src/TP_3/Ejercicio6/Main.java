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
        int[] arr = new int[50000];
        Random random = new Random();
        int num;    
        
        for (int i = 0; i < arr.length; ++i) {
            num = random.nextInt(10) + 1;
            arr[i] = num;
        }
    }

}
