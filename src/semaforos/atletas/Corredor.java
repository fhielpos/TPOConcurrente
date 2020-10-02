package semaforos.atletas;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Corredor implements Runnable {
    private Semaphore testigo;
    private int id;

    public Corredor(Semaphore semaforo, int unId){
        this.testigo = semaforo;
        this.id = unId;
    }

    private void correr(){
        int tiempo = 9 + new Random().nextInt(3);
        try{
            Thread.sleep(tiempo*1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            this.testigo.acquire(this.id);
            System.out.println(Thread.currentThread().getName() +" agarrando testigo");
            correr();
            System.out.println(Thread.currentThread().getName() +" liberando testigo: " +System.currentTimeMillis());
            this.testigo.release((this.id + 1));

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
