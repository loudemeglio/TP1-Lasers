package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CargarNiveles {

    private static final Map<String, Supplier<Bloque>> registroBloques = new HashMap<>();

    static {
        registroBloques.put("R", BloqueEspejo::new);
        registroBloques.put("B", BloqueOpacoMovil::new);
        registroBloques.put(".", Piso::new);
        registroBloques.put("F", BloqueOpacoFijo::new);
        registroBloques.put("C", BloqueCristal::new);
        registroBloques.put("G", BloqueVidrio::new);

    }

    public static Nivel cargarNivelDeArchivo(int numeroNivel) {
        String ruta = "src/main/resources/levels/level" + numeroNivel + ".dat";
        List<String> lineasGrilla = new ArrayList<>();
        int maxColumnas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while ((linea = br.readLine()) != null && !linea.trim().isEmpty()) {
                lineasGrilla.add(linea);
                maxColumnas = Math.max(maxColumnas, linea.length());
            }

            int filas = lineasGrilla.size();
            int filasDuplicadas = filas * 2;
            int columnasDuplicadas = maxColumnas * 2;

            Nivel nivel = new Nivel(numeroNivel, filasDuplicadas, columnasDuplicadas);
            Grilla grilla = new Grilla(columnasDuplicadas, filasDuplicadas);

            for (int i = 0; i < filasDuplicadas; i++) {
                for (int j = 0; j < columnasDuplicadas; j++) {
                    String simbolo = " ";
                    if (i / 2 < filas && j / 2 < maxColumnas) {
                        String[] celdas = lineasGrilla.get(i / 2).split("");
                        simbolo = j / 2 < celdas.length ? celdas[j / 2] : " ";
                    }

                    Bloque bloque = crearBloqueDesdeSimbolo(simbolo);
                    grilla.setCelda(i, j, bloque);
                }
            }
            nivel.setGrilla(grilla);

            // Leer emisores y objetivos
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                if (partes[0].equals("E")) { // Emisor
                    int columna = Integer.parseInt(partes[1]);
                    int fila = Integer.parseInt(partes[2]);
                    Direccion direccion = Direccion.valueOf(partes[3]);
                    nivel.agregarLaser(new Laser(columna,  fila, direccion));
                } else if (partes[0].equals("G")) { // Objetivo
                    int columna = Integer.parseInt(partes[1]);
                    int fila = Integer.parseInt(partes[2]);
                    nivel.agregarObjetivo(new Objetivo(columna, fila));
                }
            }

            return nivel;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static Bloque crearBloqueDesdeSimbolo(String simbolo) {
        // Buscar el constructor del bloque asociado al símbolo
        Supplier<Bloque> constructor = registroBloques.get(simbolo);
        if (constructor != null) {
            return constructor.get(); // Crear el bloque
        }
        return null; // Si el símbolo no está registrado, devolver null
    }
}
