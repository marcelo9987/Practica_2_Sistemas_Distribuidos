package modelo;

import java.util.Arrays;
import java.util.Optional;

public enum EnumOpciones
{
    SUMA(0)
        , SUMA_N(1)
        , RESTA(2)
        , MULTIPLICACION(3)
        , DIVISION(4)
        , RAIZ(5)
        , SALIR(6);


    private final int valor;
    EnumOpciones(int i) {
        this.valor = i;
    }


}
