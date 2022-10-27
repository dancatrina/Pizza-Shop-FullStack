package com.ordersystem.pizzaorder.exception.error;

import java.text.SimpleDateFormat;

public class ErrorMessage {

    private Integer statusRequest;
    private String message;
    private String timeStamp;

    public ErrorMessage(Integer statusRequest, String message) {
        this.statusRequest = statusRequest;
        this.message = message;

        timeStamp = SimpleDateFormat.getDateInstance().format(System.currentTimeMillis());
    }

    public Integer getStatusRequest() {return statusRequest;}
    public String getMessage() {return message;}
    public String getTimeStamp() {return timeStamp;}

    public void setStatusRequest(Integer statusRequest) {this.statusRequest = statusRequest;}
    public void setMessage(String message) {this.message = message;}
    public void setTimeStamp(String timeStamp) {this.timeStamp = timeStamp;}
}
