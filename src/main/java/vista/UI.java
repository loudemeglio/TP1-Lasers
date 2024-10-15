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

    private Coordenada bloqueOrigen;
    private Coordenada bloqueDestino;
    private boolean origenSeleccionado = false;
    private boolean nivelCompleto = false; // Variable para controlar si el nivel está completo
    private boolean reiniciar;


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

        nivelCompleto = nivel.verificarNivelCompletado();
        if (nivelCompleto) {
            cambiarFondoGrillaColorClaro();
            deshabilitarInteracciones(); // Deshabilita la interacción

        } else {
            resetearFondoGrilla();
        }
    }

    private void deshabilitarInteracciones() {
        for (Node node : grid.getChildren()) {
            node.setDisable(true); // Deshabilita todos los nodos en la grilla
        }
    }

    private void cambiarFondoGrillaColorClaro() {
        // Cambiar el fondo de la grilla a un verde claro
        grid.setStyle("-fx-background-color: lightgreen;");
    }

    private void resetearFondoGrilla() {
        // Resetear el fondo de la grilla a su color original
        grid.setStyle("-fx-background-color: transparent;"); // o el color original que tenga
    }

    private List<Bloque> obtenerBloquesDesdeGrilla(Grilla grilla) {
        List<Bloque> bloques = new ArrayList<>();

        // Recorre la grilla completa
        for (int fila = 0; fila < grilla.getFilas(); fila +=2) {
            for (int col = 0; col < grilla.getColumnas(); col += 2) {
                Celda celda = grilla.getCelda(fila, col);
                Bloque bloque = (celda != null) ? celda.getTipoBloque() : null;
                // Agregar el bloque (o null) a la lista
                bloques.add(bloque);
            }
        }
        return bloques;
    }



    // Configurar la grilla con las celdas
    private void configurarGrilla(Grilla grilla) {
        List<Bloque> bloquesTipos = obtenerBloquesDesdeGrilla(grilla);
        int cont = 0;
        for (int fila = 0; fila < grilla.getFilasOriginales(); fila++) {
            for (int col = 0; col < grilla.getColOriginales(); col++) {
                Celda celda = grilla.getCelda(fila, col); // Cambié fila * 2 por fila
                Bloque bloque = bloquesTipos.get(cont);
                cont++;

                Rectangle rectangulo;
                if (bloque != null) {
                    rectangulo = VistaBloque.crearRepresentacion(bloque);
                } else {
                    rectangulo = new Rectangle(50, 50, Color.WHITE);
                    rectangulo.setStroke(Color.BLACK);
                    rectangulo.setStrokeWidth(1);
                }

                // Solo añade el rectángulo si no está ya presente
                if (!rectangulos.contains(rectangulo)) {
                    grid.add(rectangulo, col, fila); // Cambié a solo col y fila
                    rectangulos.add(rectangulo);
                }
            }
        }
        System.out.println("LA lista de rectangulos tiene: " + rectangulos.size());

        for (Rectangle r : rectangulos) {
            r.setOnMouseClicked(event -> {
                Integer posX = GridPane.getColumnIndex(r);  // Obtener columna
                System.out.println("COL index " + posX);
                Integer posY = GridPane.getRowIndex(r);     // Obtener fila
                System.out.println("FIL index " + posY);

                if (posX == null || posY == null) {
                    // En caso de que las coordenadas no estén asignadas
                    System.out.println("No se pudieron obtener las coordenadas de la celda");
                    return;
                }

                if (!origenSeleccionado) {
                    // Selecciona el origen
                    bloqueOrigen = new Coordenada(posX, posY);
                    origenSeleccionado = true;
                    System.out.println("Celda origen seleccionada: " + bloqueOrigen);
                } else {
                    // Selecciona el destino
                    bloqueDestino = new Coordenada(posX, posY);
                    origenSeleccionado = false; // Resetea para la siguiente interacción

                    System.out.println("Celda destino seleccionada: " + bloqueDestino);

                    // Lógica para mover el bloque de origen a destino
                    if (juego.moverBloque(bloqueOrigen, bloqueDestino)) {
                        System.out.println("Movimiento realizado de " + bloqueOrigen + " a " + bloqueDestino);
                        construirGrilla(); // Actualiza la grilla después de mover
                    } else {
                        System.out.println("Movimiento inválido");
                    }
                }
            });
        }

    }



    // Agregar emisores a la grilla
    // Agregar emisores a la grilla
    private void agregarEmisores(List<Laser> emisores) {
        for (Laser emisor : emisores) {
            Circle circuloEmisor = new Circle(5, Color.RED);

            // Obtener las coordenadas del emisor
            int colEmisor = emisor.getColInicial();
            int filEmisor = emisor.getFilInicial();

            // Ajuste de coordenadas para posicionar emisores en los límites de las celdas
            if (colEmisor % 2 == 0 && filEmisor % 2 == 0) {
                // Emisor en la esquina superior izquierda de un bloque
                grid.add(circuloEmisor, colEmisor / 2, filEmisor / 2);
                GridPane.setHalignment(circuloEmisor, HPos.LEFT);
                GridPane.setValignment(circuloEmisor, VPos.TOP);
            } else if (colEmisor % 2 == 1 && filEmisor % 2 == 0) {
                // Emisor en el borde superior (centrado horizontalmente)
                grid.add(circuloEmisor, colEmisor / 2, filEmisor / 2);
                GridPane.setHalignment(circuloEmisor, HPos.CENTER);
                GridPane.setValignment(circuloEmisor, VPos.TOP);
            } else if (colEmisor % 2 == 0 && filEmisor % 2 == 1) {
                // Emisor en el borde izquierdo (centrado verticalmente)
                grid.add(circuloEmisor, colEmisor / 2, filEmisor / 2);
                GridPane.setHalignment(circuloEmisor, HPos.LEFT);
                GridPane.setValignment(circuloEmisor, VPos.CENTER);
            } else {
                // Emisor en el centro de la celda
                grid.add(circuloEmisor, colEmisor / 2, filEmisor / 2);
                GridPane.setHalignment(circuloEmisor, HPos.CENTER);
                GridPane.setValignment(circuloEmisor, VPos.CENTER);
            }

            // Ajuste de traslación si es necesario
            circuloEmisor.setTranslateY(-4);
            circuloEmisor.setTranslateX(-5);
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

            // Obtener las coordenadas del objetivo
            int colObjetivo = objetivo.getColumna();
            int filObjetivo = objetivo.getFila();

            // Ajuste de coordenadas para posicionar objetivos en los límites de las celdas
            if (colObjetivo % 2 == 0 && filObjetivo % 2 == 0) {
                // Objetivo en la esquina superior izquierda de un bloque
                grid.add(circuloObjetivo, colObjetivo / 2, filObjetivo / 2);
                GridPane.setHalignment(circuloObjetivo, HPos.LEFT);
                GridPane.setValignment(circuloObjetivo, VPos.TOP);
            } else if (colObjetivo % 2 == 1 && filObjetivo % 2 == 0) {
                // Objetivo en el borde superior (centrado horizontalmente)
                grid.add(circuloObjetivo, colObjetivo / 2, filObjetivo / 2);
                GridPane.setHalignment(circuloObjetivo, HPos.CENTER);
                GridPane.setValignment(circuloObjetivo, VPos.TOP);
            } else if (colObjetivo % 2 == 0 && filObjetivo % 2 == 1) {
                // Objetivo en el borde izquierdo (centrado verticalmente)
                grid.add(circuloObjetivo, colObjetivo / 2, filObjetivo / 2);
                GridPane.setHalignment(circuloObjetivo, HPos.LEFT);
                GridPane.setValignment(circuloObjetivo, VPos.CENTER);
            } else {
                // Objetivo en el centro de la celda
                grid.add(circuloObjetivo, colObjetivo / 2, filObjetivo / 2);
                GridPane.setHalignment(circuloObjetivo, HPos.CENTER);
                GridPane.setValignment(circuloObjetivo, VPos.CENTER);
            }

            // Ajuste de traslación si es necesario
            circuloObjetivo.setTranslateY(-5);
            circuloObjetivo.setTranslateX(-5);
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
