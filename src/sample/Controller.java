package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.model.Message;
import sample.threads.Sqz;
import sample.threads.UdpSender;
import sample.threads.UdpServer;

import java.io.IOException;
import java.net.InetAddress;

public class Controller {


    public static XYChart.Series<String, Number> series;
    public static XYChart.Series<Number, Number> seriesSqo;
    @FXML
    public Label obs;

    @FXML
    private TextField ipARM;
    @FXML
    private TextField portARM;
    @FXML
    private TextField portVideo;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private BarChart<String, Number> BarChart;

    @FXML
    private javafx.scene.chart.LineChart<Number, Number> AreaChart;


    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public Controller() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {

        series = new XYChart.Series<>();
        BarChart.setAnimated(false);
        BarChart.getData().add(series);

        seriesSqo = new XYChart.Series<>();
        AreaChart.getData().add(seriesSqo);

    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    /**
     * Создание сообщения
     */
    private void makeMessage(Message temple) {

        Thread thread = null;
        try {
            thread = new Thread(new UdpSender(mainApp, InetAddress.getByName(ipARM.getText()), Integer.parseInt(portARM.getText()), temple));
        } catch (IOException e) {
            System.out.println("Ошибка ip адреса");
            e.printStackTrace();
        }
        thread.setDaemon(true);
        thread.start();
        System.out.println("Создан дополнительный поток отправки " +thread);

    }

    /**
     * Вызывается при клике по видео кнопке
     */
    @FXML
    private void handleVideoButton() {
        //Если поток уже есть, то не запускаем другой
        if(!mainApp.isStop()){return;}
        mainApp.setStop(false);
        Thread thread = new Thread(new UdpServer(mainApp, Integer.parseInt(portVideo.getText())), "Поток получения видео");
        thread.setDaemon(true);
        thread.start();

        Thread thread2 = new Thread(new Sqz(mainApp), "Поток SQo");
        thread2.setDaemon(true);
        thread2.start();
        System.out.println("Создан дополнительный поток приема " +thread);

    }

    @FXML
    private void setStop() {
        mainApp.setStop(true);
    }


}
