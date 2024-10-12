package modelo;

public class BloqueCristal implements Bloque {
    @Override
    public String getSimbolo() {
        return "C";
    }
    
    @Override
    public void interactuarConLaser(Laser laser) {
        // Obtener la dirección actual del láser
        Direccion direccionOriginal = laser.getDireccion();

        // Mover el láser dos posiciones en línea recta (constante)
        moverEnLineaRecta(laser, direccionOriginal);

        System.out.println("El laser sigue en su direccion: " + laser.getDireccion());

        // Mover el láser en la nueva dirección diagonal

    }

    @Override
    public boolean esMovil() {
        return false;
    }

    private void moverEnLineaRecta(Laser laser, Direccion direccion) {
        switch (direccion) {
            case SE:
            case NE:
                // Mover solo en columna (mantener fila constante)
                for (int i = 0; i < 2; i++) {
                    laser.moverColumnaPositiva();
                }
                break;
            case SW:
            case NW:
                // Mover solo en columna negativa (mantener fila constante)
                for (int i = 0; i < 2; i++) {
                    laser.moverColumnaNegativa();
                }
                break;
            default:
                throw new IllegalArgumentException("Dirección desconocida");
        }
    }
}
