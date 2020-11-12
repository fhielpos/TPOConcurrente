package tp6.museo_mon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Sala {
    private int capacidadSala;

    private int capacidadMaxima;
    private int personasDentro;
    private int temperatura;
    private int capacidadReducida = 35;

    public Sala(int limiteSala) {
        this.personasDentro = 0;
        this.capacidadSala = limiteSala;
        this.capacidadMaxima = limiteSala;
    }

    public Sala(int maximo, int minimo) {
        this.personasDentro = 0;
        this.capacidadSala = maximo;
        this.capacidadMaxima = maximo;
        this.capacidadReducida = minimo;
    }

    public synchronized void restringirCapacidad() {
        this.capacidadSala = this.capacidadReducida;
    }

    public synchronized void restaurarCapacidad() {
        this.capacidadSala = this.capacidadMaxima;
    }

    public synchronized void entrarSala() {
        try {
            while (personasDentro >= capacidadSala) {
                System.out.println("Sala llena esperando");
                this.wait();
            }
            this.personasDentro++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    public synchronized void entrarSalaJubilado() {
        try {
            while (personasDentro >= capacidadSala) {
                System.out.println("Sala llena jubilado esperando");
                this.wait();
            }
            this.personasDentro++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void salirSala() {
        this.personasDentro--;
//        if (this.lockSala.hasWaiters(jubilado)) {
//           jubilado.signalAll();
//        } else {
//            this.notifyAll();
//        }
    }

    public synchronized int getCapacidad() {
        return this.capacidadSala;
    }

    public synchronized int getTemperatura() {
        return this.temperatura;
    }

    public synchronized void setTemperatura(int nuevaTemp) {
        this.temperatura = nuevaTemp;
    }

    public synchronized void setCapacidad(int nuevaCapacidad) {
        this.capacidadSala = nuevaCapacidad;
    }
}
