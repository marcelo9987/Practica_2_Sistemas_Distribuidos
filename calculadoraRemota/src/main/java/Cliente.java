import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.EnumOpciones;

import static java.lang.Float.NaN;
import static modelo.EnumOpciones.*;

public class Cliente {
    private static final String IP = "localhost"; // Puedes cambiar a localhost
    private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor


    private static float[] _obtenerNumeros() {
        ArrayList<Float> numeros = new ArrayList<Float>();
        Scanner sc = new Scanner(System.in);

        int numerosEntrada = 0;
        do {
            System.out.println("¿Cuantos números deseas introducir?  ");
            numerosEntrada = Integer.parseInt(sc.nextLine());
        } while (numerosEntrada <= 0);


        for (int i = 0; i < numerosEntrada; ++i) {

            float numero = 0;

            System.out.println("Ingresa un número: ");
            try {
                numero = Float.parseFloat(sc.nextLine());
            } catch (NumberFormatException e) {
                numero = 0;
            }

            numeros.add(numero);
        }
        float datos[] = new float[numerosEntrada];

        int numeroActual = 0;
        for (Float numero : numeros.stream().toList()) {
            datos[numeroActual] = numero;
            ++numeroActual;
        }

        return datos;

    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        IComun interfaz = (IComun) registry.lookup("superCalculadora"); //Buscar en el registro...

        Scanner sc = new Scanner(System.in);
        float numero1 = NaN;
        float numero2 = NaN;
        float resultado = 0;
        String menu = """
                [SALIR]
                [SUMA]
                [SUMA_N]
                [RESTA]
                [MULTIPLICACION]
                [DIVISION]
                [RAIZ]
                Elige:
                """;
        do {
            System.out.println(menu);

            EnumOpciones opcion;

            try {
                opcion = EnumOpciones.valueOf(sc.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Opción inválida");
                continue;
            }


            if (opcion != SALIR && opcion != SUMA_N) {
                System.out.println("Ingresa el número 1: ");
                try {
                    numero1 = Float.parseFloat(sc.nextLine());
                } catch (NumberFormatException e) {
                    numero1 = 0;
                }


                if (opcion != SUMA_N && opcion != RAIZ) {
                    System.out.println("Ingresa el número 2: ");
                    try {
                        numero2 = Float.parseFloat(sc.nextLine());
                    } catch (NumberFormatException e) {
                        numero2 = 0;
                    }
                }
            }

            switch (opcion) {
                case SUMA:
                    resultado = interfaz.sumar(numero1, numero2);
                    break;
                case SUMA_N:
                    resultado = interfaz.sumarN(_obtenerNumeros());
                    break;
                case RESTA:
                    resultado = interfaz.restar(numero1, numero2);
                    break;
                case MULTIPLICACION:
                    resultado = interfaz.multiplicar(numero1, numero2);
                    break;
                case DIVISION:
                    resultado = interfaz.dividir(numero1, numero2);
                    break;
                case RAIZ:
                    resultado = interfaz.raiz(numero1);
                    break;
                case SALIR:
                    System.exit(0);
            }

            System.out.println("Resultado => " + resultado);
            System.out.println("Presiona ENTER para continuar");
            sc.nextLine();

        } while (true);
    }
}
