package com.aruna.mokitotest.thirdparty.service;

import com.aruna.mokitotest.thirdparty.model.FlightDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl  implements FlightService{

    @Override
    public List<FlightDetail> getFlights() {
        List<FlightDetail> flightDetails = List.of(new FlightDetail("SriLankan", 100),
                new FlightDetail("QuatarAirways", 200),
                new FlightDetail("Emirates", 300),
                new FlightDetail("SingaporeAirline", 500));
        return flightDetails;
    }
}
