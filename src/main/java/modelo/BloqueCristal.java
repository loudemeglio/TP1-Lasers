package modelo;

public class BloqueCristal implements Bloque {
    @Override
    public String getSimbolo() {
        return "C";
    }
    
    @Override
    public void interactuarConLaser(Laser laser) {

    }

    @Override
    public boolean esMovil() {
        return false;
    }
}
