package tp6.museo;

import java.util.Random;

public class Visitante implements Runnable {
    private boolean jubilado;
    private Sala museo;

    public Visitante(boolean esJubilado, Sala unaSala){
        this.jubilado = esJubilado;
        this.museo = unaSala;
    }

    public void run(){
        if (this.jubilado)
            museo.entrarSalaJubilado();
        else
            museo.entrarSala();
        try{
            System.out.println(Thread.currentThread().getName() +" esta en la sala, durmiendo...");
            Thread.sleep(new Random().nextInt(1500)+5000);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" saliendo de la sala");
        museo.salirSala();
    }
}
