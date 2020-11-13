package Ejercicio_13;


public class Main {
    public static void main(String[] args){
        Confiteria confiteria = new Confiteria();
        for(int i = 1; i < 7; i++) new Thread(new Empleado(confiteria), "Empleado " + i).start();
    }
}
