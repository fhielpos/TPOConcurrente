package semaforos.atletas;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Corredor implements Runnable {
    private Semaphore testigo;

    public Corredor(Semaphore semaforo){
        this.testigo = semaforo;
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
            this.testigo.acquire();
            System.out.println(Thread.currentThread().getName() +" agarrando testigo");
            correr();
            System.out.println("Liberando testigo: " +System.currentTimeMillis());
            this.testigo.release();

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
