import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import static java.lang.Float.NaN;

public class Cliente {
    private static final String IP = "localhost"; // Puedes cambiar a localhost
    private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        IComun interfaz = (IComun) registry.lookup("superCalculadora"); //Buscar en el registro...

        Scanner sc = new Scanner(System.in);
        int eleccion;
        float numero1 = NaN;
        float numero2 = NaN;
        float resultado = 0;
        String menu = "\n\n------------------\n\n[-1] => Salir\n[0] => Sumar\n[1] => Restar\n[2] => Multiplicar\n[3] => Dividir\n[4] => Raíz\nElige: ";
        do {
            System.out.println(menu);

            try {
                eleccion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                eleccion = -1;
            }

            if (eleccion == -1) {
                continue;
            }

            System.out.println("Ingresa el número 1: ");
            try {
                numero1 = Float.parseFloat(sc.nextLine());
            } catch (NumberFormatException e) {
                numero1 = 0;
            }

            if(eleccion!=4)
            {
                System.out.println("Ingresa el número 2: ");
                try {
                    numero2 = Float.parseFloat(sc.nextLine());
                } catch (NumberFormatException e) {
                    numero2 = 0;
                }
            }

            switch (eleccion) {
                case 0:
                    resultado = interfaz.sumar(numero1, numero2);
                    break;
                case 1:
                    resultado = interfaz.restar(numero1, numero2);
                    break;
                case 2:
                    resultado = interfaz.multiplicar(numero1, numero2);
                    break;
                case 3:
                    resultado = interfaz.dividir(numero1, numero2);
                    break;
                case 4:
                    resultado = interfaz.raiz(numero1);
                    break;
            }

            System.out.println("Resultado => " + resultado);
            System.out.println("Presiona ENTER para continuar");
            sc.nextLine();

        } while (eleccion != -1);
    }
}
