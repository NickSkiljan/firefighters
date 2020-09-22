package main.scenarios;

import main.api.*;
import main.api.exceptions.FireproofBuildingException;
import main.impls.CityImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ExtraScenarios {
  @Test
  public void multipleFiresOneFirefighter() throws FireproofBuildingException {
    City basicCity = new CityImpl(5, 5, new CityNode(0, 0));
    FireDispatch fireDispatch = basicCity.getFireDispatch();

    CityNode[] fireNodes = {
            new CityNode(4, 4),
            new CityNode(3, 3),
            new CityNode(2, 2),
            new CityNode(1, 1),
            new CityNode(1, 2),
            new CityNode(3, 4)
    };
    Pyromaniac.setFires(basicCity, fireNodes);
    fireDispatch.setFirefighters(1);
    fireDispatch.dispatchFirefighers(fireNodes);
    for (int x = 0; x < fireNodes.length; x++) {
      Assert.assertFalse(basicCity.getBuilding(fireNodes[x]).isBurning());
    }
    Firefighter firefighter = fireDispatch.getFirefighters().get(0);
    Assert.assertEquals(8, firefighter.distanceTraveled());

  }

  @Test
  public void multipleFiresMultipleFirefighters() throws FireproofBuildingException {
    City basicCity = new CityImpl(5, 5, new CityNode(0, 0));
    FireDispatch fireDispatch = basicCity.getFireDispatch();

    CityNode[] fireNodes = {
            new CityNode(4, 4),
            new CityNode(0, 4),
            new CityNode(2, 2),
            new CityNode(4, 0)
    };
    Pyromaniac.setFires(basicCity, fireNodes);
    fireDispatch.setFirefighters(3);
    fireDispatch.dispatchFirefighers(fireNodes);
    for (int x = 0; x < fireNodes.length; x++) {
      Assert.assertFalse(basicCity.getBuilding(fireNodes[x]).isBurning());
    }
    List<Firefighter> firefighters = fireDispatch.getFirefighters();
    int totalDistanceTraveled = 0;
    for (Firefighter firefighter : firefighters) {
      totalDistanceTraveled += firefighter.distanceTraveled();
    }
    Assert.assertEquals(16, totalDistanceTraveled);
  }
}
