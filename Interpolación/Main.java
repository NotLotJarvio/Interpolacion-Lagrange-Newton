package Interpolación;

public class Main {
    public static void main(String[] args) {
        // Puntos de ejemplo del ejercicio en clase: (1, 2), (2, 3), (4, 10)
        double[] x = {1.0, 2.0, 4.0};
        double[] y = {2.0, 3.0, 10.0};

        // Punto intermedio a interpolar
        double xEval = 3.0;

        // 1. Calcular tabla de diferencias divididas llamando a la clase de interpolación
        double[][] tablaDiferencias = InterpolacionNewton.calcularDiferenciasDivididas(x, y);

        // 2. Imprimir la tabla
        InterpolacionNewton.imprimirTabla(x, tablaDiferencias);

        // 3. Mostrar los coeficientes (Diagonal superior)
        System.out.println("\n--- COEFICIENTES DEL POLINOMIO (a_k) ---");
        for (int k = 0; k < x.length; k++) {
            System.out.printf("a_%d = %.4f\n", k, tablaDiferencias[0][k]);
        }

        // 4. Evaluar en x = 3
        double yInterpolado = InterpolacionNewton.evaluarPolinomio(x, tablaDiferencias, xEval);

        System.out.println("\n--- RESULTADO DE LA INTERPOLACIÓN ---");
        System.out.printf("El valor estimado en x = %.2f es P(%.2f) = %.4f\n", xEval, xEval, yInterpolado);
    }
}