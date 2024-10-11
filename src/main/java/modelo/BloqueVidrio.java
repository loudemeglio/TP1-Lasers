package modelo;
import java.util.ArrayList;
import java.util.List;
//NO ME ANDA EL LIST!!

import static modelo.Direccion.*;
import static modelo.Direccion.SW;

public class BloqueVidrio implements Bloque {
    

    @Override
    public void interactuarConLaser(Laser laser) {
        System.out.println("Refracta el rayo en 2");

        List<Laser> lasers = new ArrayList<>();
        lasers.add(laser);  // Añadir el láser original

        Laser nuevoLaser = new Laser(laser.getColumna(), laser.getFila(), laser.getDireccion(), 0.1);
        //LadoImpacto ladoImpacto = determinarLadoImpacto(laser);  // Determinar el lado de impacto
        Direccion nuevaDireccion = cambiarDireccion(laser.getDireccion(), ladoImpacto, laser);  // Calcular la nueva dirección
        //es la misma de bloque espejo

        // Crear un nuevo láser en la nueva dirección, en la misma posición
        lasers.add(nuevoLaser);
    }

    @Override
    public String getSimbolo() {
        return "G";
    }

    public void cambiarDireccion (Laser laser, LadoImpacto ladoImpacto, Direccion direccion){
        if (direccion != null) {
            switch (direccion) {
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
    
    @Override
    public boolean esMovil() {
        return true;
    }
}
