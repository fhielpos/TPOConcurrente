/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_13;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Mozo implements Runnable{
    Boolean hayEmpleado = false;
    Confiteria confiteria;
    
    public Mozo(Confiteria confiteria){
        this.confiteria = confiteria;
    }
    
    public void run(){
        hobby();
    }
    
    public void hobby(){
        System.out.println("El mozo esta disfrutando su hobby");
        while(!hayEmpleado){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mozo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        atenderEmpleado();
    }
    
    public void atenderEmpleado(){
        System.out.println("Se toma la orden del empleado y se la pasa al cocinero");
        confiteria.prepararComida();
        System.out.println("El mozo le lleva la comida al empleado");
        confiteria.comidaServida();
        try {
            confiteria.comensal().join();       //Solo valido si el thread muere
        } catch (InterruptedException ex) {
            Logger.getLogger(Mozo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(confiteria.comensalesServidos() < 6) hobby();
        else System.out.println("Ya no hay mas empleados para servir, el mozo cierra la confiteria por el dia");
    }
    
    public synchronized void avisoEmpleado(){
        hayEmpleado = !hayEmpleado;
    }
}