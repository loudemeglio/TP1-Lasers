package modelo;

import java.util.ArrayList;
import java.util.List;

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

    public int getFilasOriginales() {
        return filas/2;
    }
    public int getColOriginales() {
        return columnas/2;
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

    public boolean moverBloque(Nivel n, Coordenada origen, Coordenada destino) {
        List<Bloque> bloquesOrigen= new ArrayList<>();
        List<Bloque> bloquesDestino= new ArrayList<>();

        //if (esPosicionValida(origen) && esPosicionValida(destino)) {
            Celda origen00 = getCelda(2*origen.getFila(), 2*origen.getColumna());
            bloquesOrigen.add(origen00.getTipoBloque());
            Celda destino00 = getCelda(2*destino.getFila(), 2*destino.getColumna());
            bloquesDestino.add(destino00.getTipoBloque());

            Celda origen10 = getCelda(2*origen.getFila(), (2*origen.getColumna()) + 1);
            bloquesOrigen.add(origen10.getTipoBloque());
            Celda destino10 = getCelda(2*destino.getFila(), (2*destino.getColumna()) + 1);
            bloquesDestino.add(destino10.getTipoBloque());

            Celda origen01 = getCelda((2*origen.getFila()) + 1, 2*origen.getColumna());
            bloquesOrigen.add(origen01.getTipoBloque());
            Celda destino01 = getCelda((2*destino.getFila()) + 1, 2*destino.getColumna());
            bloquesDestino.add(destino01.getTipoBloque());

            Celda origen11 = getCelda((2*origen.getFila()) + 1, (2*origen.getColumna()) + 1);
            bloquesOrigen.add(origen11.getTipoBloque());
            Celda destino11 = getCelda((2*destino.getFila()) + 1, (2*destino.getColumna()) + 1);
            bloquesDestino.add(destino11.getTipoBloque());

            for (Bloque b : bloquesOrigen) {
                if (b == null || b instanceof Piso || !b.esMovil()) {
                    return false;
                }
            }
            for (Bloque b : bloquesDestino) {
                if (b == null || !(b instanceof Piso)) {
                    return false;
                }
            }
            //mover
            Bloque bloque00 = origen00.getTipoBloque();
            origen00.setTipoBloque(new Piso());
            destino00.setTipoBloque(bloque00);

            Bloque bloque01 = origen01.getTipoBloque();
            origen01.setTipoBloque(new Piso());
            destino01.setTipoBloque(bloque01);

            Bloque bloque10 = origen10.getTipoBloque();
            origen10.setTipoBloque(new Piso());
            destino10.setTipoBloque(bloque10);

            Bloque bloque11 = origen11.getTipoBloque();
            origen11.setTipoBloque(new Piso());
            destino11.setTipoBloque(bloque11);

            reiniciarLaser(n);
            return true;
        //}
        //return false; // Movimiento fallido
    }


    private void reiniciarLaser(Nivel nivel) {
        for (Laser l : nivel.getLasers()) {
            nivel.getTrayectosLaser().clear();
            if (nivel.getTrayectosLaser().isEmpty()) {
                System.out.println("La trayectoria esta vacia");

            }
            int filInicial = l.getFilInicial();
            int colInicial = l.getColInicial();
            Direccion dirOriginal = l.getDirOriginal();
            l.setColumna(colInicial);
            l.setFila(filInicial);
            l.setDireccion(dirOriginal);
            nivel.apuntarLaser(l);
        }
    }


    private boolean esPosicionValida(Coordenada coordenada) {
        int fila = coordenada.getFila();
        int columna = coordenada.getColumna();
        return fila >= 0 && fila < matriz.length && columna >= 0 && columna < matriz[0].length;
    }


}
