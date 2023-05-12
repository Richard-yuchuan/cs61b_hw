public class Planet
{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  private static final double G = 6.67e-11;

  public Planet (double xP, double yP, double xV, double yV, double m, String img)
  {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  public Planet(Planet p)
  {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }
  public double calcDistance(Planet p)
  {
    return Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));
  }
  public double calcForceExertedBy(Planet p)
  {
    double r = calcDistance(p);
    return G*this.mass*p.mass/(r*r);
  }
  public double calcForceExertedByX(Planet p)
  {
    double r = calcDistance(p);
    double force = calcForceExertedBy(p);
    double dx = p.xxPos-this.xxPos;
    return force*dx/r;
  }
  public double calcForceExertedByY(Planet p)
  {
    double r = calcDistance(p);
    double force = calcForceExertedBy(p);
    double dy = p.yyPos-this.yyPos;
    return force*dy/r;
  }
  public double calcNetForceExertedByX(Planet[] allPlanets)
  {
    double netx = 0;
    for (int i = 0; i < allPlanets.length; i++)
    {
      if (this.equals(allPlanets[i]) == false)
      {
      netx += calcForceExertedByX(allPlanets[i]);
      }
    }
    return netx;
  }
  public double calcNetForceExertedByY(Planet[] allPlanets)
  {
    double nety = 0;
    for (int i = 0; i < allPlanets.length; i++)
    {
      if (this.equals(allPlanets[i]) == false)
      {
      nety += calcForceExertedByY(allPlanets[i]);
      }
    }
    return nety;
  }
  public void update(double dt, double Fx, double Fy)
  {
    double ax = Fx/this.mass;
    double ay = Fy/this.mass;
    xxVel += dt*ax;
    yyVel += dt*ay;
    xxPos += dt*xxVel;
    yyPos += dt*yyVel;
  }
  public void draw(){
    StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
    /*for (int i = 0; i<p.length; i++){
    StdDraw.picture(p[i].xxPos,p[i].yyPos,"images/"+p[i].imgFileName);
  }*/
  }
}
