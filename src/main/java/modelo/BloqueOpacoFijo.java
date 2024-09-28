package modelo;

public class BloqueOpacoFijo implements Bloque {
    @Override
    public void interactuarConLaser(Laser laser) {
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
