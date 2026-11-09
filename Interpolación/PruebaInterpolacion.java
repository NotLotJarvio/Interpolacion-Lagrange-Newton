package interpolacion;

import java.util.Arrays;

public class PruebaInterpolacion {

    public static double evaluarNewtonConMatriz(double[] x, double[] y, double xEval) {
        int n = x.length;
        double[][] tabla = new double[n][n];

        for (int i = 0; i < n; i++) {
            tabla[i][0] = y[i];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                tabla[i][j] = (tabla[i + 1][j - 1] - tabla[i][j - 1]) / (x[i + j] - x[i]);
            }
        }

        System.out.println("=== MATRIZ DE DIFERENCIAS DIVIDIDAS DE NEWTON ===");
        for (int i = 0; i < n; i++) {
            System.out.printf("x[%d] = %5.2f | ", i, x[i]);
            for (int j = 0; j < n - i; j++) {
                System.out.printf("%10.4f ", tabla[i][j]);
            }
            System.out.println();
        }

        double resultado = tabla[0][0];
        double producto = 1.0;
        for (int i = 1; i < n; i++) {
            producto *= (xEval - x[i - 1]);
            resultado += tabla[0][i] * producto;
        }
        return resultado;
    }

    public static void main(String[] args) {
        double[] x = {0.0, 1.0, 2.0, 4.0};
        double[] y = {1.0, 2.0, 9.0, 65.0};
        double xEval = 3.0;

        System.out.println("Nodos x: " + Arrays.toString(x));
        System.out.println("Nodos y: " + Arrays.toString(y));
        System.out.println("Punto a evaluar (xEval): " + xEval + "\n");

        double resultadoNewton = evaluarNewtonConMatriz(x, y, xEval);
        double resultadoLagrange = InterpolacionLagrange.interpolacionLagrange(x, y, xEval);

        System.out.println("\n=== RESULTADOS DE LA INTERPOLACION ===");
        System.out.printf("Resultado Diferencias Divididas de Newton: %.6f%n", resultadoNewton);
        System.out.printf("Resultado Polinomio de Lagrange:           %.6f%n", resultadoLagrange);
        System.out.printf("Diferencia absoluta entre metodos:         %.6e%n", Math.abs(resultadoNewton - resultadoLagrange));
    }
}