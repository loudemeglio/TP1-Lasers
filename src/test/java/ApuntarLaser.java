import modelo.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApuntarLaser {
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
    public void testApuntarLaser1() {
        // Cargar el nivel
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
            System.out.println(); // Salto de línea al final de cada fila
        }

        // Mover el láser
        nivel.apuntarLaser(laser); // Mover el láser una vez

        // Obtener la posición después del movimiento
        String posicion = laser.obtenerPosicion();

        List<LaserTrayecto> trayectos = nivel.getTrayectosLaser();
        System.out.println("Trayectoria del láser:");
        for (LaserTrayecto trayecto : trayectos) {
            System.out.println("Desde (" + trayecto.getInicio().getColumna() + ", " + trayecto.getInicio().getFila() +
                    ") hasta (" + trayecto.getFin().getColumna() + ", " + trayecto.getFin().getFila() + ")");
        }

        // Verificar que la posición sea la esperada
        String expected = "Posición del láser: (8,0, 9,0)";
        String actual = laser.obtenerPosicion(); // Método que obtiene la posición formateada
        Direccion d = Direccion.NE;
        Direccion act = laser.getDireccion();
        System.out.println("Posición actual: " + actual); // Debugging
        assertEquals(d, act);
    }

    @Test
    public void testApuntarLaser2() {
        // Cargar el nivel
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(2);
        Grilla grilla = nivel.getGrilla();
        Laser laser = nivel.getLasers().get(0); // Obtener el láser desde el nivel

        System.out.println(); // Salto de línea al final de cada fila
        System.out.println("\u001B[31mEjecutando nivel: 2\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println(); // Salto de línea al final de cada fila
        }

        // Mover el láser
        nivel.apuntarLaser(laser); // Mover el láser una vez

        // Obtener la posición después del movimiento
        String posicion = laser.obtenerPosicion();

        // Verificar que la posición sea la esperada
        String expected = "Posición del láser: (8,0, 9,0)";
        String actual = laser.obtenerPosicion(); // Método que obtiene la posición formateada

        Direccion d = Direccion.SE;
        Direccion act = laser.getDireccion();
        System.out.println("Posición actual: " + actual); // Debugging
        assertEquals(d, act);
    }

    @Test
    public void testApuntarLaser3() {
        // Cargar el nivel
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(3);
        Grilla grilla = nivel.getGrilla();
        Laser laser = nivel.getLasers().get(0); // Obtener el láser desde el nivel

        System.out.println(); // Salto de línea al final de cada fila
        System.out.println("\u001B[31mEjecutando nivel: 3\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println(); // Salto de línea al final de cada fila
        }

        // Mover el láser
        nivel.apuntarLaser(laser); // Mover el láser una vez

        // Obtener la posición después del movimiento
        String posicion = laser.obtenerPosicion();

        // Verificar que la posición sea la esperada
        String expected = "Posición del láser: (8,0, 9,0)";
        String actual = laser.obtenerPosicion(); // Método que obtiene la posición formateada

        Direccion d = Direccion.SE;
        Direccion act = laser.getDireccion();
        System.out.println("Posición actual: " + actual); // Debugging
        assertEquals(d, act);
    }
    @Test
    public void testApuntarLaser4() {
            // Cargar el nivel
            Nivel nivel = CargarNiveles.cargarNivelDeArchivo(4);
            Grilla grilla = nivel.getGrilla();
            Laser laser = nivel.getLasers().get(0); // Obtener el láser desde el nivel

            System.out.println(); // Salto de línea al final de cada fila
            System.out.println("\u001B[31mEjecutando nivel: 4\u001B[0m");
            for (int fila = 0; fila < grilla.getFilas(); fila++) {
                for (int col = 0; col < grilla.getColumnas(); col++) {
                    Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                    if (bloque != null) {
                        System.out.print(bloque.getClass().getSimpleName() + " ");
                    } else {
                        System.out.print("Vacío ");
                    }
                }
                System.out.println(); // Salto de línea al final de cada fila
            }

            // Mover el láser
            nivel.apuntarLaser(laser); // Mover el láser una vez

            // Obtener la posición después del movimiento
            String posicion = laser.obtenerPosicion();

            // Verificar que la posición sea la esperada
            String expected = "Posición del láser: (8,0, 9,0)";
            String actual = laser.obtenerPosicion();

            Direccion d = Direccion.NW;
            Direccion act = laser.getDireccion();
            System.out.println("Posición actual: " + actual); // Debugging
            assertEquals(d, act);
        }

    @Test
    public void testApuntarLaser5() {
        // Cargar el nivel
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(5);
        Grilla grilla = nivel.getGrilla();
        Laser laser = nivel.getLasers().get(0); // Obtener el láser desde el nivel

        System.out.println(); // Salto de línea al final de cada fila
        System.out.println("\u001B[31mEjecutando nivel: 5\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println(); // Salto de línea al final de cada fila
        }

        // Mover el láser
        nivel.apuntarLaser(laser); // Mover el láser una vez

        // Obtener la posición después del movimiento
        String posicion = laser.obtenerPosicion();

        // Verificar que la posición sea la esperada
        String actual = laser.obtenerPosicion(); // Método que obtiene la posición formateada

        Direccion d = Direccion.SW;
        Direccion act = laser.getDireccion();
        System.out.println("Posición actual: " + actual); // Debugging
        assertEquals(d, act);
    }

    @Test
    public void testApuntarLaser6() {
        // Cargar el nivel
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(6);
        Grilla grilla = nivel.getGrilla();
        Laser laser = nivel.getLasers().get(0); // Obtener el láser desde el nivel
        Laser laser2 = nivel.getLasers().get(1); // Obtener el láser desde el nivel


        System.out.println(); // Salto de línea al final de cada fila
        System.out.println("\u001B[31mEjecutando nivel: 6\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println(); // Salto de línea al final de cada fila
        }

        // Mover el láser
        nivel.apuntarLaser(laser); // Mover el láser una vez
        nivel.apuntarLaser(laser2);

        // Obtener la posición después del movimiento
        String posicion = laser.obtenerPosicion();

        // Verificar que la posición sea la esperada
        String actual = laser.obtenerPosicion(); // Método que obtiene la posición formateada

        Direccion d = Direccion.SE;
        Direccion act = laser.getDireccion();
        System.out.println("Posición actual: " + actual); // Debugging
        assertEquals(d, act);
    }

    /*@Test
    public void testApuntarLaseraux() {
        // Cargar el nivel
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(7);
        Grilla grilla = nivel.getGrilla();
        Laser laser = nivel.getLasers().get(0); // Obtener el láser desde el nivel

        System.out.println(); // Salto de línea al final de cada fila
        System.out.println("\u001B[31mEjecutando nivel: 7\u001B[0m");
        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                Bloque bloque = grilla.getCelda(fila, col).getTipoBloque();
                if (bloque != null) {
                    System.out.print(bloque.getClass().getSimpleName() + " ");
                } else {
                    System.out.print("Vacío ");
                }
            }
            System.out.println(); // Salto de línea al final de cada fila
        }

        // Mover el láser
        nivel.apuntarLaser(laser); // Mover el láser una vez

        // Obtener la posición después del movimiento
        String posicion = laser.obtenerPosicion();

        // Verificar que la posición sea la esperada
        String actual = laser.obtenerPosicion(); // Método que obtiene la posición formateada

        Direccion d = Direccion.SW;
        Direccion act = laser.getDireccion();
        System.out.println("Posición actual: " + actual); // Debugging
        assertEquals(d, act);
    }*/

}
