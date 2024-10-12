package vista;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import modelo.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class UI extends Application {
    private Juego juego;          // Maneja los niveles del juego
    private GridPane grid;        // Cuadrícula del juego
    private Pane laserLayer;      // Capa para dibujar los láseres

    @Override
    public void start(Stage primaryStage) {
        juego = new Juego(6);        // Inicia con 6 niveles
        grid = new GridPane();
        laserLayer = new Pane();     // Inicializa la capa del láser
        construirGrilla();           // Carga el nivel actual inicialmente

        // Crear los botones para seleccionar niveles
        VBox vbox = new VBox();
        vbox.getChildren().addAll(crearBotonesNiveles());
        vbox.setSpacing(5);                  // Espaciado entre los botones
        vbox.setPadding(new Insets(10));

        // Separador vertical
        Separator separator = new Separator();
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);

        // Crear un StackPane para superponer la cuadrícula y la capa del láser
        StackPane gridStack = new StackPane();
        gridStack.getChildren().addAll(grid, laserLayer);

        // Alinear el StackPane
        gridStack.setAlignment(Pos.TOP_LEFT);

        // Usar HBox para colocar los botones a la izquierda y la cuadrícula a la derecha
        HBox hbox = new HBox(10);             // Espaciado de 10 píxeles entre los elementos del HBox
        hbox.getChildren().addAll(vbox, separator, gridStack);
        hbox.setPadding(new Insets(10));

        // Configurar la escena principal con el HBox que contiene los botones y el StackPane
        Scene scene = new Scene(hbox, 500, 400);
        primaryStage.setTitle("Juego de Láseres");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private Button[] crearBotonesNiveles () {
            Button[] botones = new Button[juego.getTotalNiveles()]; // Basado en el número de niveles del juego

            for (int i = 0; i < botones.length; i++) {
                int nivelIndex = i; // Índice del nivel (comienza en 0)
                botones[i] = new Button("Nivel " + (nivelIndex + 1)); // Mostrar como Nivel 1, 2, etc.
                botones[i].setMinSize(100, 30);   // Tamaño mínimo de los botones
                botones[i].setPrefSize(50, 30);  // Tamaño preferido de los botones

                // Acción al presionar el botón
                botones[i].setOnAction(event -> {
                    juego.setNivelActual(nivelIndex); // Cambiar al nivel seleccionado
                    construirGrilla();                // Reconstruir la cuadrícula para el nuevo nivel
                });
            }
            return botones;
    }

    private void construirGrilla() {
        grid.getChildren().clear();        // Limpia la cuadrícula antes de agregar nuevos elementos
        laserLayer.getChildren().clear();  // Limpia la capa del láser

        Nivel nivel = juego.getNivelActual(); // Obtiene el nivel actual del juego
        Grilla grilla = nivel.getGrilla();
        List<Laser> emisores = nivel.getLasers();
        List<Objetivo> objetivos = nivel.getObjetivos();
        List<LaserTrayecto> trayectosLaser = nivel.getTrayectosLaser();

        // Configurar el GridPane
        grid.setGridLinesVisible(true);    // Mostrar las líneas de la cuadrícula
        grid.setAlignment(Pos.TOP_LEFT);   // Alinear la cuadrícula en la parte superior izquierda

        // Establecer las restricciones de filas y columnas
        /*for (int fila = 0; fila < grilla.getFilas(); fila++) {
            grid.getRowConstraints().add(new javafx.scene.layout.RowConstraints(50)); // Altura de cada fila
        }
        for (int col = 0; col < grilla.getColumnas(); col++) {
            grid.getColumnConstraints().add(new javafx.scene.layout.ColumnConstraints(50)); // Ancho de cada columna
        }*/

        // Recorrer las celdas del nivel, creando rectángulos para cada celda
        for (int fila = 0; fila < grilla.getFilas(); fila+=2) {
            for (int col = 0; col < grilla.getColumnas(); col+=2) {
                Rectangle rectangulo = new Rectangle(50, 50); // Tamaño de cada celda
                Celda celda = grilla.getCelda(fila, col);
                Bloque bloque = celda != null ? celda.getTipoBloque() : null;

                // Cambiar el color según el tipo de bloque
                if (bloque == null) {
                    rectangulo.setFill(Color.WHITE); // Piso vacío
                } else if (bloque instanceof BloqueEspejo) {
                    rectangulo.setFill(Color.SKYBLUE); // Bloque Espejo
                } else if (bloque instanceof BloqueOpacoFijo) {
                    rectangulo.setFill(Color.BLACK); // Bloque Opaco Fijo
                } else if (bloque instanceof BloqueOpacoMovil) {
                    rectangulo.setFill(Color.DARKGREY); // Bloque Opaco Móvil
                } else if (bloque instanceof BloqueCristal) {
                    rectangulo.setFill(Color.LIGHTBLUE); // Bloque Cristal
                } else if (bloque instanceof Piso) {
                    rectangulo.setFill(Color.LIGHTGREY); // Piso
                } else if (bloque instanceof BloqueVidrio) {
                    rectangulo.setFill(Color.LIGHTSTEELBLUE); // Bloque Vidrio
                } else {
                    rectangulo.setFill(Color.WHITE); // Caso por defecto
                }

                rectangulo.setStroke(Color.BLACK);
                rectangulo.setStrokeWidth(1); // Borde de cada celda

                // Agregar el rectángulo a la cuadrícula
                grid.add(rectangulo, col, fila, 2, 2);
            }
        }

        for (int fila = 0; fila < grilla.getFilas(); fila++) {
            for (int col = 0; col < grilla.getColumnas(); col++) {
                // Agregar emisores a la cuadrícula
                for (Laser emisor : emisores) {
                    Circle circuloEmisor = new Circle(5, Color.RED); // Círculo para el emisor (radio 10)
                    grid.add(circuloEmisor, emisor.getColInicial(), emisor.getFilInicial());
                    System.out.println("COL "+ emisor.getColInicial() + " FIL " + emisor.getFilInicial());
                    circuloEmisor.setTranslateY(-4);
                    circuloEmisor.setTranslateX(-5);
                    GridPane.setHalignment(circuloEmisor, javafx.geometry.HPos.CENTER);
                    GridPane.setValignment(circuloEmisor, javafx.geometry.VPos.CENTER);
                }

                // Agregar objetivos a la cuadrícula
                for (Objetivo objetivo : objetivos) {
                    Circle circuloObjetivo = new Circle(5, Color.ORANGE); // Círculo para el objetivo (radio 10)
                    grid.add(circuloObjetivo, objetivo.getColumna(), objetivo.getFila());
                    circuloObjetivo.setTranslateY(-5);
                    circuloObjetivo.setTranslateX(-5);
                    GridPane.setHalignment(circuloObjetivo, javafx.geometry.HPos.CENTER);
                    GridPane.setValignment(circuloObjetivo, javafx.geometry.VPos.CENTER);
                }

                // Dibujar el trayecto del láser usando líneas en la capa del láser
                for (LaserTrayecto trayecto : trayectosLaser) {
                    Coordenada inicio = trayecto.getInicio();
                    Coordenada fin = trayecto.getFin();

                    // Convertir las coordenadas de la grilla a píxeles (50 píxeles por celda)
                    double startX = inicio.getColumna() * 25; // Centro de la celda
                    double startY = inicio.getFila() * 25;    // Centro de la celda
                    double endX = fin.getColumna() * 25;      // Centro de la celda
                    double endY = fin.getFila() * 25;         // Centro de la celda

                    // Crear la línea del láser
                    Line laserLine = new Line(startX, startY, endX, endY);
                    laserLine.setStroke(Color.RED);   // Color rojo para el láser
                    laserLine.setStrokeWidth(2);      // Grosor de la línea

                    // Agregar la línea a la capa del láser
                    laserLayer.getChildren().add(laserLine);
                }
            }
        }
    }

    public static void main (String[]args){
        launch(args);
    }

}
