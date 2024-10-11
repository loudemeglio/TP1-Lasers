package modelo;

public class BloqueOpacoMovil implements Bloque {
    @Override
    public void interactuarConLaser(Laser laser) {

        laser.detener();
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
