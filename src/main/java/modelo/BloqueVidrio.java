package modelo;

public class BloqueVidrio implements Bloque {
    private Nivel nivel; // Referencia al nivel


    @Override
    public void interactuarConLaser(Laser laser) {

        Direccion direccionActual = laser.getDireccion();
        LadoImpacto ladoImpacto = determinarLadoImpacto(laser);

        Direccion direccionReflejada = cambiarDireccion(direccionActual, ladoImpacto);


        Laser laser2 = new Laser(laser.getColumna(), laser.getFila(), direccionReflejada);
        //   nivel.agregarLaser(laser2);
        System.out.println("Bloque de vidrio: El láser se ha dividido. Dirección reflejada: " + direccionReflejada);

    }

    @Override
    public String getSimbolo() {
        return "G";
    }


    @Override
    public boolean esMovil() {
        return true;
    }



    public LadoImpacto determinarLadoImpacto(Laser laser) {
        int fila = laser.getFila();
        int col = laser.getColumna();

        if (fila % 2 != 0 && col % 2 == 0) {
            return LadoImpacto.LATERAL;
        } else if (col % 2 != 0) {
            return LadoImpacto.SUPERIOR_INFERIOR;
        }
        return null;
    }

    public Direccion cambiarDireccion(Direccion direccion, LadoImpacto ladoImpacto) {
        switch (ladoImpacto) {
            case LATERAL:
                // Refleja el rayo en direcciones perpendiculares
                switch (direccion) {
                    case SE:
                        return Direccion.SW;  // Refleja a la izquierda
                    case SW:
                        return Direccion.SE;  // Refleja a la derecha
                    case NE:
                        return Direccion.NW;
                    case NW:
                        return Direccion.NE;
                    default:
                        throw new IllegalArgumentException("Dirección desconocida para reflejar en lateral");
                }
            case SUPERIOR_INFERIOR:
                switch (direccion) {
                    case SE:
                        return Direccion.NE;  // Refleja hacia arriba
                    case SW:
                        return Direccion.NW;  // Refleja hacia arriba
                    case NE:
                        return Direccion.SE;  // Refleja hacia abajo
                    case NW:
                        return Direccion.SW;  // Refleja hacia abajo
                    default:
                        throw new IllegalArgumentException("Dirección desconocida para reflejar en superior/inferior");
                }
            default:
                throw new IllegalArgumentException("Lado de impacto desconocido");
        }
    }

}
