import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

         ImplementacionRemota impl = new ImplementacionRemota();

        Remote remote = UnicastRemoteObject.exportObject(impl, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
        System.out.println("Servidor escuchando en el puerto " + PUERTO);
        registry.bind("superCalculadora", remote); // Registrar calculadora
        for (;;)
        {
        }
    }
}
