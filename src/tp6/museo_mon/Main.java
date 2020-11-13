package tp6.museo_mon;

public class Main {
    public static void main(String[] args) {
        int cantVisitantes = 20;
        int cantJubilados = 35;

        // Hilos visitantes
        Thread[] visitantes = new Thread[cantVisitantes];
        Thread[] jubilados = new Thread[cantJubilados];

        // Museo y sensor de temperatura
        Sala museo = new Sala(50, 35);
        Thread sensor = new Thread(new Sensor(museo, 30));
        sensor.start();

        // Creando hilos jubilados
        for (int i = 0; i < cantJubilados; i++) {
            jubilados[i] = new Thread(new Visitante(true, museo), "JUBILADO " + i);
            jubilados[i].start();
        }

        // Creando hilos visitantes
        for (int i = 0; i < cantVisitantes; i++) {
            visitantes[i] = new Thread(new Visitante(false, museo), "VISITANTE " + i);
            visitantes[i].start();
        }

    }
}
