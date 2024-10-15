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

        // Realizar un primer movimiento antes de cualquier interacción
        laser.mover();
        posicion = new Coordenada(laser.getColumna(), laser.getFila());

        while (grilla.estaDentroDeLimites(posicion) && !verificarNivelCompletado()) {

            Celda celda = grilla.getCelda(posicion.getFila(), posicion.getColumna());
            verificarObjetivoAlcanzado(posicion);

            if (celda != null && celda.tieneBloque()) {
                Bloque bloque = celda.getTipoBloque();
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
                break;
            }

            // Mover el láser después de verificar la celda
            laser.mover();
            posicion = new Coordenada(laser.getColumna(), laser.getFila());
        }

        fin = new Coordenada(laser.getColumna(), laser.getFila());
        finalizarLaser(fin, inicio, posicion);
    }


    private void verificarObjetivoAlcanzado(Coordenada coord) {
        for (Objetivo objetivo : objetivos) {
            if (objetivo.getFila() == coord.getFila() && objetivo.getColumna() == coord.getColumna() && !objetivo.isAlcanzado()) {
                objetivo.setAlcanzado(true);
            }
        }
    }
    private void manejarBloquePiso(Laser laser, Coordenada coord) {
        LadoImpacto ladoImpacto = laser.verificarDireccionLaser(coord);
        if (ladoImpacto == LadoImpacto.LATERAL) {
            interactuarBloqueLateral(coord, laser);
        } else if (ladoImpacto == LadoImpacto.SUPERIOR_INFERIOR) {
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
            bloqueAdyacente.interactuarConLaser(laser);
            hayInteraccion = true;
            return true;
        }
        hayInteraccion = false;
        return false;
    }

    private void bloquearInteraccion(Bloque bloque, Coordenada coord, Laser laser) {
        bloque.interactuarConLaser(laser);
    }

    private void finalizarLaser(Coordenada fin, Coordenada inicio, Coordenada coord) {
        if (fin == null) {
            fin = coord; // El láser salió de la grilla sin obstáculos
        }


        // Agrega el trayecto del láser a la lista de trayecto
        LaserTrayecto trayecto = new LaserTrayecto(inicio, fin);
        trayectosLaser.add(trayecto);

        // Verificar si todos los objetivos han sido alcanzados
        if (verificarNivelCompletado()) {
        } else {
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
        for (Laser laser : lasers) {
            apuntarLaser(laser); // Apunta el laser para cada emisor
        }
    }
}
