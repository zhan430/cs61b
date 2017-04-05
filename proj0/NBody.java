import java.util.Arrays;
public class NBody {
	public static double readRadius(String filename) {
		In f = new In(filename);
		f.readInt();
		double radius = f.readDouble();
		f.close();
		return radius;
	}
	public static Planet[] readPlanets(String filename) {
		In f = new In(filename);
		int n = f.readInt();
		f.readDouble();
		f.readLine();
		Planet[] planets = new Planet[n];
		for(int i = 0; i < n; i++) {
			String[] splited = f.readLine().trim().split(" +");
			planets[i] = new Planet(Double.parseDouble(splited[0]), Double.parseDouble(splited[1]), 
									Double.parseDouble(splited[2]), Double.parseDouble(splited[3]), 
									Double.parseDouble(splited[4]), splited[5]);
		}
		return planets;
	}
	private static void print(Planet[] planets, double radius) {
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}
	}
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);
		StdAudio.play("./audio/2001.mid");
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0.0, 0.0, "./images/starfield.jpg");
		for(Planet p : planets) {
			p.draw();
		}
		for(double t = 0.0; t < T; t += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0.0, 0.0, "./images/starfield.jpg");
			for(Planet p : planets) {
				p.draw();
			}
			StdDraw.show(10);
		}
		print(planets, radius);
	}
}
