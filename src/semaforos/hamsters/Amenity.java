package semaforos.hamsters;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Amenity {
        private ReentrantLock lock = new ReentrantLock();
        private String nombre;

    // Clase que define los amenities dentro de la jaula de Hamsters

    public Amenity(String unNombre){
        this.nombre = unNombre;
    }

    public void usar() throws InterruptedException {
        int sleep = (new Random().nextInt(500) + 500);
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " usando: " +this.nombre);
        try{
            Thread.sleep(sleep);
        } finally {
            lock.unlock();
        }
    }
}
