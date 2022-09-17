package com.aruna.mokitotest.flightbooking;

import com.aruna.mokitotest.thirdparty.model.FlightDetail;
import com.aruna.mokitotest.thirdparty.service.FlightService;
import static org.assertj.core.api.Assertions.*;

import com.aruna.mokitotest.thirdparty.service.FlightServiceImpl;
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
    private FlightServiceImpl flightService;

    @Test
    public void testGetCheapFlights () {

        List<FlightDetail> londonMockFlights = List.of(new FlightDetail("MockedSriLankan", 100),
                new FlightDetail("MockedQatarAirways", 200),
                new FlightDetail("MockedEmirates", 200),
                new FlightDetail("MockedSingaporeAirline", 500));

        List<FlightDetail> parisMockFlights = List.of(new FlightDetail("MockedSriLankan", 200),
                new FlightDetail("MockedQatarAirways", 400),
                new FlightDetail("MockedEmirates", 400),
                new FlightDetail("MockedSingaporeAirline", 1000));

        when(flightService.getFlights("London")).thenReturn(londonMockFlights);
        when(flightService.getFlights("Paris")).thenReturn(parisMockFlights);

        List<FlightDetail> parisCheapFlights = bookingService.getCheapFlights(250, "Paris");
        assertThat(parisCheapFlights).hasSize(1);

        List<FlightDetail> londonCheapFlights = bookingService.getCheapFlights(250, "London");
        assertThat(londonCheapFlights).hasSize(3);
    }
}
