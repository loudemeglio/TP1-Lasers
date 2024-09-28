import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import modelo.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GrillaTest {
    private Grilla grilla;
    @Before
    public void setUp() {
        grilla = new Grilla(5, 5); // Supongamos que el constructor toma filas y columnas
    }

    @Test
    public void testInicializacionGrilla() {
        assertNotNull(grilla); // Asegúrate de que la grilla no sea nula
        assertEquals(5, grilla.getFilas()); // Verifica que el número de filas sea correcto
        assertEquals(5, grilla.getColumnas()); // Verifica que el número de columnas sea correcto
    }

    @Test
    public void testDuplicacionGrilla1() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(1);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();

        assertEquals(12, grillaDuplicada.getFilas());
        assertEquals(8, grillaDuplicada.getColumnas());
    }

    @Test
    public void testDuplicacionGrilla2() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(2);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();

        assertEquals(10, grillaDuplicada.getFilas());
        assertEquals(8, grillaDuplicada.getColumnas());
    }

    @Test
    public void testDuplicacionGrilla3() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(3);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();

        assertEquals(6, grillaDuplicada.getFilas());
        assertEquals(6, grillaDuplicada.getColumnas());
    }

    @Test
    public void testDuplicacionGrilla4() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(4);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();

        assertEquals(8, grillaDuplicada.getFilas());
        assertEquals(8, grillaDuplicada.getColumnas());
    }

    @Test
    public void testDuplicacionGrilla5() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(5);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();

        assertEquals(8, grillaDuplicada.getFilas());
        assertEquals(8, grillaDuplicada.getColumnas());
    }

    @Test
    public void testDuplicacionGrilla6() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(6);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();

        assertEquals(8, grillaDuplicada.getFilas());
        assertEquals(8, grillaDuplicada.getColumnas());
    }

    @Test
    public void testEmisores1() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(1);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Laser> emisores = nivel.getLasers();
        int cantidad = emisores.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 1, cantidad);

    }

    @Test
    public void testEmisores2() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(2);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Laser> emisores = nivel.getLasers();
        int cantidad = emisores.size();

        assertEquals("La cantidad de emisores en el nivel 2 debe ser 1", 1, cantidad);

    }

    @Test
    public void testEmisores3() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(3);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Laser> emisores = nivel.getLasers();
        int cantidad = emisores.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 1, cantidad);

    }
    @Test
    public void testEmisores4() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(4);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Laser> emisores = nivel.getLasers();
        int cantidad = emisores.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 1, cantidad);

    }

    @Test
    public void testEmisores6() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(6);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Laser> emisores = nivel.getLasers();
        int cantidad = emisores.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 2, cantidad);

    }

    @Test
    public void testObjetivos1() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(1);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Objetivo> objetivos= nivel.getObjetivos();
        int cantidad = objetivos.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 1, cantidad);

    }

    @Test
    public void testObjetivos2() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(2);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Objetivo> objetivos= nivel.getObjetivos();
        int cantidad = objetivos.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 3, cantidad);

    }

    @Test
    public void testObjetivos3() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(3);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Objetivo> objetivos= nivel.getObjetivos();
        int cantidad = objetivos.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 1, cantidad);

    }

    @Test
    public void testObjetivos4() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(4);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Objetivo> objetivos= nivel.getObjetivos();
        int cantidad = objetivos.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 5, cantidad);

    }

    @Test
    public void testObjetivos5() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(5);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Objetivo> objetivos= nivel.getObjetivos();
        int cantidad = objetivos.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 2, cantidad);

    }

    @Test
    public void testObjetivos6() {
        // Cargar el nivel, suponiendo que el nivel 1 existe en la ruta especificada
        Nivel nivel = CargarNiveles.cargarNivelDeArchivo(6);

        // Verificar que la grilla se duplicó correctamente
        Grilla grillaDuplicada = nivel.getGrilla();
        List<Objetivo> objetivos= nivel.getObjetivos();
        int cantidad = objetivos.size();

        assertEquals("La cantidad de emisores en el nivel 1 debe ser 2", 4, cantidad);

    }
}
