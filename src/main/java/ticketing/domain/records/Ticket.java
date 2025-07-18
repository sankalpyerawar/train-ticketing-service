package ticketing.domain.records;

import ticketing.domain.CoachType;

import java.math.BigDecimal;

public record Ticket(
  String trainNumber,
  Station startStation,
  Station endStation,
  CoachType coachType,
  int noOfTickets,
  BigDecimal totalPrice
) {}
