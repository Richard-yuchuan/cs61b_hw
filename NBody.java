public class NBody{
public static double readRadius(String filename){
  In file = new In(filename);
  int num = file.readInt();
  double radius = file.readDouble();
  return radius;
}
public static Planet[] readPlanets(String filename){
  In file = new In(filename);
  int numofplanet = file.readInt();
  double radius = file.readDouble();
  //double paramofplanets[] = file.readDoubles();
  //String planetName[] = file.readStrings();
  Planet[] planets = new Planet[numofplanet];
  for (int i = 0; i< numofplanet; i++)
  {
    double xp = file.readDouble();
    double yp = file.readDouble();
    double xv = file.readDouble();
    double yv = file.readDouble();
    double mass = file.readDouble();
    String planetName = file.readString();
    planets[i] = new Planet(xp,yp,xv,yv,mass,planetName);
  }
  return planets;
}
public static void main(String[] args) {
  double T = Double.parseDouble(args[0]);
  double dt = Double.parseDouble(args[1]);
  String filename = args[2];
  double r = readRadius(filename);
  Planet[] planets = readPlanets(filename);
  /*set scale of the universe*/
  StdDraw.setScale(-r,r);
  StdDraw.picture(0,0,"images/starfield.jpg");
  for (int i = 0; i<planets.length; i++)
  {
    planets[i].draw();
  }
  StdDraw.enableDoubleBuffering();
  double count = 0;
  while(count < T)
  {
  double[] xForces = new double[planets.length];
  double[] yForces = new double[planets.length];
  for(int i = 0; i<planets.length; i++)
  {
    xForces[i]= planets[i].calcNetForceExertedByX(planets);
    yForces[i]= planets[i].calcNetForceExertedByY(planets);
  }
  for(int i = 0; i<planets.length; i++){
    planets[i].update(dt,xForces[i],yForces[i]);
  }
  StdDraw.clear();
  StdDraw.picture(0,0,"images/starfield.jpg");
  for (int i = 0; i<planets.length; i++)
  {
    planets[i].draw();
  }
  StdDraw.show();
  StdDraw.pause(10);
  count += dt;
  }
}
}
