package modelo;

public class Grilla {
    private Celda[][] matriz;
    private int filas;
    private int columnas;
    private int cont = 0;
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
        if (cont == 0) {
            cont = 1;
            return true;
        }
        return coord.getFila() >= 0 && coord.getFila() < filas &&
                coord.getColumna() >= 0 && coord.getColumna() < columnas;
    }

    public boolean moverBloque(Coordenada origen, Coordenada destino) {
        if (esPosicionValida(origen) && esPosicionValida(destino)) {
            Celda celdaOrigen = getCelda(origen.getFila(), origen.getColumna());
            Celda celdaDestino = getCelda(destino.getFila(), destino.getColumna());

            if (celdaOrigen.tieneBloque() && celdaDestino.getTipoBloque() instanceof Piso){
                if (!celdaOrigen.getTipoBloque().esMovil()) {
                    return false;
                }
                Bloque bloquePiso = celdaDestino.getTipoBloque();
                Bloque bloque = celdaOrigen.getTipoBloque();
                celdaOrigen.setTipoBloque(bloquePiso);
                celdaDestino.setTipoBloque(bloque);
                return true;
            }
        }
        return false;
    }

    private boolean esPosicionValida(Coordenada coordenada) {
        int fila = coordenada.getFila();
        int columna = coordenada.getColumna();
        return fila >= 0 && fila < matriz.length && columna >= 0 && columna < matriz[0].length;
    }


}
