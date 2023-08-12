package com.aruna.mokitotest.flightbooking;

import com.aruna.mokitotest.thirdparty.model.FlightDetail;

import static org.assertj.core.api.Assertions.*;

import com.aruna.mokitotest.thirdparty.service.FlightServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.AdditionalMatchers.*;
import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private FlightServiceImpl flightService;

    @Test
    public void test_1() {
        List<FlightDetail> londonMockFlights = List.of(new FlightDetail("MockedSriLankan", 100),
                new FlightDetail("MockedQatarAirways", 200),
                new FlightDetail("MockedEmirates", 200),
                new FlightDetail("MockedSingaporeAirline", 500));

        List<FlightDetail> parisMockFlights = List.of(new FlightDetail("MockedSriLankan", 200),
                new FlightDetail("MockedQatarAirways", 400),
                new FlightDetail("MockedEmirates", 400),
                new FlightDetail("MockedSingaporeAirline", 1000));

        ////////////////////////////
        //          stubbing          ///
        ////////////////////////////

        when(flightService.getFlights("London")).thenReturn(londonMockFlights);
        when(flightService.getFlights("Paris")).thenReturn(parisMockFlights);
        when(flightService.getFlights(("Melbourn"))).thenAnswer(invocationOnMock -> getDummyFlights(invocationOnMock.getArgument(0)));

        List<FlightDetail> flights = bookingService.getCheapFlights(250, "Paris");
        assertThat(flights).hasSize(1);

        flights = bookingService.getCheapFlights(250, "London");
        assertThat(flights).hasSize(3);

        flights = bookingService.getCheapFlights(200, "Melbourn");
        assertThat(flights).hasSize(1);
        assertThat(flights.get(0).getPrice() == 150);

    }

    @Test
    public void test_2() {
        // callRealMethod
        when(flightService.getFlights("London")).thenAnswer(InvocationOnMock::callRealMethod);
        List<FlightDetail> flights = bookingService.getCheapFlights(250, "London");
        assertThat(flights).hasSize(2);

    }

    @Test
    public void test_3() {
        // anyString() // anyInt() ....
        when(flightService.getFlights(anyString())).thenReturn(getDummyFlights("dummy"));
        List<FlightDetail> flights = bookingService.getCheapFlights(250, "London");
        flights = flights;
        assertThat(flights).hasSize(1);
    }

    @Test
    public void test_4() {
        // anyString() with eq() :
        // if anyString() is there then any other fixed value parameters should be passed with eq() any other form of a matcher, otherwise fails
        when(flightService.getFlightsWithDestination(eq("Stockholm"), anyString())).thenAnswer(invocationOnMock -> getDummyFlights("Stockholm"));
        List<FlightDetail> flights = bookingService.getCheapFlightsWithDestination(250, "Stockholm", "London");
        assertThat(flights).hasSize(1);
    }

    @Test
    public void test_5() {
        // ArgumentMatcher<T>
        // example argThat(ArgumentMatcher<T> matcher)
        when(flightService.getFlightsWithDestination(argThat(s -> s.startsWith("St")), anyString())).thenAnswer(invocationOnMock -> getDummyFlights("Stockholm"));
        List<FlightDetail> flights = bookingService.getCheapFlightsWithDestination(250, "Stockholm", "London");
        assertThat(flights).hasSize(1);

    }

    @Test
    public void test_6() {
        // Combining Matchers or() / not () / and ()
        // verify
        when(flightService.getFlightsWithDestination(or(eq("Stockholm"), contains("Amst")), anyString())).thenAnswer(invocationOnMock -> getDummyFlights("Stockholm"));
        List<FlightDetail> flights = bookingService.getCheapFlightsWithDestination(250, "Stockholm", "London");
        assertThat(flights).hasSize(1);
        verify(flightService, times(1)).getFlightsWithDestination(or(eq("Stockholm"), contains("Amst")), anyString());

    }

    private List<FlightDetail> getDummyFlights(String city) {
        return List.of(new FlightDetail(city + "Airline", 150));

    }
}
