package modelo;

public class Coordenada {
    private double columna;
    private double fila;

    public Coordenada(double columna, double fila) {
        this.columna = columna;
        this.fila = fila;
    }

    public double getColumna() {
        return columna;
    }

    public double getFila() {
        return fila;
    }
    public int getColumnaEntera() {
        return (int) Math.floor(this.columna);
    }

    public int getFilaEntera() {
        return (int) Math.floor(this.fila);
    }

    public void setColumna(double columna) {
        this.columna = columna;
    }

    public void setFila(double fila) {
        this.fila = fila;
    }

    @Override
    public String toString() {
        return "Coordenada{" + "columna=" + columna + ", fila=" + fila + '}';
    }

}




