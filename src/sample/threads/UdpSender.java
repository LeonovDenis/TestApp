package sample.threads;

import org.apache.commons.codec.binary.Hex;
import sample.MainApp;
import sample.model.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UdpSender implements Runnable {

    private InetAddress addr;

    private int port;

    private Message message;

    private MainApp mainApp;

    /**
     * @param mainApp
     * @param addr
     * @param port
     * @param message
     * @throws IOException
     */
    public UdpSender(MainApp mainApp, InetAddress addr, int port, Message message) throws IOException {
        this.addr = addr;
        this.port = port;
        this.message = message;
        this.mainApp = mainApp;
    }


    @Override
    public void run() {

        byte[] receiveData = new byte[248];

        try {
            System.out.println("Стар потока отправки : " + Thread.currentThread() + "/");
            //Инициализация датаграмм сокета
            DatagramSocket clientSocket = new DatagramSocket();
            //Текстовое представление строки сообщения в Hex кодировке
            String sentence = message.getFull();
            //Декодирование строки в набор байт с Hex кодировкой
            byte[] sendData = Hex.decodeHex(sentence);
            //Формирование пакета байт
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, addr, port);
            //Отправка пакета клиенту
            clientSocket.send(sendPacket);

            //Инициализация датаграм пакета ответного сообщения 
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                //Задание предельного времени ожидания ответа
                clientSocket.setSoTimeout(300);

                //ожидание ответа
                clientSocket.receive(receivePacket);
                //массив байт ответного сообщения
                byte[] data = receivePacket.getData();
                //декодирование массива в строквое представление с Hex кодировкой
                String receivedSentence = new String(Hex.encodeHex(data));
                //добавление полученного ответа в сообщение
                message.setOtvet(receivedSentence);

            /**
             * если время ожидания вышло
             */
            } catch (SocketTimeoutException e) {


                System.out.println("Истекло время ожидания, прием данных закончен");
                message.setOtvet("Нет ответа");


            }

        } catch (Exception e) {
            System.out.println("Оххх1");
            e.printStackTrace();
            message.setOtvet("Получили ошибку");

        }
        finally {
            mainApp.getMessageObsList().add(message);
        }
    }


}

