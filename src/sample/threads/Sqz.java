package sample.threads;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.XYChart;
import sample.Controller;
import sample.MainApp;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Sqz implements Runnable {
    private final MainApp mainApp;
    private final Map<Integer, Integer> SQ = new TreeMap<>();

    public Sqz(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void run() {
        while (!mainApp.isStop()) {
            ObservableList<ObservableList<Short>> videoMap = mainApp.getVideoMap();
            for (int i = 0; i < videoMap.size(); i++) {
                ObservableList<Short> shorts = videoMap.get(i);
                SortedList<Short> sorted = shorts.sorted();
                for (int j = 0; j < sorted.size(); j++) {
                    Short aShort = sorted.get(j);
                    if(aShort!=null) {
                        if (SQ.containsKey(Integer.valueOf(aShort))) {
                            SQ.replace(Integer.valueOf(aShort), SQ.get(Integer.valueOf(aShort)), SQ.get(Integer.valueOf(aShort)) + 1);
                        } else {
                            SQ.put(Integer.valueOf(aShort), 1);
                        }
                    }

                }

            }
            XYChart.Series<Number, Number> seriesS = new XYChart.Series<>();


            for (Map.Entry<Integer, Integer> entry : SQ.entrySet()) {
                if(entry.getKey()==null){continue;}
                seriesS.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
                Thread r = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Добавка ско");
                        //Очистка серии
                        //Добавка данных в серию

                        Controller.seriesSqo.getData().setAll(seriesS.getData());
                        mainApp.getController().obs.setText(String.valueOf(videoMap.size()));
                        System.out.println("Добавка ско - добавлено");
                    }
                });
                //Запуск потока в главном потоке GUI
                Platform.runLater(r);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("График SQO перерисован");
            }
        }
    }
