
public class Planet {
	double myXPos;            // current x position
	double myYPos;            // current y position
	double myXVel;            // current velocity in x direction 
	double myYVel;            // current velocity in y direction
	double myMass;            // mass of planet
	String myFileName;        // file name (in images folder) 
	
	public Planet(double xp, double yp, double xv,
        double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass; 
		myFileName = filename;
	}
	
	public Planet(Planet p) {
		this.myXPos = p.myXPos;
		this.myYPos = p.myYPos;
		this.myXVel = p.myXVel;
		this.myYVel = p.myYVel;
		this.myMass = p.myMass;
		this.myFileName = p.myFileName;
	}
	
	public double calcDistance (Planet p) {
		return Math.sqrt((this.myXPos-p.myXPos)*(this.myXPos-p.myXPos) + (this.myYPos-p.myYPos)*(this.myYPos-p.myYPos));
	}
	
	public double calcForceExertedBy(Planet p) {
		return (6.67 * Math.pow(10, -11) * this.myMass * p.myMass / Math.pow(calcDistance(p), 2));
	}
	
	public double calcForceExertedByX(Planet p) {
		return calcForceExertedBy(p) * (p.myXPos - this.myXPos) / calcDistance(p);
	}
	
	public double calcForceExertedByY(Planet p) {
		return calcForceExertedBy(p) * (p.myYPos - this.myYPos) / calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] ary) {
		double sum = 0;
		for (int i = 0; i < ary.length; i++) {
			if (! ary[i].equals(this)) {
			    sum += calcForceExertedByX(ary[i]);
			}
		}
		return sum;
	}
	
	public double calcNetForceExertedByY(Planet[] ary) {
		double sum = 0;
		for (int i = 0; i < ary.length; i++) {
			if (! ary[i].equals(this)) {
			    sum += calcForceExertedByY(ary[i]);
			}
		}
		return sum;
	}
	
	public void update(double seconds, double xforce, double yforce) {
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		
		myXVel = myXVel + seconds * ax;
		myYVel = myYVel + seconds * ay;
		
		myXPos = myXPos + seconds * myXVel;
		myYPos = myYPos + seconds * myYVel;
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
