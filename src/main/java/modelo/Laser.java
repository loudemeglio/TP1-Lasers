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

    public boolean estaFueraDeGrilla(Grilla grilla) {
        return columna < 0 || fila < 0 || columna >= grilla.getColumnas() || fila >= grilla.getFilas();
    }

}
