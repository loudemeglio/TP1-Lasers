package vista;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UI extends Application {
    private Juego juego;
    private GridPane grid;
    private Pane laserLayer;
    private ArrayList<Rectangle> rectangulos;


    private Rectangle draggedRectangle; // Para almacenar el rectángulo que se está arrastrando
    private int draggedColumn; // Para almacenar la columna de origen
    private int draggedRow; // Para almacenar la fila de origen
    private int finalColum;
    private int finalRow;


    @Override
    public void start(Stage primaryStage) {
        juego = new Juego(6);        // Inicia con 6 niveles
        grid = new GridPane();
        laserLayer = new Pane();
        rectangulos = new ArrayList<>();

        construirGrilla();           // Carga el nivel actual inicialmente

        VBox vbox = new VBox();
        vbox.getChildren().addAll(crearBotonesNiveles());
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10));

        Separator separator = new Separator();
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);

        StackPane gridStack = new StackPane();
        gridStack.getChildren().addAll(grid, laserLayer);
        laserLayer.setMouseTransparent(true);

        gridStack.setAlignment(Pos.TOP_LEFT);

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(vbox, separator, gridStack);
        hbox.setPadding(new Insets(10));

        Scene scene = new Scene(hbox, 500, 400);
        primaryStage.setTitle("Juego de Láseres");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button[] crearBotonesNiveles() {
        Button[] botones = new Button[juego.getTotalNiveles()];

        for (int i = 0; i < botones.length; i++) {
            int nivelIndex = i;
            botones[i] = new Button("Nivel " + (nivelIndex + 1));
            botones[i].setMinSize(100, 30);
            botones[i].setPrefSize(50, 30);

            botones[i].setOnAction(event -> {
                juego.setNivelActual(nivelIndex);
                construirGrilla();
            });
        }
        return botones;
    }

    // Función principal que organiza la construcción de la grilla
    private void construirGrilla() {
        grid.getChildren().clear();
        laserLayer.getChildren().clear();
        rectangulos.clear();

        Nivel nivel = juego.getNivelActual();
        Grilla grilla = nivel.getGrilla();
        List<Laser> emisores = nivel.getLasers();
        List<Objetivo> objetivos = nivel.getObjetivos();
        List<LaserTrayecto> trayectosLaser = nivel.getTrayectosLaser();

        configurarGrilla(grilla);
        agregarEmisores(emisores);
        agregarObjetivos(objetivos);
        dibujarTrayectosLaser(trayectosLaser);
    }

    // Configurar la grilla con las celdas
    private void configurarGrilla(Grilla grilla) {
        for (int fila = 0; fila < grilla.getFilas(); fila += 2) {
            for (int col = 0; col < grilla.getColumnas(); col += 2) {
                Celda celda = grilla.getCelda(fila, col);
                Bloque bloque = celda != null ? celda.getTipoBloque() : null;

                Rectangle rectangulo;
                if (bloque != null) {
                    final int column = col; // Captura la columna
                    final int row = fila;
                    rectangulo = VistaBloque.crearRepresentacion(bloque);

                } else {
                    rectangulo = new Rectangle(50, 50, Color.WHITE);
                    rectangulo.setStroke(Color.BLACK);
                    rectangulo.setStrokeWidth(1);
                }

                grid.add(rectangulo, col, fila, 2, 2);
                rectangulos.add(rectangulo);
            }
        }
        System.out.println("LA lista de rectangulos tiene : " + rectangulos.size());

        grid.setOnMouseDragged(event -> {
            if (draggedRectangle != null) {
                draggedRectangle.setX(event.getSceneX() - draggedRectangle.getWidth() / 2);
                draggedRectangle.setY(event.getSceneY() - draggedRectangle.getHeight() / 2);
                System.out.println("Se ajustan las coordenadas para que el rectángulo se mueva correctamente, centrado en el cursor del mouse.");
            }
        });

        grid.setOnMouseReleased(event -> {
            if (draggedRectangle != null) {
                int mouseX = (int) event.getX();
                int mouseY = (int) event.getY();
                int targetColumn = ((mouseX / 50) / 2)*2;
                int targetRow = ((mouseY / 50) / 2)*2;

                if (targetColumn >= 0 && targetColumn < grilla.getColumnas() && targetRow >= 0 && targetRow < grilla.getFilas()) {
                    // Remover el rectángulo de la posición anterior
                    grid.getChildren().remove(draggedRectangle);

                    // Añadir el rectángulo a la nueva posición con colspan y rowspan de 2
                    grid.add(draggedRectangle, targetColumn, targetRow, 2, 2);

                    // Actualizar la lógica del juego
                    juego.moverBloque(new Coordenada(draggedRow, draggedColumn), new Coordenada(targetRow, targetColumn));

                    // Reconstruir la grilla para reflejar cambios
                    construirGrilla();
                }

                draggedRectangle = null;
            }
        });
    }
    private void agregarEventosDragAndDrop(Rectangle rectangulo, Grilla grilla, int filaInicial, int colInicial, Bloque bloque) {

        rectangulo.setOnMousePressed(mouseEvent -> {
            draggedRectangle = rectangulo;
            draggedColumn = colInicial;
            draggedRow = filaInicial;
        });
        rectangulo.setOnDragDetected(mouseEvent -> {
            draggedRectangle = rectangulo;
            draggedColumn = colInicial;
            draggedRow = filaInicial;
            System.out.println("HICE CLICK Y ARRASTRO");
            mouseEvent.consume();
        });

        rectangulo.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() != rectangulo && dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
            System.out.println("pasa por encima de otro nodo ");
        });

        rectangulo.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                // Mover el bloque a la nueva posición
                grid.getChildren().remove(draggedRectangle); // Eliminar el rectángulo arrastrado
                grid.add(draggedRectangle, colInicial, filaInicial); // Colocar el rectángulo arrastrado en la nueva celda
                success = true;
            }


            // Marcar el evento como consumido
            dragEvent.setDropCompleted(success);
            System.out.println("el usuario suelta el objeto arrastrado.");
            dragEvent.consume();
        });
    }



    // Agregar emisores a la grilla
    private void agregarEmisores(List<Laser> emisores) {
        for (Laser emisor : emisores) {
            Circle circuloEmisor = new Circle(5, Color.RED);
            grid.add(circuloEmisor, emisor.getColInicial(), emisor.getFilInicial());
            circuloEmisor.setTranslateY(-4);
            circuloEmisor.setTranslateX(-5);
            GridPane.setHalignment(circuloEmisor, HPos.CENTER);
            GridPane.setValignment(circuloEmisor, VPos.CENTER);
        }
    }

    // Agregar objetivos a la grilla
    private void agregarObjetivos(List<Objetivo> objetivos) {
        for (Objetivo objetivo : objetivos) {
            Circle circuloObjetivo = new Circle(5);

            if (objetivo.isAlcanzado()) {
                circuloObjetivo.setFill(Color.RED);
            } else {
                circuloObjetivo.setFill(Color.WHITE);
                circuloObjetivo.setStroke(Color.RED);
                circuloObjetivo.setStrokeWidth(2);
            }

            grid.add(circuloObjetivo, objetivo.getColumna(), objetivo.getFila());
            circuloObjetivo.setTranslateY(-5);
            circuloObjetivo.setTranslateX(-5);
            GridPane.setHalignment(circuloObjetivo, HPos.CENTER);
            GridPane.setValignment(circuloObjetivo, VPos.CENTER);
        }
    }

    // Dibujar trayectos de los láseres en la capa de láser
    private void dibujarTrayectosLaser(List<LaserTrayecto> trayectosLaser) {
        for (LaserTrayecto trayecto : trayectosLaser) {
            Coordenada inicio = trayecto.getInicio();
            Coordenada fin = trayecto.getFin();

            double startX = inicio.getColumna() * 25;
            double startY = inicio.getFila() * 25;
            double endX = fin.getColumna() * 25;
            double endY = fin.getFila() * 25;

            Line laserLine = new Line(startX, startY, endX, endY);
            laserLine.setStroke(Color.RED);
            laserLine.setStrokeWidth(2);

            laserLayer.getChildren().add(laserLine);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
