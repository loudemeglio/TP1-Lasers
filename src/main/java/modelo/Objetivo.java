package modelo;

public class Objetivo {
    private Coordenada coordenada;

    public Objetivo(int columna, int fila) {
        this.coordenada = new Coordenada(columna, fila);
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
}
