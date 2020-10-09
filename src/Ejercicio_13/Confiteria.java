/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_13;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Confiteria {
    Lock asiento = new ReentrantLock();
    Semaphore comidaLista = new Semaphore(0);
    Mozo mozo = new Mozo(this);
    Thread empleadoComiendo;
    int empleadosServidos = 0;
    
    public Confiteria(){
        new Thread(mozo, "Mozo").start();
    }
    
    public boolean hayLugar(){
        return asiento.tryLock();   
    }
    
    public void avisarMozo(){
        empleadoComiendo = Thread.currentThread();
        mozo.avisoEmpleado();
    }
    
    public void prepararComida(){
        try {
            System.out.println("Se esta preparando la comida del empleado");
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Comida preparada");
    }
    
    public void esperarComida() throws InterruptedException{
        comidaLista.acquire();
    }
    
    public void comidaServida(){
        comidaLista.release();
    }
    
    public Thread comensal(){
        return empleadoComiendo;
    }
    
    public int comensalesServidos(){
        return empleadosServidos;
    }
    
    public void levantarse(){
        asiento.unlock();
        mozo.avisoEmpleado();
        empleadosServidos++;
    }
}