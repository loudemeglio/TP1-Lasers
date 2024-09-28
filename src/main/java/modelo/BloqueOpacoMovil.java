package modelo;

public class BloqueOpacoMovil implements Bloque {
    @Override
    public void interactuarConLaser(Laser laser) {
        System.out.println("El laser ha sido absorbido.");
    }

    public String getSimbolo() {
        return "B";
    }

    @Override
    public boolean esMovil() {
        return true;
    }
}
