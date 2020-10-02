package semaforos.escritores;

public class Turnero {
    private int turno;
    private int maximo;

    public Turnero(int unMaximo) {
        this.turno = 1;
        this.maximo = unMaximo;
    }

    public synchronized void incrementar() {
        // Método sincronizado para evitar que se interrumpa el incremento
        if (this.turno == this.maximo)
            // Si termina el ciclo de turnos, reinicio
            this.turno = 1;
        else
            // Si el ciclo no terminó, aumento el turno
            this.turno++;
    }

    public synchronized int getTurno() {
        // Método sincronizado para evitar que un hilo obtenga el turno mientras este se está incrementando
        return this.turno;
    }
}
