import java.rmi.RemoteException;

public class ImplementacionRemota implements IComun {

    @Override
    public float sumar(float numero1, float numero2) throws RemoteException {
        return numero1 + numero2;
    }

    @Override
    public float restar(float numero1, float numero2) throws RemoteException {
        return numero1 - numero2;
    }

    @Override
    public float multiplicar(float numero1, float numero2) throws RemoteException {
        return numero1 * numero2;
    }

    @Override
    public float dividir(float numero1, float numero2) throws RemoteException {
        return numero1 / numero2;
    }

    @Override
    public float raiz(float numero) throws RemoteException {
        return (float) Math.sqrt(numero);
    }

}
