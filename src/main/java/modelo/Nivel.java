package modelo;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private Grilla grilla;
    private List<Laser> lasers;
    private List<Objetivo> objetivos;
    private int numeroNivel;

    public Nivel(int numeroNivel, int filas, int columnas) {
        this.grilla = new Grilla(filas, columnas);
        this.numeroNivel = numeroNivel;
        this.lasers = new ArrayList<>();
        this.objetivos = new ArrayList<>();
    }

    public void setGrilla(Grilla grilla) {
        this.grilla = grilla;
    }

    public Grilla getGrilla() {
        return grilla;
    }

    public void agregarLaser(Laser emisor) {
        lasers.add(emisor);
    }

    public void agregarObjetivo(Objetivo objetivo) {
        objetivos.add(objetivo);
    }

    public List<Laser> getLasers() {
        return lasers;
    }

    public List<Objetivo> getObjetivos() {
        return objetivos;
    }

    public int getNumeroNivel() {
        return numeroNivel;
    }
}
