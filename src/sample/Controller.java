package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.InetAddress;

public class Controller {

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
        // Инициализация таблицы адресатов с двумя столбцами.


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
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleSendButton() {

        /*message temple =new message(header.getText(), func.getText(),reserv.getText(),error.getText(),data.getText(),null);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable sender= null;
                try {
                    sender = new UdpSender(mainApp, InetAddress.getByName(ipARM.getText()),Integer.parseInt(portARM.getText()),temple);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Platform.runLater(sender);
            }
        });

        thread.setDaemon(true);
        thread.start();
        System.out.println("Создан дополнительный поток отправки " +
                thread);
*/
    }

    @FXML
    private void handleVideoButton() {

       /* mainApp.setStop(false);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable resiever = new UdpServer(mainApp,Integer.parseInt(portVideo.getText()));
                Thread thread2=new Thread(resiever,"поток");
                thread2.setDaemon(true);
                thread2.start();
            }
        });

        thread.setDaemon(true);
        thread.start();
        System.out.println("Создан дополнительный поток приема " +
                thread);

*/
    }

}
