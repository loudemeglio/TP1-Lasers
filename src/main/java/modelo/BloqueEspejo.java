package modelo;

import static modelo.Direccion.*;

public class BloqueEspejo implements Bloque {
    @Override
    public void interactuarConLaser(Laser laser) {
        Direccion direccionActual = laser.getDireccion();
        int columna = (int)laser.getColumna(); // Suponiendo que tienes un método para obtener la columna

        // Si la columna es par, reflejar en una dirección específica
        if (direccionActual != null) {
            switch (direccionActual) {
                case SE:
                    laser.setDireccion(NE); // Cambiar la dirección del láser
                    break;
                case SW:
                    laser.setDireccion(NW);
                    break;
                case NE:
                    laser.setDireccion(SE);
                    break;
                case NW:
                    laser.setDireccion(SW);
                    break;
                default:
                    throw new IllegalArgumentException("Dirección desconocida para reflejar en superior/inferior");
            }
        } else {
            // Si no es una columna par, puedes definir otro comportamiento o lanzar una excepción
            throw new IllegalArgumentException("Lado de impacto desconocido");
        }

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
