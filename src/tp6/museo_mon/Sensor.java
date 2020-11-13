package tp6.museo_mon;

import java.util.Random;

public class Sensor implements Runnable {
    private int tUmbral;
    private Sala museo;
    private boolean reducido = false;

    public Sensor(Sala unMuseo, int umbral) {
        this.museo = unMuseo;
        this.tUmbral = umbral;
    }

    @Override
    public void run() {
        while (true) {
            // Set temperatura random
            this.museo.setTemperatura(new Random().nextInt(20) + 25);

            if (this.museo.getTemperatura() >= tUmbral && !this.reducido) {
                // Reducir cantidad
                System.out.println("SENSOR: Temperatura maxima, reduciendo");
                museo.restringirCapacidad();
                this.reducido = true;
            } else if (this.museo.getTemperatura() < tUmbral && this.reducido) {
                // Extender cantidad
                System.out.println("SENSOR: Temperatura baja, aumentando");
                museo.restaurarCapacidad();
                this.reducido = false;
            }

            try {
                // Dormir antes de volver a verificar
                System.out.println("CAPACIDAD: " + museo.getCapacidad());
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
