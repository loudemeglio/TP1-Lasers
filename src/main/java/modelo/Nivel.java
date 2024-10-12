package modelo;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private Grilla grilla;
    private List<Laser> lasers;
    private List<Objetivo> objetivos;
    private int numeroNivel;
    private List<LaserTrayecto> trayectosLaser;
    private boolean hayInteraccion;


    public Nivel(int numeroNivel, int filas, int columnas) {
        this.grilla = new Grilla(columnas, filas);
        this.numeroNivel = numeroNivel;
        this.lasers = new ArrayList<>();
        this.objetivos = new ArrayList<>();
        this.trayectosLaser = new ArrayList<>();
    }

    public List<LaserTrayecto> getTrayectosLaser() {
        return trayectosLaser;
    }

    public void apuntarLaser(Laser laser) {
        Coordenada inicio = new Coordenada(laser.getColumna(), laser.getFila());
        Coordenada posicion = new Coordenada(laser.getColumna(), laser.getFila());
        Coordenada fin = null;
        Boolean posicionEnRango = true;
        Coordenada posInicial = new Coordenada(laser.getColInicial(), laser.getFilInicial());
        System.out.println("Apuntando láser desde: " + inicio.getColumna() + "," + inicio.getFila());

        // Realizar un primer movimiento antes de cualquier interacción
        laser.mover();
        posicion = new Coordenada(laser.getColumna(), laser.getFila());

        while (grilla.estaDentroDeLimites(posicion) && !verificarNivelCompletado()) {
            System.out.println("Posición actual del láser: " + posicion.getColumna() + "," + posicion.getFila());

            Celda celda = grilla.getCelda(posicion.getFila(), posicion.getColumna());
            verificarObjetivoAlcanzado(posicion);

            if (celda != null && celda.tieneBloque()) {
                Bloque bloque = celda.getTipoBloque();
                System.out.println("el bloque en esta celda: " + bloque);
                if (bloque instanceof Piso) {

                    if (!trayectosLaser.isEmpty()) {
                        manejarBloquePiso(laser, posicion);
                    } else if (posicion != posInicial ) {
                        manejarBloquePiso(laser, posicion);
                    } else {
                        LaserTrayecto trayecto = new LaserTrayecto(inicio, inicio);
                        trayectosLaser.add(trayecto);
                    }

                    if (hayInteraccion) {
                        fin = new Coordenada(posicion.getColumna(), posicion.getFila());
                        LaserTrayecto trayecto = new LaserTrayecto(inicio, fin);
                        trayectosLaser.add(trayecto);
                        LaserTrayecto ultimoTrayecto = trayectosLaser.get(trayectosLaser.size() - 1);
                        inicio = ultimoTrayecto.getFin(); // Usar la posición final del último trayecto
                        posicion = new Coordenada(inicio.getColumna(), inicio.getFila());
                    }
                } else {
                    laser.verificarColision(grilla);
                    fin = new Coordenada(posicion.getColumna(), posicion.getFila());
                    LaserTrayecto trayecto = new LaserTrayecto(inicio, fin);
                    trayectosLaser.add(trayecto);
                    LaserTrayecto ultimoTrayecto = trayectosLaser.get(trayectosLaser.size() - 1);
                    inicio = ultimoTrayecto.getFin(); // Usar la posición final del último trayecto
                    posicion = new Coordenada(inicio.getColumna(), inicio.getFila());
                }
            }

            if (!laser.estaActivo()) {
                System.out.println("El laser ha impactado con un bloque opaco");
                break;
            }

            // Mover el láser después de verificar la celda
            laser.mover();
            posicion = new Coordenada(laser.getColumna(), laser.getFila());
            System.out.println("Moviendo a: " + posicion.getColumna() + "," + posicion.getFila());
        }

        fin = new Coordenada(laser.getColumna(), laser.getFila());
        finalizarLaser(fin, inicio, posicion);
    }


    private void verificarObjetivoAlcanzado(Coordenada coord) {
        for (Objetivo objetivo : objetivos) {
            if (objetivo.getFila() == coord.getFila() && objetivo.getColumna() == coord.getColumna() && !objetivo.isAlcanzado()) {
                objetivo.setAlcanzado(true);
                System.out.println("¡Objetivo alcanzado en: " + coord.getColumna() + "," + coord.getFila() + "!");
            }
        }
    }
    private void manejarBloquePiso(Laser laser, Coordenada coord) {
        LadoImpacto ladoImpacto = laser.verificarDireccionLaser(coord);
        if (ladoImpacto == LadoImpacto.LATERAL) {
            System.out.println("Impacta en el LATERAL");
            interactuarBloqueLateral(coord, laser);
        } else if (ladoImpacto == LadoImpacto.SUPERIOR_INFERIOR) {
            System.out.println("Impacta en el SUP-INF");
            interactuarBloqueSuperiorInferior(coord, laser);
        } else {
            laser.mover();
        }

    }

    private void interactuarBloqueLateral(Coordenada coord, Laser laser) {
        Coordenada adyacenteIzq = new Coordenada(coord.getColumna() - 1, coord.getFila());
        Coordenada adyacenteDer = new Coordenada(coord.getColumna() + 1, coord.getFila());
        if (!grilla.estaDentroDeLimites(adyacenteIzq) || !grilla.estaDentroDeLimites(adyacenteDer)) {
            return;
        }
        if (interactuarBloqueAdyacente(adyacenteIzq, laser) || interactuarBloqueAdyacente(adyacenteDer, laser)) {
            return;
        }
    }

    private void interactuarBloqueSuperiorInferior(Coordenada coord, Laser laser) {
        Coordenada adyacenteArriba = new Coordenada(coord.getColumna(), coord.getFila() - 1);
        Coordenada adyacenteAbajo = new Coordenada(coord.getColumna(), coord.getFila() + 1);
        if (!grilla.estaDentroDeLimites(adyacenteAbajo) || !grilla.estaDentroDeLimites(adyacenteArriba)) {
            return;
        }

        if (interactuarBloqueAdyacente(adyacenteArriba, laser) || interactuarBloqueAdyacente(adyacenteAbajo, laser)) {
            return;
        }
    }

    private boolean interactuarBloqueAdyacente(Coordenada adyacente, Laser laser) {
        Celda celdaAdyacente = grilla.getCelda(adyacente.getFila(), adyacente.getColumna());
        if (celdaAdyacente != null && celdaAdyacente.tieneBloque()) {
            Bloque bloqueAdyacente = celdaAdyacente.getTipoBloque();
            System.out.println("Interacción con bloque en: " + adyacente.getColumna() + "," + adyacente.getFila());
            System.out.println("Interactua cn el bloque: " + bloqueAdyacente);
            bloqueAdyacente.interactuarConLaser(laser);
            hayInteraccion = true;
            return true;
        }
        hayInteraccion = false;
        return false;
    }

    private void bloquearInteraccion(Bloque bloque, Coordenada coord, Laser laser) {
        bloque.interactuarConLaser(laser);
        System.out.println("Bloque encontrado en: " + coord.getColumna() + "," + coord.getFila());
    }

    private void finalizarLaser(Coordenada fin, Coordenada inicio, Coordenada coord) {
        if (fin == null) {
            fin = coord; // El láser salió de la grilla sin obstáculos
        }

        System.out.println(fin != null
                ? "El láser terminó en la coordenada: " + fin.getColumna() + "," + fin.getFila()
                : "El láser salió de la grilla sin encontrar bloque."
        );

        // Agrega el trayecto del láser a la lista de trayecto
        LaserTrayecto trayecto = new LaserTrayecto(inicio, fin);
        trayectosLaser.add(trayecto);

        // Verificar si todos los objetivos han sido alcanzados
        if (verificarNivelCompletado()) {
            System.out.println("¡Nivel completado! Todos los objetivos han sido alcanzados.");
        } else {
            System.out.println("Todavía hay objetivos sin alcanzar.");
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

    public boolean verificarNivelCompletado() {
        for (Objetivo objetivo : objetivos) {
            if (!objetivo.isAlcanzado()) {
                return false; // Si hay al menos un objetivo no alcanzado, el nivel no está completado
            }
        }
        return true; // Todos los objetivos han sido alcanzados
    }

    public void ejecutarNiveles(Nivel nivel) {
        System.out.println("\u001B[31mEjecutando nivel: " + numeroNivel + "\u001B[0m");
        for (Laser laser : lasers) {
            apuntarLaser(laser); // Apunta el laser para cada emisor
        }
    }
}
