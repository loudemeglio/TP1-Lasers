package modelo;

public class BloqueVidrio implements Bloque {
    

    @Override
    public void interactuarConLaser(Laser laser) {
        System.out.println("Refracta el rayo en 2");

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
