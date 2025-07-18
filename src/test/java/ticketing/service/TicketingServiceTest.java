package ticketing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ticketing.domain.CoachType;
import ticketing.domain.Train;
import ticketing.domain.records.Station;
import ticketing.domain.records.Ticket;
import ticketing.pricing.TicketPricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketingServiceTest {
  @Mock
  private Train mockTrain;
  @Mock
  private TicketPricing mockTicketPricingService;

  private TicketingService ticketingService;

  private static final String TRAIN_NUMBER = "12345";
  private static final Station START_STATION = new Station("Mumbai");
  private static final Station END_STATION = new Station("Pune");
  private static final CoachType COACH_TYPE = CoachType.SLEEPER;

  @BeforeEach
  void setUp() {
    Map<String, Train> trains = new HashMap<>();
    trains.put(TRAIN_NUMBER, mockTrain);
    ticketingService = new TicketingService(trains, mockTicketPricingService);
  }

  @Test
  void shouldReturnTicketForValidInput() {
    int noOfTickets = 2;
    int expectedStops = 4;
    BigDecimal expectedPrice = new BigDecimal("320.00");

    when(mockTrain.isCoachSupported(COACH_TYPE)).thenReturn(true);
    when(mockTrain.getStopsBetweenStations(START_STATION, END_STATION)).thenReturn(expectedStops);
    when(mockTicketPricingService.getTicketPrice(noOfTickets, COACH_TYPE, expectedStops)).thenReturn(expectedPrice);

    Ticket ticket = ticketingService.getTicket(START_STATION, END_STATION, noOfTickets, TRAIN_NUMBER, COACH_TYPE);

    assertNotNull(ticket);
    assertEquals(TRAIN_NUMBER, ticket.trainNumber());
    assertEquals(noOfTickets, ticket.noOfTickets());
    assertEquals(expectedPrice,ticket.totalPrice());
  }

  @Test
  void shouldThrowExceptionWhenTrainIsNotFound() {
    String invalidTrainNumber = "99999";

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      ticketingService.getTicket(START_STATION, END_STATION, 1, invalidTrainNumber, COACH_TYPE);
    });

    assertEquals("Train number " + invalidTrainNumber + " not found.", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenCoachTypeIsNotSupported() {
    when(mockTrain.isCoachSupported(COACH_TYPE)).thenReturn(false);

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      ticketingService.getTicket(START_STATION, END_STATION, 1, TRAIN_NUMBER, COACH_TYPE);
    });

    assertEquals("Coach type " + COACH_TYPE + " is not available on this train.", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWheRouteIsInvalid() {
    Station invalidStart = new Station("Pune");
    Station invalidEnd = new Station("Mumbai");
    String expectedErrorMessage = "Start station must be before the end station on the route.";

    when(mockTrain.isCoachSupported(COACH_TYPE)).thenReturn(true);
    when(mockTrain.getStopsBetweenStations(invalidStart, invalidEnd))
      .thenThrow(new IllegalArgumentException(expectedErrorMessage));

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      ticketingService.getTicket(invalidStart, invalidEnd, 1, TRAIN_NUMBER, COACH_TYPE);
    });

    assertEquals(expectedErrorMessage, exception.getMessage());
  }
}