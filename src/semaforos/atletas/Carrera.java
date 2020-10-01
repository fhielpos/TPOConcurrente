package semaforos.atletas;

import java.util.concurrent.Semaphore;

public class Carrera {

    public static void main(String[] args) {
        Semaphore testigo = new Semaphore(1, true);

        int participantes=4;
        Corredor[] corredores = new Corredor[participantes];
        Thread[] hilosCorredores = new Thread[participantes];

        for(int i =0;i<participantes;i++){
            corredores[i] = new Corredor(testigo);
            hilosCorredores[i] = new Thread(corredores[i]);
            hilosCorredores[i].start();
        }
    }

}
