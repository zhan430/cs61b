public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName = new String();
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel; 
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}
	public double calcForceExertedBy(Planet p) {
		double g = 6.67e-11;
		return g * p.mass * this.mass / (this.calcDistance(p) * this.calcDistance(p));
	}
	public double calcForceExertedByX(Planet p) {
		return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
	}
	public double calcForceExertedByY(Planet p) {
		return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] allplanets) {
		double sum = 0.0;
		for (Planet p : allplanets) {
			if (this.equals(p) == true) {
				continue;
			}
			sum += this.calcForceExertedByX(p);
		}
		return sum;
	}
	public double calcNetForceExertedByY(Planet[] allplanets) {
		double sum = 0.0;
		for (Planet p : allplanets) {
			if (this.equals(p) == true) {
				continue;
			}
			sum += this.calcForceExertedByY(p);
		}
		return sum;
	}
	public void update(double dt, double fX, double fY) {
		this.xxVel += (dt * fX / this.mass);
		this.yyVel += (dt * fY / this.mass);
		this.xxPos += (dt * this.xxVel);
		this.yyPos += (dt * this.yyVel);
	}
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
	}
}
