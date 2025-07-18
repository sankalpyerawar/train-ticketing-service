package ticketing.pricing;

import ticketing.domain.CoachType;

import java.math.BigDecimal;

public interface TicketPricing {
  BigDecimal getTicketPrice(int noOfTickets, CoachType coachType, int noOfStops);
}
