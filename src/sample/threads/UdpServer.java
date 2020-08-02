package sample.threads;

import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import org.apache.commons.codec.binary.Hex;
import sample.Controller;
import sample.MainApp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer implements Runnable {

    private final int port;
    private final MainApp mainApp;
    byte[] receiveData = new byte[1154];
    private Short[] videodata = new Short[576];

    public UdpServer(MainApp mainApp, int port) {
        this.port = port;
        this.mainApp = mainApp;
    }

    @Override
    public void run() {
        DatagramSocket serverSocket = null;
        DatagramPacket receivePacket = null;

        try {
            //Открытие датаграмм сокета
            serverSocket = new DatagramSocket(port);
            System.out.println("Запуск серверного потока: " + Thread.currentThread() + "/");
            /**
             * Слушаем канал пока не будет команды на стоп
             */
            while (!mainApp.isStop()) {
                //Инициализация датаграм пакета
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                //получаем пакет
                serverSocket.receive(receivePacket);

                byte[] data = receivePacket.getData();
                //Строковое представление сообщения в Hex кодировке
                String recieveSentence = new String(Hex.encodeHex(data));
                System.out.println("RECEIVED: " + recieveSentence);
                //Проверка заголовка видео пакета
                if (recieveSentence.toUpperCase().startsWith("3FFF")) {
                    videodata = byteToShort(data);
                    //Запись одного видеокадра
                    mainApp.getVideo().setAll(videodata);
                    //Запись массива видеокадров. Не более 100

                    if (mainApp.getVideoMap().size() >= 100) {
                        mainApp.getVideoMap().remove(0);
                    }
                    mainApp.getVideoMap().add(mainApp.getVideo());

                } else {
                    System.out.println("Пакет пришел. Заголовок не тот.");
                }
                //Поток добавляющий данные в серию диаграммы
                Thread r = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Добавка видео");
                        //Очистка серии
                        Controller.series.getData().clear();
                        //Добавка данных в серию
                        for (int i = 0; i < 288; i++) {
                            //Если пакет с меньшим кол-вом данных, то значения null заменяются на 0
                            Controller.series.getData().add(new XYChart.Data<>(String.valueOf(i + 1), videodata[i] != null ? videodata[i] : 0));
                        }
                    }
                });
                //Запуск потока в главном потоке GUI
                Platform.runLater(r);
                System.out.println("График перерисован");

            }

        } catch (Exception e) {
            System.out.println("Оххх");
            e.printStackTrace();

        } finally {
            //Закрытие сокета
            if (serverSocket != null) {
                serverSocket.close();
                System.out.println("Сервер сокет закрыт.");
            }
        }

    }

    /**
     * Значения закодированы двумя байтами.
     * Перевод массива байт в массиы Short и откидываем заголовок
     *
     * @param b массив байт сообщения
     * @return
     */
    public Short[] byteToShort(byte[] b) {
        Short[] x = new Short[(b.length) / 2];
        int k = 0;
        for (int i = 2; i < b.length; i = i + 2) {
            x[k++] = (short) (128 * ((byte) (b[i] & (byte) 0x7f)) + b[i + 1]);
            System.out.println("Значение:" + k + " --> " + x[k - 1]);
        }
        return x;
    }
}

