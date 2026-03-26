import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculadoraTest {


    private IComun stub;
    private Registry registry;

    @BeforeAll
    void setupServer() throws RemoteException {
        ImplementacionRemota impl = new ImplementacionRemota();

        stub = (IComun) UnicastRemoteObject.exportObject(impl, 0);
        int PUERTO = 1101;
        registry = LocateRegistry.createRegistry(PUERTO);
        registry.rebind("superCalculadoraTest", stub);
    }


    @Test
    void testSumar() throws Exception {
        float res = stub.sumar(2, 3);
        assertEquals(5.0f, res, 0.001);
    }

    @Test
    void testRestar() throws Exception {

        float res = stub.restar(5, 3);
        assertEquals(2.0f, res, 0.001);
    }

    @Test
    void testMultiplicar() throws Exception {
        float res = stub.multiplicar(2, 3);
        assertEquals(6.0f, res, 0.001);
    }

    @Test
    void testDividir() throws Exception {
        float res = stub.dividir(10, 2);
        assertEquals(5.0f, res, 0.001);
    }

    @Test
    void testRaiz() throws Exception {
        float res = stub.raiz(9);
        assertEquals(3.0f, res, 0.001);
    }
}