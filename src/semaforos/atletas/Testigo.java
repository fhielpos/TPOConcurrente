package semaforos.atletas;

import java.util.concurrent.Semaphore;

public class Testigo {
    private Semaphore[] testigo;

    public Testigo(int cantCorredores){
        this.testigo = new Semaphore[cantCorredores];
        for (int i=0;i<cantCorredores;i++){
            this.testigo[i] = new Semaphore(0);
        }
    }

    public void agarrarTestigo(int num){
        try {
            this.testigo[num-1].acquire();
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public void iniciarCarrera(){
        this.testigo[0].release();
    }

    public void soltarTestigo(int num){
        if(num != this.testigo.length)
            this.testigo[num].release();
    }
}
