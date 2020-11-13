package tp6.museo_mon;
/**
 * @author Admin
 */

import java.util.concurrent.locks.ReentrantLock;

public class Sala {
    private int capacidadSala;      // Capacidad actual
    private int capacidadMaxima;    // Capacidad maxima
    private int capacidadReducida = 35; // Capacidad minima

    private int personasDentro;
    private int temperatura;

    private final Object monitorJubilados = new Object();
    private final Object monitorRegular = new Object();
    private boolean jubiladosEsperando = false;
    private int cantJubiladosEsperando = 0;
    ReentrantLock lockMetodos = new ReentrantLock();

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

    public void entrarSala() throws InterruptedException {
        lockMetodos.lock();
        while (personasDentro >= capacidadSala) {
            // Si esta lleno, espero
            System.out.println("Sala llena, " + Thread.currentThread().getName() + " esperando");
            lockMetodos.unlock();
            synchronized (monitorRegular) {
                monitorRegular.wait();
            }
            lockMetodos.lock();
        }
        this.personasDentro++;
        lockMetodos.unlock();
    }

    public void entrarSalaJubilado() throws InterruptedException {
        boolean yaEstabaEsperando = false;
        lockMetodos.lock();
        while (personasDentro >= capacidadSala) {
            // Si esta lleno, espero
            System.out.println("Sala llena, " + Thread.currentThread().getName() + " esperando");
            if (!yaEstabaEsperando) {
                jubiladosEsperando = true;
                cantJubiladosEsperando++;
                yaEstabaEsperando = true;
                System.out.println("\n hay " + cantJubiladosEsperando + " jubilados esperando  \n");
            }
            lockMetodos.unlock();
            synchronized (monitorJubilados) {
                monitorJubilados.wait();
            }
            lockMetodos.lock();
        }
        this.personasDentro++;
        lockMetodos.unlock();
    }

    public void salirSala() {
        lockMetodos.lock();
        this.personasDentro--;
        if (jubiladosEsperando) {
            synchronized (monitorJubilados) {
                monitorJubilados.notify();
            }
            cantJubiladosEsperando--;
            System.out.println("\n hay " + cantJubiladosEsperando + " jubilados esperando  \n");
        } else {
            synchronized (monitorRegular) {
                monitorRegular.notify();
            }
        }
        if (cantJubiladosEsperando == 0) {
            System.out.println("NO HAY JUBILADOS ESPERANDO");
            jubiladosEsperando = false;
        }
        lockMetodos.unlock();
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