package tp6.centro;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CentroHemoterapia {
    private ReentrantLock salaEspera = new ReentrantLock(true);

    private Condition condCamillas = salaEspera.newCondition();
    private Condition condRevistas = salaEspera.newCondition();

    private int cantRevistas;
    private int revistasDisponibles;

    private int cantCamillas;
    private int camillasDisponibles;

    private int cantSillas;
    private int sillasDisponibles;

    private int turno;      // Turno para saber a que paciente atender
    private int pacientes;  // Cuenta cuantos pacientes pasaron en el dia

    public CentroHemoterapia(int limiteSillas, int limiteCamillas, int limiteRevistas) {
        this.cantCamillas = limiteSillas;
        this.sillasDisponibles = this.cantSillas;

        this.cantCamillas = limiteCamillas;
        this.camillasDisponibles = this.cantCamillas;

        this.cantRevistas = limiteRevistas;
        this.revistasDisponibles = this.cantRevistas;

        this.turno = this.camillasDisponibles;
        this.pacientes = 0;
    }

    public void entrarSala() {
        boolean poseeRevista = false;
        boolean estaSentado = false;
        int miTurno;

        try {
            salaEspera.lock();

            this.pacientes++;
            miTurno = this.pacientes;

            // Mientras no sea mi turno o no haya camillas
            // El turno < miTurno es por si sale mas de un donante a la vez, para que no se pase su turno
            while (this.turno < miTurno || camillasDisponibles == 0) {

                // Ver si hay sillas disponibles
                if (this.sillasDisponibles != 0) {
                    this.sillasDisponibles--;
                    estaSentado = true;
                }

                // Inicio condicion revistas
                if (!poseeRevista) {
                    while (this.revistasDisponibles == 0) {
                        // Ver la tele y esperar por una revista
                        this.condRevistas.await(1000, TimeUnit.MILLISECONDS);
                    }
                    this.revistasDisponibles--;
                    poseeRevista = true;
                }
                // Fin condicion revistas


                if (estaSentado && poseeRevista)
                    // Posee todos los recursos, nomas espera a que se libere una camilla
                    this.condCamillas.await();
                else
                    // Podria sentarse o buscar una revista, esperar un rato y volver a intentar
                    this.condCamillas.await(1000, TimeUnit.MILLISECONDS);
            }

            // Hay camillas disponibles, liberar recursos
            if (estaSentado)
                this.sillasDisponibles++;
            if (poseeRevista) {
                this.revistasDisponibles++;
                this.condRevistas.signalAll();
            }

            System.out.println("Thread entrando a donar: " + Thread.currentThread().getName() + " TURNO: " + this.turno + " PACIENTE: " + miTurno);
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
        this.condCamillas.signalAll();
        this.salaEspera.unlock();
    }

}
