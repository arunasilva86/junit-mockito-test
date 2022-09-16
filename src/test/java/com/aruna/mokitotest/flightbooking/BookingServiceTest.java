package com.aruna.mokitotest.flightbooking;

import com.aruna.mokitotest.thirdparty.model.FlightDetail;
import com.aruna.mokitotest.thirdparty.service.FlightService;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private FlightService flightService;

    @Test
    public void testGetCheapFlights () {

        List<FlightDetail> mockedFlightDetails = List.of(new FlightDetail("MockedSriLankan", 100),
                new FlightDetail("MockedQatarAirways", 200),
                new FlightDetail("MockedEmirates", 300),
                new FlightDetail("MockedSingaporeAirline", 500));
        when(flightService.getFlights()).thenReturn(mockedFlightDetails);

        List<FlightDetail> cheapFlightDetails = bookingService.getCheapFlights(250);
        assertThat(cheapFlightDetails).isNotNull().isNotEmpty();
    }
}
