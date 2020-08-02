package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private Parent root;
    private Controller controller;

    //Один видеокадр
    private ObservableList<Short> video = FXCollections.observableArrayList();
    //Массив кадров
    private ObservableMap<Number, ObservableList<Short>> stack = FXCollections.observableHashMap();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Test App");

        initRootLayout();
        initOnClose();


    }

    /**
     * Загрузка корневой панели окна.
     * Инициализация контроллера.
     */
    private void initRootLayout() {
        try {

            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sample.fxml"));
            root = (AnchorPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Инициализация параметров закрытия окна
     */
    private void initOnClose() {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public ObservableList<Short> getVideo() {
        return video;
    }

    public void setVideo(ObservableList<Short> video) {
        this.video = video;
    }

    public ObservableMap<Number, ObservableList<Short>> getStack() {
        return stack;
    }

    public void setStack(ObservableMap<Number, ObservableList<Short>> stack) {
        this.stack = stack;
    }
}
