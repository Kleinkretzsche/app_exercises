package app.exercise.elements;
import java.awt.Color;
import sag.*;
import sag.elements.*;
import sag.elements.shapes.*;
import java.time.*;

public class GAnalClock implements Drawable 
{
  private final GGroup group = new GGroup();

  private LocalTime time = null;
  private GLine lh, lm, ls;
  private GText text;

  public GAnalClock() 
  { 
    time = LocalTime.now();
    addBg();
    addClock();
    addText();
  }

  private static float degToRad(float deg) 
  {
    return deg * ((float) Math.PI / 180.0f);
  }

  private static float[] getRotCoord(float radius, float degree) 
  {
    float[] xy = new float[2];
    xy[0] = radius * (float) Math.cos(degToRad(degree));
    xy[1] = radius * (float) Math.sin(degToRad(degree));
    return xy;
  }

  private static GLine mkIndicator(float radius, float degree) 
  {
    float[] xy1 = getRotCoord(radius, degree);
    float[] xy2 = getRotCoord(radius * 0.9f, degree);
    GLine l = new GLine(xy1[0], xy1[1], xy2[0], xy2[1]);
    l.setStroke(Color.BLACK, 10);
    return l;
  }

  private void addBg() 
  {
    GCircle foundation = new GCircle(400);    
    foundation.setFill(Color.GRAY);
    group.addChild(foundation);
    for (int i = 0; i < 12; i++) 
    {
      group.addChild(mkIndicator(400, i * 30));
    }
  }

  private void addText() 
  {
    if (time.getHour() < 12)
      text = new GText("a.m.");
    else
      text = new GText("p.m.");
    
    text.setAlignment(GText.TextAnchor.MIDDLE);
    text.move(0, 150);
    group.addChild(text);
  }
  
  private void addClock() 
  {
    float xyHour[] = getRotCoord(170, time.getHour() * 30 - 90 + time.getMinute() * 0.4f); 
    float xyMin[]  = getRotCoord(250, time.getMinute() * 6 - 90); 
    float xySec[]  = getRotCoord(300, time.getSecond() * 6 - 90);  

    lh = new GLine(xyHour[0], xyHour[1], 0, 0);
    lh.setStroke(Color.RED, 20);
    group.addChild(lh);

    lm = new GLine(xyMin[0], xyMin[1], 0, 0);
    lm.setStroke(Color.BLACK, 16);
    group.addChild(lm);

    ls = new GLine(xySec[0], xySec[1], 0, 0);
    ls.setStroke(Color.BLACK, 10);
    group.addChild(ls);
  }

  public void update() 
  {
    time = LocalTime.now();

    if (time.getHour() < 12)
      text.setText("a.m.");
    else 
      text.setText("p.m.");
    
    float xyHour[] = getRotCoord(170, time.getHour() * 30 - 90 + time.getMinute() * 0.4f); 
    float xyMin[]  = getRotCoord(250, time.getMinute() * 6 - 90); 
    float xySec[]  = getRotCoord(300, time.getSecond() * 6 - 90);  
    lh.setPosition1(xyHour[0], xyHour[1]);
    lm.setPosition1(xyMin[0], xyMin[1]);
    ls.setPosition1(xySec[0], xySec[1]);
  }

  @Override
  public GElement getGElement() { return group; }
}
