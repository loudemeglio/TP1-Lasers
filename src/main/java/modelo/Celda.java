package modelo;

public class Celda {
    private Bloque tipoBloque;

    public Celda(Bloque tipoBloque) {
        this.tipoBloque = tipoBloque;
    }

    public Bloque getTipoBloque() {
        return tipoBloque;
    }

    public void setTipoBloque(Bloque tipoBloque) {
        this.tipoBloque = tipoBloque;
    }

    public boolean tieneBloque() {
        return tipoBloque != null;
    }
}
