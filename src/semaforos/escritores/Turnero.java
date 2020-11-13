package semaforos.escritores;

import java.util.concurrent.Semaphore;

public class Turnero {
    //private int turno;
    private int maximo;
    private Semaphore[] turno;

    public Turnero(int unMaximo) {
        this.turno = new Semaphore[unMaximo];
        this.maximo = unMaximo;

        for(int i = 0;i<unMaximo;i++){
            this.turno[i] = new Semaphore(0);
        }
    }

    public void incrementar(int num) {
        this.turno[num].release();

    }

    public synchronized int getTurno() {
        // Método sincronizado para evitar que un hilo obtenga el turno mientras este se está incrementando
        return 0;
    }
}
