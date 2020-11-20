package tp6.centro;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CentroHemoterapia {
    private ReentrantLock salaEspera = new ReentrantLock(true);

    private Condition condCamillas = salaEspera.newCondition();
    private Condition condRevistas = salaEspera.newCondition();

    private int revistasDisponibles;

    private int camillasDisponibles;

    private int sillasDisponibles;

    private int turno;      // Turno para saber a que paciente atender
    private int pacientes;  // Cuenta cuantos pacientes pasaron en el dia

    public CentroHemoterapia(int limiteSillas, int limiteCamillas, int limiteRevistas) {
        this.sillasDisponibles = limiteSillas;

        this.camillasDisponibles = limiteCamillas;

        this.revistasDisponibles = limiteRevistas;

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

            // Mientras no sea mi turno
            // El turno < miTurno es por si sale mas de un donante a la vez, para que no se pase su turno
            while (this.turno < miTurno) {

                // Ver si hay sillas disponibles
                if (this.sillasDisponibles != 0 && !estaSentado) {
                    this.sillasDisponibles--;
                    estaSentado = true;
                }

                // Inicio condicion revistas
                if (!poseeRevista) {
                    if (this.revistasDisponibles == 0) {
                        // Ver la tele y esperar por una revista
                        this.condRevistas.await(3000, TimeUnit.MILLISECONDS);
                    } else {
                        this.revistasDisponibles--;
                        poseeRevista = true;
                    }
                }
                // Fin condicion revistas


                if (estaSentado && poseeRevista)
                    // Posee todos los recursos, nomas espera a que se libere una camilla
                    this.condCamillas.await();
                else
                    // Podria sentarse o buscar una revista, esperar un rato y volver a intentar
                    this.condCamillas.await(1000, TimeUnit.MILLISECONDS);
            }

            // Ocupar camilla
            this.camillasDisponibles--;

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            // Liberar recursos
            if (estaSentado)
                this.sillasDisponibles++;
            if (poseeRevista) {
                this.revistasDisponibles++;
                this.condRevistas.signalAll();
            }

            // Solo para debug
            // System.out.println("Thread entrando a donar: " + Thread.currentThread().getName() + " TURNO: " + this.turno + " PACIENTE: " + miTurno);

            salaEspera.unlock();
        }

    }

    public void salir() {
        this.salaEspera.lock();
        this.camillasDisponibles++;
        this.turno++;
        // Solo para debug
        // System.out.println(Thread.currentThread().getName() + " saliendo" + " TURNO: " + this.turno);
        this.condCamillas.signalAll();
        this.salaEspera.unlock();
    }

}
