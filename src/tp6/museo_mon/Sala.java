package tp6.museo_mon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Sala {
    private int capacidadSala;      // Capacidad actual
    private int capacidadMaxima;    // Capacidad maxima
    private int capacidadReducida = 35; // Capacidad minima

    private int personasDentro;
    private int temperatura;

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
                // Si esta lleno, espero
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
                // Si esta lleno, espero
                System.out.println("Sala llena jubilado esperando");
                // FALTA DISCRIMINAR JUBILADOS DE VISITANTES
                this.wait();
            }
            this.personasDentro++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void salirSala() {
        this.personasDentro--;
        // FALTA VERIFICAR QUE NO HAYA JUBILADOS ESPERANDO
        this.notify();
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
