package vista;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modelo.*;

public class VistaBloque {
    public static Rectangle crearRepresentacion(Bloque bloque) {
        Rectangle rectangulo = new Rectangle(50, 50);
        rectangulo.setStroke(Color.BLACK);
        rectangulo.setStrokeWidth(1);

        if (bloque == null) {
            rectangulo.setFill(Color.WHITE); // Piso vacío
        } else if (bloque instanceof BloqueEspejo) {
            rectangulo.setFill(Color.CADETBLUE); // Bloque Espejo
        } else if (bloque instanceof BloqueOpacoFijo) {
            rectangulo.setFill(Color.DIMGRAY); // Bloque Opaco Fijo
        } else if (bloque instanceof BloqueOpacoMovil) {
            rectangulo.setFill(Color.DARKGREY); // Bloque Opaco Móvil
        } else if (bloque instanceof BloqueCristal) {
            rectangulo.setFill(Color.LIGHTBLUE);
        } else if (bloque instanceof Piso) {
            rectangulo.setFill(Color.LIGHTGREY);
        } else if (bloque instanceof BloqueVidrio) {
            rectangulo.setFill(Color.LIGHTSTEELBLUE); // Bloque Cristal
        } else {
            rectangulo.setFill(Color.WHITE); // Caso por defecto para bloques desconocidos
        }


        return rectangulo;
    }
}
