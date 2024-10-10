package modelo;

public class Laser {
    private double columna;
    private double fila;
    private Direccion direccion;
    private double paso;     // Tamaño del paso para avanzar el láser (pequeño, ej. 0.1)


    public Laser(double columna, double fila, Direccion direccion, double paso) {
        this.columna = columna;
        this.fila = fila;
        this.direccion = direccion;
        this.paso = paso;  // Pequeño paso para movimiento continuo
    }

    public double getColumna() {
        return columna;
    }

    public double getFila() {
        return fila;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public boolean estaFueraDeGrilla(Grilla grilla) {
        return columna < 0 || fila < 0 || columna >= grilla.getColumnas() || fila >= grilla.getFilas();
    }

    // SE MUEVE DEPENDIENDO LA DIRECCION QUE TOMA EL LASER
    public void mover() {
        System.out.println("LASER nueva direccion: "+ this.direccion);
        System.out.println("Posición inicial del láser: (" + this.getColumna() + ", " + this.getFila() + ")");
        switch (this.direccion) {
            case SE:
                this.columna += this.paso;
                this.fila += this.paso;
                break;
            case SW:
                this.columna -= this.paso;
                this.fila += this.paso;
                break;
            case NE:
                this.columna += this.paso;
                this.fila -= this.paso;
                break;
            case NW:
                this.columna -= this.paso;
                this.fila -= this.paso;
                break;
            default:
                throw new IllegalArgumentException("Dirección desconocida");
        }
    }

    // Verificar colisión o interacción con una celda/bloque E INTERACTUA
    public boolean verificarColision(Grilla grilla) {
        int columnaEntera = (int) Math.floor(this.columna);
        int filaEntera = (int) Math.floor(this.fila);

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
        return String.format("Posición del láser: (%.1f, %.1f)", columna, fila);
    }
}
