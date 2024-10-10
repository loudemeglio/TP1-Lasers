package modelo;

public class Grilla {
    private Celda[][] matriz;
    private int filas;
    private int columnas;

    public Grilla(int columnas, int filas) {
        this.filas = filas;
        this.columnas = columnas;
        matriz = new Celda[filas][columnas];
        // Inicializar todas las celdas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = new Celda(null);
            }
        }
    }

    public void setCelda(int fila, int columna, Bloque bloque) {
        matriz[fila][columna].setTipoBloque(bloque);
    }

    public Celda getCelda(int fila, int columna) {
        return matriz[fila][columna];
    }

    public Celda[][] getMatriz() {
        return matriz;
    }

    public int getFilas() {
        return matriz.length;
    }

    public int getColumnas() {
        return matriz[0].length;
    }

    public boolean estaDentroDeLimites(Coordenada coord) {
        return coord.getFila() >= 0 && coord.getFila() < filas &&
                coord.getColumna() >= 0 && coord.getColumna() < columnas;
    }



}
