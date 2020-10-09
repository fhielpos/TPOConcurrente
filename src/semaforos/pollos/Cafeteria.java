package semaforos.pollos;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


public class Cafeteria {
    private int capacidad;
    private int ocupacion = 0;
    private boolean lleno;
    private Semaphore semMozo = new Semaphore(1);
    private Semaphore semCocinero = new Semaphore(0);
    private ReentrantLock lockCapacidad = new ReentrantLock();

    public Cafeteria(int unaCapacidad) throws Exception {
        this.capacidad = unaCapacidad;
        if (this.capacidad == 0)
            throw new Exception("La capacidad de la cafeteria debe ser mayor que cero");
    }

    public synchronized boolean estaLleno() {
        return this.lleno;
    }

    public void inventar() {
        try {
            this.semMozo.acquire();
            System.out.println("Mozo inventando cosas");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void solicitarAtencion() {
        this.semMozo.release();
        try {
            this.semMozo.acquire();
            System.out.println("Ofrecer opciones al cliente");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void comer() {
        this.semMozo.release();
        try {
            System.out.println(Thread.currentThread().getName() + " comiendo");
            Thread.sleep(new Random().nextInt(1000) + 500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    public void salir() {
        this.lockCapacidad.lock();
        System.out.println(Thread.currentThread().getName() + " saliendo de la cafeteria");
        this.ocupacion--;
        this.lleno = false;
        this.lockCapacidad.unlock();
    }

    public boolean entrar() {
        this.lockCapacidad.lock();
        if (!this.estaLleno()) {
            System.out.println(Thread.currentThread().getName() + " entrando a la cafeteria");
            this.ocupacion++;
            if (this.ocupacion == this.capacidad) {
                this.lleno = true;
            }
            this.lockCapacidad.unlock();
            return true;
        } else
            return false;
    }
}
