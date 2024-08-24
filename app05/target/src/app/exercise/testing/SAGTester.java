package app.exercise.testing;

import java.awt.Color; 
import sag.*; 
import sag.elements.*; 
import sag.elements.shapes.*;
import app.exercise.elements.GAnalClock;
import java.util.concurrent.TimeUnit;


public class SAGTester 
{
  public static void main(String[] args) 
  {
    SAGFrame frame = new SAGFrame("The time",30,1000,1000);
    SAGPanel panel = new SAGPanel(1000,1000);
    frame.setSAGPanel(panel);
    frame.setVisible(true);

    GGroup main = panel.addLayer(LayerPosition.CENTER);

    GAnalClock myanal = new GAnalClock();
    main.addChild(myanal);

    while (true) 
    {
      myanal.update();
      try 
      {
	TimeUnit.SECONDS.sleep(1);
      }
      catch (InterruptedException e) 
      {
        Thread.currentThread().interrupt();
      }
    }
  }
}
