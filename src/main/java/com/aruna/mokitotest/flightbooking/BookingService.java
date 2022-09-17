package com.aruna.mokitotest.flightbooking;

import com.aruna.mokitotest.thirdparty.model.FlightDetail;
import com.aruna.mokitotest.thirdparty.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    @Autowired
    private FlightService flightService;

    public List<FlightDetail> getCheapFlights (double maxAmount, String city) {

        List<FlightDetail> flightDetailLit = flightService.getFlights(city);
        List<FlightDetail> filteredFlightDetailLit =flightDetailLit.stream()
                .filter(flightDetail -> flightDetail.getPrice() <= maxAmount)
                .collect(Collectors.toList());
        return filteredFlightDetailLit;
    }

}
