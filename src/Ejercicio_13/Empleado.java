/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_13;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Empleado implements Runnable {
    Confiteria confiteria;
    Boolean comio = false;
    
    public Empleado(Confiteria confiteria){
        this.confiteria = confiteria;
    }
    
    public void run(){
        while(!comio){
            try {
                Thread.sleep(new Random().nextInt(500));
                System.out.println("El " + Thread.currentThread().getName() + " desea servirse algo");
                if(confiteria.hayLugar()) sentarse();            
                else{
                    System.out.println("El asiento de la confiteria estaba ocupado, el " + Thread.currentThread().getName() + " sigue trabajando \n");
                    Thread.sleep(new Random().nextInt(10000));
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);                
            }
        }
    }
    
    public void sentarse() throws InterruptedException{
        System.out.println("El " + Thread.currentThread().getName() + " se sienta en la confiteria y le avisa al mozo");        
        confiteria.avisarMozo();
        confiteria.esperarComida();
        System.out.println("El " + Thread.currentThread().getName() + " empieza a comer");
        Thread.sleep(new Random().nextInt(3000));
        System.out.println("El " + Thread.currentThread().getName() + " termino de comer, se levanta y vuelve a trabajar");
        confiteria.levantarse();
        comio = true;
    }
}