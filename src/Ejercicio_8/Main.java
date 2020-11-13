package Ejercicio_8;


public class Main {
    public static void main(String[] args) throws Exception{
        int nroAtletas, nroEquipos;
        nroAtletas = 4;
        nroEquipos = 2;
        Atleta[] atletas;
        Carrera carrera = new Carrera(nroAtletas, nroEquipos);
        atletas = carrera.getAtletas();
        for(int i = 0; i < nroAtletas; i++){
            new Thread(atletas[i], "Atleta " + (i + 1)).start();
        }
    }
}
