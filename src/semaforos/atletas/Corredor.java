package semaforos.atletas;

import java.util.Random;

public class Corredor implements Runnable {
    private Testigo testigo;
    private int id;

    public Corredor(Testigo unTestigo, int unId){
        this.testigo = unTestigo;
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
            this.testigo.agarrarTestigo((this.id));
            System.out.println(Thread.currentThread().getName() +" agarrando testigo");
            correr();
            System.out.println(Thread.currentThread().getName() +" liberando testigo: " +System.currentTimeMillis());
        } finally {
            this.testigo.soltarTestigo((this.id));
        }
    }
}
