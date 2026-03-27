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
        if (numero2 == 0) {
            throw new RemoteException("No se puede dividir entre cero");
        }
        return numero1 / numero2;
    }

    @Override
    public float raiz(float numero) throws RemoteException {
        if (numero < 0) {
            throw new RemoteException("No se puede calcular la raíz de un número negativo");
        }
        return (float) Math.sqrt(numero);
    }

    @Override
    public float sumarN(float... numeros) {
        float resultado = 0;
        for (float numeroEspecifico : numeros) {
            resultado += numeroEspecifico;
        }
        return resultado;
    }
}



