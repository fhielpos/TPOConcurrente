/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_8;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Atleta implements Runnable{
    Semaphore testigo;
    
    public void run(){
        try {
            if(testigo.tryAcquire()){                      
               System.out.println(Thread.currentThread().getName() + " empieza a correr");
               Thread.sleep(ThreadLocalRandom.current().nextInt(9000, 11000 + 1));
               testigo.release();
               System.out.println(Thread.currentThread().getName() + " pasa el testigo");
            }
            
            else{
                System.out.println(Thread.currentThread().getName() + " esperando el testigo");
                testigo.acquire();
                System.out.println(Thread.currentThread().getName() + " comienza a correr");
                Thread.sleep(ThreadLocalRandom.current().nextInt(9000, 11000 + 1));
                if(testigo.hasQueuedThreads()){
                    System.out.println(Thread.currentThread().getName() + " pasa el testigo");
                    testigo.release();                    
                }
                else{
                    System.out.println(Thread.currentThread().getName() + " llega a la meta");
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Atleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Atleta(Semaphore testigo){
        this.testigo = testigo;
    }    
}
