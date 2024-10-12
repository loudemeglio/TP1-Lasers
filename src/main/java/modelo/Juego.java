package modelo;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private List<Nivel> niveles;
    private int nivelActual;


    public Juego(int totalNiveles) {
        this.niveles = cargarNiveles(totalNiveles);
        this.nivelActual = 0; // Comienza en el primer nivel
        for (Nivel nivel : niveles) {
            nivel.ejecutarNiveles(nivel);
            // esto no tendria que estar aca me parece pq es lo que inicializa y llama a apuntar
        }
    }

    private List<Nivel> cargarNiveles(int totalNiveles) {
        List<Nivel> niveles = new ArrayList<>();
        for (int i = 1; i <= totalNiveles; i++) {
            Nivel nivel = CargarNiveles.cargarNivelDeArchivo(i);
            if (nivel != null) {
                niveles.add(nivel);
            } else {
                return null;
            }
        }
        return niveles;
    }

    public Nivel getNivelActual() {
        return niveles.get(nivelActual);
    }



    public void setNivelActual(int nuevoNivel) {
        if (nuevoNivel >= 0 && nuevoNivel < niveles.size()) {
            nivelActual = nuevoNivel;
        }
    }

    // total de niveles
    public int getTotalNiveles() {
        return niveles.size();
    }

    public void seleccionarNivel(int n) {
        return;
    }

    public boolean moverBloque(Coordenada origen, Coordenada destino) {
        return niveles.get(nivelActual).getGrilla().moverBloque(origen, destino);
    }

}
