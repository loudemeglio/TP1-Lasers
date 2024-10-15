package modelo;

public class BloqueVidrio implements Bloque {
    

    @Override
    public void interactuarConLaser(Laser laser) {

    }

    @Override
    public String getSimbolo() {
        return "G";
    }
    
    
    @Override
    public boolean esMovil() {
        return true;
    }
}
