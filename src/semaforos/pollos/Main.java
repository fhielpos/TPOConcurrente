package semaforos.pollos;

public class Main {
    public static void main(String[] args) {
        try {
            Cafeteria cafeteria = new Cafeteria(1);
            Thread cocinero = new Thread(new Mozo(cafeteria), "Cocinero");
            cocinero.start();

            int cantEmpleados = 10;
            Thread empleados[] = new Thread[cantEmpleados];

            for(int i=0;i<cantEmpleados;i++){
                empleados[i] = new Thread(new Empleado(cafeteria), "Empleado " +i);
                empleados[i].start();
            }




        } catch (Exception ex) {
            ex.printStackTrace();
            // Vale la pena usar logger aca?
            // Logger.getLogger(Cafeteria.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
