package ticketing.service;

import ticketing.domain.CoachType;
import ticketing.domain.Train;
import ticketing.domain.records.Station;
import ticketing.domain.records.Ticket;
import ticketing.pricing.TicketPricing;

import java.math.BigDecimal;
import java.util.Map;

public class TicketingService {
  private final Map<String, Train> trains;
  private final TicketPricing ticketPricingService;

  public TicketingService(Map<String, Train> trains, TicketPricing ticketPricingService) {
    this.trains = trains;
    this.ticketPricingService = ticketPricingService;
  }

  public Ticket getTicket(
    Station startStation,
    Station endStation,
    int noOfTickets,
    String trainNumber,
    CoachType coachType
  ) {
    Train train = trains.get(trainNumber);
    if (train == null) {
      throw new IllegalArgumentException("Train number " + trainNumber + " not found.");
    }

    if (!train.isCoachSupported(coachType)) {
      throw new IllegalArgumentException("Coach type " + coachType + " is not available on this train.");
    }

    int noOfStops = train.getStopsBetweenStations(startStation, endStation);

    BigDecimal totalPrice = ticketPricingService.getTicketPrice(noOfTickets, coachType, noOfStops);

    return new Ticket(
      trainNumber,
      startStation,
      endStation,
      coachType,
      noOfTickets,
      totalPrice
    );
  }
}
