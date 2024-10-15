package modelo;

public class Celda {
    private Bloque tipoBloque;
    private Bloque bloqueOriginal;

    public Celda(Bloque tipoBloque) {
        this.tipoBloque = tipoBloque;
        this.bloqueOriginal = tipoBloque; // Almacenar el bloque original

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

    public Bloque getBloqueOriginal() {
        return bloqueOriginal; // Método para obtener el bloque original
    }

    public void setBloqueOriginal(Bloque bloqueOriginal) {
        this.bloqueOriginal = bloqueOriginal; // Método para establecer el bloque original
    }
}
