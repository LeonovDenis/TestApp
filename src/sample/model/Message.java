package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    private  StringProperty header;
    private  StringProperty func;
    private  StringProperty reserv;
    private  StringProperty error;
    private  StringProperty data;
    private  StringProperty otvet;

    public String getOtvet() {
        return otvet.get();
    }

    public StringProperty otvetProperty() {
        return otvet;
    }

    public void setOtvet(String otvet) {
        this.otvet.set(otvet);
    }

    public String getFull() {
        return full.get();
    }

    public StringProperty fullProperty() {
        return full;
    }

    public void setFull(String full) {
        this.full.set(full);
    }

    private  StringProperty full;

    public Message(){
        this (null,null,null,null,null,null);
    }

    @Override
    public String toString() {
        return "message{" +
                "header=" + header +
                ", func=" + func +
                ", reserv=" + reserv +
                ", error=" + error +
                ", data=" + data +
                ", otvet=" + otvet +
                ", full=" + full +
                '}';
    }

    public Message(String header, String func, String reserv, String error, String data, String otvet){
        this.header = new SimpleStringProperty(header);
        this.func = new SimpleStringProperty(func);
        this.reserv = new SimpleStringProperty(reserv);
        this.error = new SimpleStringProperty(error);
        this.data = new SimpleStringProperty(data);
        this.otvet=new SimpleStringProperty(otvet);
        this.full=new SimpleStringProperty(header+func+reserv+error+data);
    }
    public Message(String msg){
        this (msg.substring(0,8),msg.substring(8,10),msg.substring(10,14),msg.substring(14,16),msg.length()<=16?null:msg.substring(16),null);
    }

    public String getHeader() {
        return header.get();
    }

    public StringProperty headerProperty() {
        return header;
    }

    public void setHeader(String header) {
        this.header.set(header);
    }

    public String getFunc() {
        return func.get();
    }

    public StringProperty funcProperty() {
        return func;
    }

    public void setFunc(String func) {
        this.func.set(func);
    }

    public String getReserv() {
        return reserv.get();
    }

    public StringProperty reservProperty() {
        return reserv;
    }

    public void setReserv(String reserv) {
        this.reserv.set(reserv);
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public String getData() {
        return data.get();
    }

    public StringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }
}
