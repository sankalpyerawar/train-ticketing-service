package ticketing.pricing;

import ticketing.domain.CoachType;

import java.math.BigDecimal;

public class FixPerStationTicketPricing implements TicketPricing {
  @Override
  public BigDecimal getTicketPrice(int noOfTickets, CoachType coachType, int noOfStops) {
    if (noOfTickets <= 0 || noOfStops < 0) {
      throw new IllegalArgumentException("Number of tickets and stops must be positive.");
    }
    if (noOfStops == 0) {
      return BigDecimal.ZERO;
    }
    BigDecimal pricePerStop = coachType.getPricePerStop();
    return pricePerStop.multiply(new BigDecimal(noOfTickets)).multiply( new BigDecimal(noOfStops));
  }
}
