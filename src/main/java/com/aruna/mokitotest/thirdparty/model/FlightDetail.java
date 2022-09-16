package com.aruna.mokitotest.thirdparty.model;

public class FlightDetail {
    String airLine;
    double price;

    public FlightDetail(String airLine, double price) {
        this.airLine = airLine;
        this.price = price;
    }

    public String getAirLine() {
        return airLine;
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
