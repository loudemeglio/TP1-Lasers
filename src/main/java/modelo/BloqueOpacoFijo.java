package modelo;

public class BloqueOpacoFijo implements Bloque {
    @Override
    public void interactuarConLaser(Laser laser) {
        laser.detener();
        System.out.println("El laser ha sido absorbido");
    }


    public String getSimbolo() {
        return "F";
    }

    @Override
    public boolean esMovil() {
        return false;
    }
}
