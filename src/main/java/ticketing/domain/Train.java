package ticketing.domain;

import ticketing.domain.records.Station;

import java.util.List;

public class Train {
  private final String trainNumber;
  private final List<Station> route;
  private final List<CoachType> supportedCoaches;

  public Train(String trainNumber, List<Station> route, List<CoachType> supportedCoaches) {
    this.trainNumber = trainNumber;
    this.route = route;
    this.supportedCoaches = supportedCoaches;
  }

  public String trainNumber() {
    return trainNumber;
  }

  public boolean isCoachSupported(CoachType coachType) {
    return supportedCoaches.contains(coachType);
  }

  public boolean isRouteSupported(Station startStation, Station endStation) {
    return route.contains(startStation) && route.contains(endStation) && isStartBeforeEnd(startStation, endStation);
  }

  private boolean isStartBeforeEnd(Station startStation, Station endStation) {
    boolean isFirstAhead = false;
    for( Station station: route ) {
      if (!isFirstAhead && station.equals( endStation )) {
        return false;
      } else if (station.equals( startStation )) {
        isFirstAhead = true;
      }
    }
    return isFirstAhead;
  }
}
