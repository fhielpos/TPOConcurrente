package tp6.museo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Sala {
    private ReentrantLock lockSala = new ReentrantLock();
    private final Condition jubilado;
    private final Condition visitante;

    private int capacidadSala;
    private int capacidadMaxima;
    private int personasDentro;
    private int temperatura;
    private int capacidadReducida = 35;

    public Sala(int limiteSala) {
        this.personasDentro = 0;
        this.capacidadSala = limiteSala;
        this.capacidadMaxima = limiteSala;
        this.jubilado = lockSala.newCondition();
        this.visitante = lockSala.newCondition();
    }

    public void restringirCapacidad() {
        this.lockSala.lock();
        this.capacidadSala = this.capacidadReducida;
        this.lockSala.unlock();
    }

    public void restaurarCapacidad() {
        this.lockSala.lock();
        this.capacidadSala = this.capacidadMaxima;
        this.lockSala.unlock();
    }

    public void entrarSala() {
        try {
            lockSala.lock();
            while (personasDentro >= capacidadSala) {
                System.out.println("Sala llena esperando");
                visitante.await();
            }
            this.personasDentro++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lockSala.unlock();
        }
    }


    public void entrarSalaJubilado() {
        try {
            lockSala.lock();
            while (personasDentro >= capacidadSala) {
                System.out.println("Sala llena jubilado esperando");
                jubilado.await();
            }
            this.personasDentro++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lockSala.unlock();
        }
    }

    public void salirSala() {
        lockSala.lock();
        this.personasDentro--;
        if (this.lockSala.hasWaiters(jubilado)) {
            jubilado.signalAll();
        } else {
            visitante.signalAll();
        }
        lockSala.unlock();
    }

    public int getCapacidad(){
        return this.capacidadSala;
    }

    public int getTemperatura() {
        return this.temperatura;
    }

    public void setTemperatura(int nuevaTemp){
        this.temperatura = nuevaTemp;
    }

}
