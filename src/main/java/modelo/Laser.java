package modelo;

import java.util.List;

public class Laser {
    private int columna;
    private int fila;
    private int colInicial;
    private int filInicial;
    private Direccion direccion;
    private Direccion direccionOriginal;
    private boolean activo = true;
    private List<LaserTrayecto> trayectosLaser;


    public Laser(int columna, int fila, Direccion direccion) {
        this.colInicial = columna;
        this.filInicial = fila;
        this.columna = columna;
        this.fila = fila;
        this.direccion = direccion;
        this.direccionOriginal = direccion;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getColInicial() { return colInicial;}

    public int getFilInicial() { return filInicial;}

    public boolean estaFueraDeGrilla(Grilla grilla) {
        return columna < 0 || fila < 0 || columna >= grilla.getColumnas() || fila >= grilla.getFilas();
    }
    public List<LaserTrayecto> getTrayectoriaLaser() {
        return trayectosLaser;
    }

    // SE MUEVE DEPENDIENDO LA DIRECCION QUE TOMA EL LASER
    public void mover() {
        System.out.println("LASER nueva direccion: "+ this.direccion);
        System.out.println("Posición inicial del láser: (" + this.getColumna() + ", " + this.getFila() + ")");
        switch (this.direccion) {
            case SE:
                this.columna += 1;
                this.fila += 1;
                break;
            case SW:
                this.columna -= 1;
                this.fila += 1;
                break;
            case NE:
                this.columna += 1;
                this.fila -= 1;
                break;
            case NW:
                this.columna -= 1;
                this.fila -= 1;
                break;
            default:
                throw new IllegalArgumentException("Dirección desconocida");
        }
    }

    // Verificar colisión o interacción con una celda/bloque E INTERACTUA
    public boolean verificarColision(Grilla grilla) {
        int columnaEntera = this.columna;
        int filaEntera = this.fila;
        // Si el láser está dentro de la grilla, verificar la celda
        if (!estaFueraDeGrilla(grilla)) {
            Celda celda = grilla.getCelda(filaEntera, columnaEntera);
            Bloque bloque = celda.getTipoBloque();
            if (bloque != null && !(bloque instanceof Piso) ) {
                // Interactuar con el bloque (reflejar, absorber, etc.)
                bloque.interactuarConLaser(this);
                System.out.println("El láser ha interactuado con un bloque en (" + columnaEntera + "," + filaEntera + "), DE TIPO : "
                + bloque);
                return true;
            }
        }
        return false;
    }


    public String obtenerPosicion() {
        return String.format("Posición del láser: (%d, %d)", columna, fila);
    }

    public boolean estaActivo() {
        return activo;
    }

    public void detener() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }

    public void moverColumnaPositiva() {
        this.columna = (int) (this.columna + 1);  // Aumentar en 1 entero
    }

    public void moverColumnaNegativa() {
        this.columna = (int) (this.columna - 1);  // Disminuir en 1 entero
    }

    public void moverFilaPositiva() {
        this.fila = (int) (this.fila + 1);  // Aumentar en 1 entero
    }

    public void moverFilaNegativa() {
        this.fila = (int) (this.fila - 1);  // Disminuir en 1 entero
    }


    public LadoImpacto verificarDireccionLaser(Coordenada coord) {
        int fila = coord.getFila();
        int columna = coord.getColumna();

        // Si la fila es par e impar la columna, el láser viene de los costados (izquierda o derecha)
        //TENDRIAMOS QUE HACER TAMBIEN CLASE CON ARRIBA, ABAJO, IZQ DERECHA?!?!
        if (fila % 2 == 0 && columna % 2 != 0) {
            return LadoImpacto.SUPERIOR_INFERIOR;
        }
        // Si la fila es impar y la columna es par, el láser viene de arriba o abajo
        else if (fila % 2 != 0 && columna % 2 == 0) {
            return LadoImpacto.LATERAL;
        }
        return null;
    }

    public Direccion getDirOriginal() {
        return direccionOriginal;
    }

    public void setColumna(int colInicial) {
        this.columna = colInicial;
    }

    public void setFila(int filInicial) {
        this.fila = filInicial;
    }
}
