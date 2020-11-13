package semaforos.escritores;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        // Turnero
        // Turnero turno = new Turnero(3);
        Semaphore turno = new Semaphore(1);

        // Letras que quiero imprimir
        char[] ids = {'A', 'B', 'C'};

        // Los encargados en escribir
        Escritor[] escritores = new Escritor[3];

        // Los hilos
        Thread[] hilosEscritores = new Thread[3];


        for (int i = 0; i < 3; i++) {
            // Creacion de escritores
            escritores[i] = new Escritor(i + 1, ids[i], turno);
            // Creacion de hilos
            hilosEscritores[i] = new Thread(escritores[i], "Escritor " + (i + 1));
            // Inicializacion de los hilos
            hilosEscritores[i].start();
        }
    }
}
