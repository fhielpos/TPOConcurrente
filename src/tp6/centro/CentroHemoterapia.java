package tp6.centro;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CentroHemoterapia {
    private ReentrantLock salaEspera = new ReentrantLock(true);

    private Condition camillas = salaEspera.newCondition();
    private Condition revistas = salaEspera.newCondition();

    private int cantRevistas;
    private int revistasDisponibles;

    private int cantCamillas;
    private int camillasDisponibles;

    private int cantSillas;
    private int sillasDisponibles;

    private int turno;
    private int pacientes = 0;

    public CentroHemoterapia(int sillas, int camillas, int revistas) {
        this.cantCamillas = sillas;
        this.sillasDisponibles = this.cantSillas;

        this.cantCamillas = camillas;
        this.camillasDisponibles = this.cantCamillas;

        this.cantRevistas = revistas;
        this.revistasDisponibles = this.cantRevistas;

        this.turno = this.camillasDisponibles;
    }

    public void entrarSala() {
        boolean poseeRevista = false;
        boolean estaSentado = false;
        int numeroTurno;

        try {
            salaEspera.lock();
            this.pacientes++;
            numeroTurno = this.pacientes;

            // Mientras no sea mi turno o no haya camillas
            while (this.turno < numeroTurno || camillasDisponibles == 0) {

                // Ver si hay sillas disponibles
                if (this.sillasDisponibles != 0) {
                    this.sillasDisponibles--;
                    estaSentado = true;
                }

                // Inicio condicion revistas
                if (!poseeRevista) {
                    while (this.revistasDisponibles == 0) {
                        // Ver la tele y esperar por una revista
                        this.revistas.await(1000, TimeUnit.MILLISECONDS);
                    }
                    this.revistasDisponibles--;
                    poseeRevista = true;
                }

                // Fin condicion revistas
                if (estaSentado && poseeRevista)
                    // Posee todos los recursos, nomas espera a que se libere una camilla
                    this.camillas.await();
                else
                    // Podria sentarse o buscar una revista, esperar un rato y volver a intentar
                    this.camillas.await(1000, TimeUnit.MILLISECONDS);
            }

            // Hay camillas disponibles, liberar recursos
            if (estaSentado)
                this.sillasDisponibles++;
            if (poseeRevista) {
                this.revistasDisponibles++;
                this.revistas.signalAll();
            }
            System.out.println("Thread entrando a donar: " + Thread.currentThread().getName() + " TURNO: " + this.turno + " PACIENTE: " + numeroTurno);
            this.camillasDisponibles--;

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            salaEspera.unlock();
        }

    }

    public void salir() {
        this.salaEspera.lock();
        this.camillasDisponibles++;
        this.turno++;
        System.out.println(Thread.currentThread().getName() + " saliendo" + " TURNO: " + this.turno);
        this.camillas.signal();
        this.salaEspera.unlock();
    }

}
