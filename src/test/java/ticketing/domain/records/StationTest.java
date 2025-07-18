package ticketing.domain.records;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

  @Test
  void shouldCreateStationAndGetStationName() {
    String stationName = "MUMBAI";
    Station station = new Station(stationName);

    assertEquals(stationName, station.stationName());
  }
}