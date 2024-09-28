package modelo;

public class Laser {
    private int columna;
    private int fila;
    private Direccion direccion;

    public Laser(int columna, int fila, Direccion direccion) {
        this.columna = columna;
        this.fila = fila;
        this.direccion = direccion;
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


}
