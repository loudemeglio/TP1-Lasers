package modelo;

import static modelo.Direccion.*;

public class BloqueEspejo implements Bloque {
    @Override
    public void interactuarConLaser(Laser laser) {
        Direccion direccionActual = laser.getDireccion();
        LadoImpacto ladoImpacto = determinarLadoImpacto(laser);

        Direccion nuevaDireccion =  cambiarDireccion(direccionActual, ladoImpacto);
        laser.setDireccion(nuevaDireccion);
    }

    public String getSimbolo() {
        return "R";
    }

    @Override
    public boolean esMovil() {
        return true;
    }

    public LadoImpacto determinarLadoImpacto(Laser laser) {
        int fila = laser.getFila();
        int col = laser.getColumna();
        // Verificar si la fila es impar y la columna es par (impacto lateral)
        if (fila % 2 != 0 && col % 2 == 0) {
            return LadoImpacto.LATERAL;
        }
        // Verificar si la fila es par y la columna es impar (impacto superior/inferior)
        else if ( col % 2 != 0) {
            return LadoImpacto.SUPERIOR_INFERIOR;
        }
        return null; // O lanzar una excepción si no cumple con ninguna de las condiciones
    }

    public Direccion cambiarDireccion(Direccion direccion, LadoImpacto ladoImpacto) {
        switch (ladoImpacto) {
            case LATERAL:
                // Si el láser viene de izquierda o derecha, lo rediriges verticalmente
                switch (direccion) {
                    case SE:
                        return Direccion.SW;
                    case SW:
                        return Direccion.SE;
                    case NE:
                        return Direccion.NW;
                    case NW:
                        return Direccion.NE;
                    default:
                        throw new IllegalArgumentException("Dirección desconocida para reflejar en lateral");
                }
            case SUPERIOR_INFERIOR:
                // Si el láser viene de arriba o abajo, lo rediriges horizontalmente
                switch (direccion) {
                    case SE:
                        return Direccion.NE;
                    case SW:
                        return Direccion.NW;
                    case NE:
                        return Direccion.SE;
                    case NW:
                        return Direccion.SW;
                    default:
                        throw new IllegalArgumentException("Dirección desconocida para reflejar en superior/inferior");
                }
            default:
                throw new IllegalArgumentException("Lado de impacto desconocido");
        }
    }
}
