package modelo;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private Grilla grilla;
    private List<Laser> lasers;
    private List<Objetivo> objetivos;
    private int numeroNivel;

    public Nivel(int numeroNivel, int filas, int columnas) {
        this.grilla = new Grilla(columnas, filas);
        this.numeroNivel = numeroNivel;
        this.lasers = new ArrayList<>();
        this.objetivos = new ArrayList<>();
    }

    public void apuntarLaser(Laser laser) {
        Coordenada inicio = new Coordenada( laser.getColumna(), laser.getFila());
        Coordenada posicion = new Coordenada(laser.getColumna(), laser.getFila());
        System.out.println("Apuntando láser desde: " + inicio.getColumna() + "," + inicio.getFila());

        // MIENTRAS ESTE EN EL RANGO DE LA GRILLA LLAMA A LASER.MOVER, DEBERIA ACA TAMBIEN CHQUEAR LO DE OBJETIVOS
        while (grilla.estaDentroDeLimites(posicion)) {
            System.out.println("Posición actual del láser: " + posicion.getColumna() + "," + posicion.getFila());

            // Mover el láser
            laser.mover();
              posicion.setColumna(laser.getColumna());
              posicion.setFila(laser.getFila());
            if (laser.verificarColision(grilla)) {
                System.out.println("El láser ha interactuado con un bloque !!!!! )");
                Bloque bloque = grilla.getCelda((int) posicion.getColumna(), (int) posicion.getFila()).getTipoBloque();
                System.out.println("EL BLOQUE ES: " + bloque);
            }
//            posicion.setColumna(laser.getColumna());
//            posicion.setFila(laser.getFila());
            // laser.mover();

            // Actualizar la posición

            System.out.println("Láser se ha movido a: " + posicion.getColumna() + "," + posicion.getFila());
        }
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
