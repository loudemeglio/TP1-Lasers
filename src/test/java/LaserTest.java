import modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LaserTest {

    private Grilla grilla;
    private Nivel nivel;
    private Laser laser;

    @BeforeEach
    public void setUp() {
        // Inicializar una grilla de 5x5
        grilla = new Grilla(5, 5);
        // Crear un nivel con 5 filas y 5 columnas
        nivel = new Nivel(1, 5, 5);
        // Crear un láser en la posición inicial (2.5, 2.5) y dirección sureste (SE)
        laser = new Laser(2.5, 2.5, Direccion.SE, 0.5);
        nivel.agregarLaser(laser); // Agregar el láser al nivel
    }

    @Test
    public void testMovimientoLaser() {
        // Realizar el movimiento del láser
        for (int i = 0; i < 5; i++) { // Hacer que se mueva 5 pasos
            laser.mover();
        }

        // Verificar las coordenadas finales
        assertEquals(2.5 + (0.5 * 5), laser.getColumna(), 0.01);
        assertEquals(2.5 + (0.5 * 5), laser.getFila(), 0.01);
    }

    @Test
    public void testLaserFueraDeGrilla() {
        // Mover el láser para que salga de la grilla
        laser = new Laser(4.5, 4.5, Direccion.SE, 1.0); // Colocarlo cerca del borde
        laser.mover();

        assertTrue(laser.estaFueraDeGrilla(grilla), "El láser debería estar fuera de la grilla.");
    }

}
