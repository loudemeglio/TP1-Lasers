package modelo;

public class LaserTrayecto {
    private Coordenada inicio;
    private Coordenada fin;

    public LaserTrayecto(Coordenada inicio, Coordenada fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public Coordenada getInicio() {
        return inicio;
    }

    public Coordenada getFin() {
        return fin;
    }
}
