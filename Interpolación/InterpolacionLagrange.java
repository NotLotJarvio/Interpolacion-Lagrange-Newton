package interpolacion;

public class InterpolacionLagrange {

    private static void validarDatos(double[] x, double[] y) {
        if (x == null || y == null || x.length == 0 || y.length == 0) {
            throw new IllegalArgumentException("Los arreglos no pueden ser nulos ni estar vacios.");
        }
        if (x.length != y.length) {
            throw new IllegalArgumentException("Los arreglos x y y deben tener el mismo tamano.");
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                if (x[i] == x[j]) {
                    throw new IllegalArgumentException("Nodos de x repetidos: " + x[i]);
                }
            }
        }
    }

    public static double interpolacionLagrange(double[] x, double[] y, double xEval) {
        validarDatos(x, y);
        double resultado = 0.0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            double termino = y[i];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    // TODO: Reemplazar la siguiente linea con la formula de la productoria L_i(x)
                    termino *= (xEval - x[j]) / (x[i] - x[j]); // <--- LINEA CORREGIDA Y COMPLETADA
                }
            }
            resultado += termino;
        }
        return resultado;
    }
}