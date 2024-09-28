package modelo;

public class BloqueEspejo implements Bloque {
    @Override
    public void interactuarConLaser(Laser laser) {
        System.out.println("El laser ha sido reflejado.");
    }
    
    public String getSimbolo() {
        return "R";
    }

    @Override
    public boolean esMovil() {
        return true;
    }
}
