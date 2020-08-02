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
import sample.model.Message;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private Parent root;
    private Controller controller;
    private volatile boolean stop=true;

    //Один видеокадр
    private ObservableList<Message> messageObsList = FXCollections.observableArrayList();
    //Один видеокадр
    private ObservableList<Short> video = FXCollections.observableArrayList();
    //Массив кадров
    private ObservableList<ObservableList<Short>> videoMap=FXCollections.observableArrayList();



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

    public ObservableList<ObservableList<Short>> getVideoMap() {
        return videoMap;
    }

    public void setVideoMap(ObservableList<ObservableList<Short>> videoMap) {
        this.videoMap = videoMap;
    }

    public ObservableList<Message> getMessageObsList() {
        return messageObsList;
    }

    public void setMessageObsList(ObservableList<Message> messageObsList) {
        this.messageObsList = messageObsList;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
