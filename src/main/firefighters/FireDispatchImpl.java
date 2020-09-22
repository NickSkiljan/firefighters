package main.firefighters;

import java.util.*;

import javafx.util.Pair;
import main.api.*;

public class FireDispatchImpl implements FireDispatch {

  List<Firefighter> firefighters;
  City city;
  CityNode fireStationLocation;

  public FireDispatchImpl(City city) {
    this.firefighters = new ArrayList<>();
    this.city = city;
    this.fireStationLocation = this.city.getFireStation().getLocation();
  }

  @Override
  public void setFirefighters(int numFirefighters) {
    for (int x = 0; x < numFirefighters; x++) {
      firefighters.add(new FirefighterImpl(fireStationLocation));
    }
  }

  @Override
  public List<Firefighter> getFirefighters() {
    return firefighters;
  }

  @Override
  public void dispatchFirefighers(CityNode... burningBuildings) {
    List<CityNode> burningBuildingsList = new ArrayList<>(Arrays.asList(burningBuildings));

    // Loop until all fires are put out
    while (burningBuildingsList.size() > 0) {
      // Find minimum distance from all firefighters to all buildings
      int minDistance = -1;
      Firefighter minFirefighter = null;
      CityNode minBurningBuilding = null;
      for (CityNode burningBuilding : burningBuildingsList) {
        for (Firefighter firefighter : firefighters) {
          int distance = Math.abs(burningBuilding.getX() - firefighter.getLocation().getX()) + Math.abs(burningBuilding.getY() - firefighter.getLocation().getY());
          if (minFirefighter == null || distance < minDistance) {
            minDistance = distance;
            minFirefighter = firefighter;
            minBurningBuilding = burningBuilding;
          }
        }
      }
      // Send the found firefighter to the building and put out fire
      burningBuildingsList.remove(minBurningBuilding);
      minFirefighter.setLocation(minBurningBuilding);
      try {
        city.getBuilding(minBurningBuilding).extinguishFire();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
