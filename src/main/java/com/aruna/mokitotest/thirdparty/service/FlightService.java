package com.aruna.mokitotest.thirdparty.service;

import com.aruna.mokitotest.thirdparty.model.FlightDetail;

import java.util.List;

public interface FlightService {
    public List<FlightDetail> getFlights (String city);
    public List<FlightDetail> getFlightsWithDestination(String from, String to);
}
