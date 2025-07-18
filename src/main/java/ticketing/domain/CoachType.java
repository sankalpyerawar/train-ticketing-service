package ticketing.domain;

import java.math.BigDecimal;

public enum CoachType {
  GENERAL(new BigDecimal("20.0")),
  SLEEPER(new BigDecimal("40.0"));

  private final BigDecimal pricePerStop;

  CoachType(BigDecimal pricePerStop) {
    this.pricePerStop = pricePerStop;
  }

  public BigDecimal getPricePerStop() {
    return this.pricePerStop;
  }
}
