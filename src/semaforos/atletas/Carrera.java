package semaforos.atletas;

import java.util.concurrent.Semaphore;

public class Carrera {

    public static void main(String[] args) {
        int participantes=4;
        //Semaphore testigo = new Semaphore(0);
        Testigo testigo = new Testigo(participantes);
        Corredor[] corredores = new Corredor[participantes];
        Thread[] hilosCorredores = new Thread[participantes];

        for(int i =0;i<participantes;i++){
            corredores[i] = new Corredor(testigo, (i+1));
            hilosCorredores[i] = new Thread(corredores[i], "Corredor: " +(i+1));
            hilosCorredores[i].start();
        }

        testigo.iniciarCarrera();
    }

}
