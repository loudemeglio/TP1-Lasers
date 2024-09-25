package modelo;

public interface Bloque {
    void interactuarConLaser(Laser laser);
    boolean esMovil(); // los fijos no, los movil si
}

