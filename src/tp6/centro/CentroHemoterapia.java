package tp6.centro;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CentroHemoterapia {
    private ReentrantLock salaEspera = new ReentrantLock();
    private ReentrantLock lockCamillas = new ReentrantLock(true);

    private Condition camillas = salaEspera.newCondition();
    private Condition revistas = salaEspera.newCondition();

    private int cantRevistas;
    private int revistasDisponibles;

    private int cantCamillas;
    private int camillasDisponibles;

    private int cantSillas;
    private int sillasDisponibles;

    public CentroHemoterapia(int sillas, int camillas, int revistas){
        this.cantCamillas = sillas;
        this.sillasDisponibles = this.cantSillas;

        this.cantCamillas = camillas;
        this.camillasDisponibles = this.cantCamillas;

        this.cantRevistas = revistas;
        this.revistasDisponibles = this.cantRevistas;
    }

    public void entrarSala(){
        boolean poseeRevista = false;
        boolean estaSentado = false;

        try{
            salaEspera.lock();
            while(camillasDisponibles == 0){
                // Ver si hay sillas disponibles
                if (this.sillasDisponibles != 0) {
                    this.sillasDisponibles--;
                    estaSentado = true;
                }

                // Inicio condicion revistas
                if (!poseeRevista) {
                    while (this.revistasDisponibles == 0) {
                        // Ver la tele y esperar por una revista
                        this.revistas.await();
                    }
                    this.revistasDisponibles--;
                    poseeRevista = true;
                }

                // Fin condicion revistas
            }

            // Hay camas disponibles, liberar recursos
            if(estaSentado)
                this.sillasDisponibles++;
            if(poseeRevista){
                this.revistasDisponibles++;
                this.revistas.notifyAll();
            }

        } catch (InterruptedException ex){
            ex.printStackTrace();
        } finally {
            salaEspera.unlock();
        }
    }

    public void donar(){
        this.lockCamillas.lock();

        this.lockCamillas.unlock();
    }

}
