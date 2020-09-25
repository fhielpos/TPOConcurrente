package semaforos.escritores;

public class Escritor implements Runnable {
    private int id;
    private char escribir;
    private Turnero turno;

    public Escritor(int identificador, char letra, Turnero turnero) {
        this.id = identificador;
        this.escribir = letra;
        this.turno = turnero;
    }

    private void escribir(){
        // Escribo mi CHAR la cantidad de veces que valga mi identificador
        for (int j = 0; j < this.id; j++) {
            System.out.print(this.escribir);
        }
        this.turno.incrementar();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            while (this.turno.getTurno() != this.id) {
                // No es mi turno, le doy lugar a otro thread (o no).
                Thread.yield();
            }
            // Es mi turno, escribo
            escribir();
        }
    }
}
