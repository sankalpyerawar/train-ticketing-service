package pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ticketing.domain.CoachType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FixPerStationTicketPricingTest {

  private FixPerStationTicketPricing pricing;

  @BeforeEach
  void setUp() {
    pricing = new FixPerStationTicketPricing();
  }

  @Test
  void shouldGetTicketPriceForMultipleTicketsAndMultipleStops() {
    assertEquals(new BigDecimal("200.0"), pricing.getTicketPrice(2, CoachType.GENERAL, 5));
  }

  @Test
  void shouldReturnZeroForNoStops() {
    assertEquals(BigDecimal.ZERO, pricing.getTicketPrice(2, CoachType.GENERAL, 0));
  }

  @Test
  void shouldThrowExceptionIfNumberOfStopsIsNegative() {
    assertThrows(IllegalArgumentException.class, () ->
      pricing.getTicketPrice(2, CoachType.GENERAL, -1)
    );
  }

  @Test
  void shouldThrowExceptionIfNumberOfPassengersAreZero() {
    assertThrows(IllegalArgumentException.class, () ->
      pricing.getTicketPrice(0, CoachType.GENERAL, 2)
    );
  }

  @Test
  void shouldThrowExceptionIfNumberOfPassengersIsNegative() {
    assertThrows(IllegalArgumentException.class, () ->
      pricing.getTicketPrice(0, CoachType.GENERAL, 2)
    );
  }
}