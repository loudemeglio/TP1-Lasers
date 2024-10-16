import modelo.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MoverTest {
    private Grilla grilla;
    @Before
    public void setUp() {
        grilla = new Grilla(5, 5); // Supongamos que el constructor toma filas y columnas
    }

    @org.junit.Test
    public void testInicializacionGrilla() {
        assertNotNull(grilla); // Asegúrate de que la grilla no sea nula
        assertEquals(5, grilla.getFilas()); // Verifica que el número de filas sea correcto
        assertEquals(5, grilla.getColumnas()); // Verifica que el número de columnas sea correcto
    }
    @Test
    public void moverBloque1() {
        // Cargar el nivel
        Juego juego = new Juego(6);
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(1);
        Grilla grilla = nivel.getGrilla();
        Laser laser = nivel.getLasers().get(0); // Obtener el láser desde el nivel

        System.out.println(); // Salto de línea al final de cada fila
        System.out.println("\u001B[31mEjecutando nivel: 1\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println();
        }

        nivel.apuntarLaser(laser);
        String expected = "Posición del láser: (8,0, 9,0)";
        Direccion d = Direccion.NE;
        Direccion act = laser.getDireccion();
        assertEquals(d, act);

        // Mueve los bloques
        /*boolean moved = grilla.moverBloque(nivel, new Coordenada(4, 0), new Coordenada(0, 4));
        moved &= grilla.moverBloque(nivel, new Coordenada(5, 0), new Coordenada(1, 4));
        moved &= grilla.moverBloque(nivel, new Coordenada(4, 1), new Coordenada(0, 5));
        moved &= grilla.moverBloque(nivel, new Coordenada(5, 1), new Coordenada(1, 5));*/

        boolean moved = grilla.moverBloque(nivel, new Coordenada(2, 0), new Coordenada(0, 3));


        // Asegúrate de que el movimiento fue exitoso
        assertTrue(moved);
        System.out.println(); // Salto de línea al final de cada fila
        System.out.println("\u001B[31mEjecutando nivel: 1\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println();
        }


        if (nivel.verificarNivelCompletado()) {
            System.out.println("COMPLETADO");
        } else {
            System.out.println("Nivel no completado aun :( ");
        }

        List<LaserTrayecto> trayectos = nivel.getTrayectosLaser();
        System.out.println("Trayectoria del láser:");
        for (LaserTrayecto trayecto : trayectos) {
            System.out.println("Desde (" + trayecto.getInicio().getColumna() + ", " + trayecto.getInicio().getFila() +
                    ") hasta (" + trayecto.getFin().getColumna() + ", " + trayecto.getFin().getFila() + ")");
        }
        System.out.println();
        System.out.println();


        // Verifica la posición del láser
        String expected2 = "Posición del láser: (8,0, 9,0)"; // Ajusta esto según la nueva posición esperada
        Direccion d2 = Direccion.NE; // Asegúrate de que esta dirección sea la correcta después del movimiento
        Direccion act2 = laser.getDireccion();
        assertEquals(d2, act2);

        grilla.moverBloque(nivel, new Coordenada(1, 2), new Coordenada(2, 2));
        System.out.println("\u001B[31mEjecutando nivel: 1\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println();
        }
        if (nivel.verificarNivelCompletado()) {
            System.out.println("COMPLETADO");
        } else {
            System.out.println("Nivel no completado aun :( ");
        }
        System.out.println();
        System.out.println();

        grilla.moverBloque(nivel, new Coordenada(3, 2), new Coordenada(3, 3));
        System.out.println("\u001B[31mEjecutando nivel: 1\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println();
        }
        if (nivel.verificarNivelCompletado()) {
            System.out.println("COMPLETADO");
        } else {
            System.out.println("Nivel no completado aun :( ");
        }
        System.out.println();
        System.out.println();

        grilla.moverBloque(nivel, new Coordenada(1, 5), new Coordenada(2, 5));
        System.out.println("\u001B[31mEjecutando nivel: 1\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println();
        }
        if (nivel.verificarNivelCompletado()) {
            System.out.println("COMPLETADO");
        } else {
            System.out.println("Nivel no completado aun :( ");
        }

        // juego.reininciarJuego();


        CargarNiveles.obtenerGrillaOriginal(nivel);
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println();
        }

        Grilla gg  = nivel.getGrilla();
        System.out.println();

        for (int fila = 0; fila < gg.getFilas(); fila++) {
            for (int col = 0; col < gg.getColumnas(); col++) {
                Bloque bloque = gg.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println();
        }

        List<LaserTrayecto> trayectosS = nivel.getTrayectosLaser();
        System.out.println("Trayectoria del láser:");
        for (LaserTrayecto trayecto : trayectosS) {
            System.out.println("Desde (" + trayecto.getInicio().getColumna() + ", " + trayecto.getInicio().getFila() +
                    ") hasta (" + trayecto.getFin().getColumna() + ", " + trayecto.getFin().getFila() + ")");
        }
        System.out.println();
        System.out.println();
        nivel.reiniciarTrayectoria();
        List<LaserTrayecto> trayectos2 = nivel.getTrayectosLaser();
        System.out.println("Trayectoria del láser:");
        for (LaserTrayecto trayecto : trayectos2) {
            System.out.println("Desde (" + trayecto.getInicio().getColumna() + ", " + trayecto.getInicio().getFila() +
                    ") hasta (" + trayecto.getFin().getColumna() + ", " + trayecto.getFin().getFila() + ")");
        }
        System.out.println();




    }
}

