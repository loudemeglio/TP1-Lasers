package modelo;

import java.util.List;

public class Nivel {
    private Grilla grilla;
    private List<Laser> lasers;
    private List<Objetivo> objetivos;

    public Nivel(Grilla grilla, List<Laser> lasers, List<Objetivo> objetivos) {
        this.grilla = grilla;
        this.lasers = lasers;
        this.objetivos = objetivos;
    }

    private Bloque crearBloque(char simbolo) {
        /* Metodo que crea un bloque dado el simbolo que recibe del archivo
        * llamando a la los distintos tipos de bloques de la interfaz*/
        return null;
    }
}
