package tp6.museo;

public class Main {
    public static void main(String[] args) {
        int cantVisitantes = 100;
        int cantJubilados = 45;

        Thread[] visitantes = new Thread[cantVisitantes];
        Thread[] jubilados = new Thread[cantJubilados];

        Sala museo = new Sala(50);

        for (int i=0;i<cantJubilados;i++){
            jubilados[i] = new Thread(new Visitante(true, museo), "JUBILADO " +i);
            jubilados[i].start();
        }

        for (int i=0;i<cantVisitantes;i++){
            visitantes[i] = new Thread(new Visitante(false, museo), "VISITANTE " +i);
            visitantes[i].start();
        }
    }
}
