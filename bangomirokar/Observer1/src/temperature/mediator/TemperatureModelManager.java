package temperature.mediator;

import temperature.model.Temperature;
import temperature.model.TemperatureList;

public class TemperatureModelManager implements TemperatureModel
{
  private TemperatureList temperatureList;

  public TemperatureModelManager()
  {
    temperatureList = new TemperatureList();
  }

  @Override public synchronized void addTemperature(String id, double value)
  {
    Temperature temperature = new Temperature(id, value);
    Temperature old = getLastInsertedTemperature(id);
    this.temperatureList.addTemperature(temperature);
    if (old != null && old.getValue() != temperature.getValue())
    {
      System.out.println("--> new=" + temperature + " (old=" + old + ")");
    }
    else if (old == null)
    {
      System.out.println("--> new=" + temperature + " (old=" + old + ")");
    }
  }

  @Override public synchronized Temperature getLastInsertedTemperature()
  {
    return temperatureList.getLastTemperature(null);
  }

  @Override public synchronized Temperature getLastInsertedTemperature(String id)
  {
    return temperatureList.getLastTemperature(id);
  }

  // and maybe other methods...
}
