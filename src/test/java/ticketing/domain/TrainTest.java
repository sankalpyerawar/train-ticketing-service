package ticketing.domain;

import org.junit.jupiter.api.Test;
import ticketing.domain.records.Station;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainTest {

  @Test
  void shouldCreateTrainObjectWithTrainNumberRouteAndSupportedCoaches() {
    String trainNumber = "12345";

    Train mumbaiToPuneTrain = mumbaiToPuneTrain(trainNumber);

    assertEquals(trainNumber, mumbaiToPuneTrain.trainNumber());
  }

  @Test
  void shouldReturnTrueIfCoachIsSupportedByTrain() {
    String trainNumber = "12345";

    Train mumbaiToPuneTrain = mumbaiToPuneTrain(trainNumber);

    CoachType coachType = CoachType.GENERAL;
    assertTrue(mumbaiToPuneTrain.isCoachSupported(coachType));
  }

  @Test
  void shouldReturnFalseIfCoachIsNotSupportedByTrain() {
    String trainNumber = "12345";

    Train mumbaiToPuneTrain = mumbaiToPuneTrain(trainNumber);

    CoachType coachType = CoachType.SLEEPER;
    assertFalse(mumbaiToPuneTrain.isCoachSupported(coachType));
  }

  @Test
  void shouldReturnTrueIfRouteIsSupportedByTrain(){
    String trainNumber = "12345";
    Station startStation = new Station("Karjat");
    Station endStation =  new Station("Chinchwad");

    Train mumbaiToPuneTrain = mumbaiToPuneTrain(trainNumber);
    assertTrue(mumbaiToPuneTrain.isRouteSupported(startStation, endStation));
  }

  @Test
  void shouldReturnFalseIfStartStationIsNotSupportedByTrain(){
    String trainNumber = "12345";
    Station startStation = new Station("Delhi");
    Station endStation =  new Station("Chinchwad");

    Train mumbaiToPuneTrain = mumbaiToPuneTrain(trainNumber);
    assertFalse(mumbaiToPuneTrain.isRouteSupported(startStation, endStation));
  }

  @Test
  void shouldReturnFalseIfEndStationIsNotSupportedByTrain(){
    String trainNumber = "12345";
    Station startStation = new Station("Karjat");
    Station endStation =  new Station("Delhi");

    Train mumbaiToPuneTrain = mumbaiToPuneTrain(trainNumber);
    assertFalse(mumbaiToPuneTrain.isRouteSupported(startStation, endStation));
  }

  @Test
  void shouldReturnFalseIfStartStationComesAfterEndStationOnTrainRoute(){
    String trainNumber = "12345";
    Station startStation = new Station("Chinchwad");
    Station endStation =  new Station("Karjat");

    Train mumbaiToPuneTrain = mumbaiToPuneTrain(trainNumber);
    assertFalse(mumbaiToPuneTrain.isRouteSupported(startStation, endStation));
  }

  private static Train mumbaiToPuneTrain(String trainNumber) {
    List<Station> route = List.of(
      new Station("Mumbai"),
      new Station("Karjat"),
      new Station("Lonavala"),
      new Station("Chinchwad"),
      new Station("Pune")
    );
    List<CoachType> supportedCoaches = List.of(CoachType.GENERAL);

    return new Train(
      trainNumber,
      route,
      supportedCoaches
    );
  }


}