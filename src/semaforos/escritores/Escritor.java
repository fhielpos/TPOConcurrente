package semaforos.escritores;

import java.util.concurrent.Semaphore;

public class Escritor implements Runnable {
    private int id;
    private char escribir;
    private Semaphore turno;

    public Escritor(int identificador, char letra, Semaphore turnero) {
        this.id = identificador;
        this.escribir = letra;
        this.turno = turnero;
    }

    private void escribir(){
        // Escribo mi CHAR la cantidad de veces que valga mi identificador
        for (int j = 0; j < this.id; j++) {
            System.out.print(this.escribir);
        }
    }

    @Override
    public void run() {
        try{
            this.turno.acquire(this.id);
            escribir();
        } catch (InterruptedException ex){
          ex.printStackTrace();
        } finally {
            this.turno.release(this.id+1);
        }
    }
}
