package modelo;

public class BloqueCristal implements Bloque {
    @Override
    public String getSimbolo() {
        return "C";
    }
    
    @Override
    public void interactuarConLaser(Laser laser) {
        Direccion direccion = laser.getDireccion();
        int coordenadaActual = (int)laser.getColumna();
        //LadoImpacto ladoImpacto = determinarLadoImpacto(laser);
        Coordenada coordenadaSalida = avanzarCoordenada(coordenadaActual, direccion, ladoImpacto);
        laser.setCoordenada(coordenadaSalida.getFila(), coordenadaSalida.getColumna());
    }

    private Coordenada avanzarCoordenada(Coordenada coord, Direccion direccion, LadoImpacto ladoImpacto) {
        switch (ladoImpacto) {
            case LATERAL:
                switch (direccion) {
                    case NE: // Noreste
                        return new Coordenada(coord.getColumna() + 2, coord.getFila());
                    case SE: // Sureste
                        return new Coordenada(coord.getColumna() + 2, coord.getFila());
                    case SW: // Suroeste
                        return new Coordenada(coord.getColumna(), coord.getFila() - 2);
                    case NW: // Noroeste
                        return new Coordenada(coord.getColumna(), coord.getFila() - 2);
                    default:
                        return coord; // No avanza si no se reconoce la dirección
                }
            case SUPERIOR_INFERIOR:
                switch (direccion) {
                    case NE: // Noreste
                        return new Coordenada(coord.getColumna() , coord.getFila() - 2);
                    case SE: // Sureste
                        return new Coordenada(coord.getColumna() , coord.getFila() + 2);
                    case SW: // Suroeste
                        return new Coordenada(coord.getColumna(), coord.getFila() + 2);
                    case NW: // Noroeste
                        return new Coordenada(coord.getColumna(), coord.getFila() - 2);
                    default:
                        return coord; // No avanza si no se reconoce la dirección
                }
            default:
                throw new IllegalArgumentException("Lado de impacto desconocido");
        }
    }

    @Override
    public boolean esMovil() {
        return false;
    }
}
