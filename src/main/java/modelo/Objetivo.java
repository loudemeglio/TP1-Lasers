package modelo;

public class Objetivo {
    private Coordenada coordenada;
    private boolean alcanzado;

    public Objetivo(int columna, int fila) {
        this.coordenada = new Coordenada(columna, fila);
        this.alcanzado = false;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public int getColumna() {
        return coordenada.getColumna();
    }

    public int getFila() {
        return coordenada.getFila();
    }

    public boolean isAlcanzado() {
        return alcanzado;
    }

    public void setAlcanzado(boolean alcanzado) {
        this.alcanzado = alcanzado;
    }
}
