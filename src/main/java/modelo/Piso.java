package modelo;

public class Piso implements Bloque {

    @Override
    public void interactuarConLaser(Laser laser) {

    }

    @Override
    public String getSimbolo() {
        return ".";
    }

    @Override
    public boolean esMovil() {
        return false;
    }
}
