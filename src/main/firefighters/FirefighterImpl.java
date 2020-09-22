package main.firefighters;

import main.api.CityNode;
import main.api.Firefighter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FirefighterImpl implements Firefighter {

  private int distance;
  private CityNode location;

  public FirefighterImpl(CityNode fireStationLocation) {
    distance = 0;
    location = fireStationLocation;
  }
  @Override
  public CityNode getLocation() {
    return location;
  }

  @Override
  public int distanceTraveled() {
    return distance;
  }

  @Override
  public void setLocation(CityNode newLocation) {
    distance += Math.abs(location.getX() - newLocation.getX()) + Math.abs(location.getY() - newLocation.getY());
    location = newLocation;
  }
}
