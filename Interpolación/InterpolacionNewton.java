package Interpolación;

public class InterpolacionNewton {

    /**
     * Calcula la tabla de diferencias divididas para los puntos dados.
     *
     * @param x Arreglo con los valores de x (nodos)
     * @param y Arreglo con los valores de y (f(x))
     * @return Matriz con la tabla de diferencias divididas
     */
    public static double[][] calcularDiferenciasDivididas(double[] x, double[] y) {
        int n = x.length;
        double[][] tabla = new double[n][n];

        // La primera columna contiene los valores de y (diferencias de orden 0)
        for (int i = 0; i < n; i++) {
            tabla[i][0] = y[i];
        }

        // Cálculo de las diferencias divididas de orden superior
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                tabla[i][j] = (tabla[i + 1][j - 1] - tabla[i][j - 1]) / (x[i + j] - x[i]);
            }
        }
        return tabla;
    }




    /**
     * Evalúa el polinomio interpolador de Newton en un punto xEval dado.
     *
     * @param x     Arreglo con los nodos de x
     * @param tabla Matriz de diferencias divididas
     * @param xEval El punto x donde queremos interpolar
     * @return El valor interpolado y = P(xEval)
     */
    public static double evaluarPolinomio(double[] x, double[][] tabla, double xEval) {
        int n = x.length;
        double resultado = tabla[0][0];
        double productoTerminos = 1.0;

        // Sumamos a1*(x - x0) + a2*(x - x0)(x - x1) + ...
        for (int i = 1; i < n; i++) {
            productoTerminos *= (xEval - x[i - 1]);
            resultado += tabla[0][i] * productoTerminos;
        }

        return resultado;
    }





    /**
     * Imprime en consola la tabla de diferencias divididas de forma estructurada.
     */
    public static void imprimirTabla(double[] x, double[][] tabla) {
        int n = x.length;
        System.out.println("\n--- TABLA DE DIFERENCIAS DIVIDIDAS ---");
        System.out.print("x_i\tf[x_i]\t");
        for (int i = 1; i < n; i++) {
            System.out.print("Orden " + i + "\t");
        }
        System.out.println("\n-----------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.printf("%.2f\t", x[i]);
            for (int j = 0; j < n - i; j++) {
                System.out.printf("%.4f\t", tabla[i][j]);
            }
            System.out.println();
        }
    }
}