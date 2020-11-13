package semaforos.pollos;

public class Empleado implements Runnable{
    public Cafeteria cafeteria;

    public Empleado(Cafeteria unaCafeteria){
        this.cafeteria = unaCafeteria;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1500);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        if(this.cafeteria.entrar()){
            this.cafeteria.solicitarAtencion();
            this.cafeteria.comer();
            this.cafeteria.salir();
        }
        else
            System.out.println(Thread.currentThread().getName() +": Cafeteria llena");
    }
}
