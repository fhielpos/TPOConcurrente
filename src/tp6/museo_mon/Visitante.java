package tp6.museo_mon;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Admin
 */
public class Visitante implements Runnable {
    private boolean jubilado;
    private Sala museo;

    public Visitante(boolean esJubilado, Sala unaSala) {
        this.jubilado = esJubilado;
        this.museo = unaSala;
    }

    public void run() {
        while (true) {
            try {
                if (this.jubilado)
                    museo.entrarSalaJubilado();
                else
                    museo.entrarSala();
            } catch (InterruptedException ex) {
                Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                System.out.println(Thread.currentThread().getName() + " esta en la sala, durmiendo...");
                Thread.sleep(new Random().nextInt(1500) + 5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " saliendo de la sala");
            museo.salirSala();
        }
    }
}