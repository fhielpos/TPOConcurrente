package tp6.museo;

import java.util.Random;

public class Sensor implements Runnable {
    private int tUmbral;
    private Sala museo;

    public Sensor(Sala unMuseo, int umbral) {
        this.museo = unMuseo;
        this.tUmbral = umbral;
    }

    @Override
    public void run() {
        while (true) {
            this.museo.setTemperatura(new Random().nextInt(20) + 25);
            if (this.museo.getTemperatura() >= tUmbral) {
                System.out.println("SENSOR: Temperatura maxima, reduciendo");
                museo.restringirCapacidad();
            } else {
                System.out.println("SENSOR: Temperatura baja, aumentando");
                museo.restaurarCapacidad();
            }
            try {
                System.out.println("CAPACIDAD: " +museo.getCapacidad());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
