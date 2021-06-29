public class NBody {
	public static double readRadius(String path) {
		In in = new In(path);

		int firstItemInFile = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String path) {
		In in = new In(path);
		int num = in.readInt();

		Planet[] planets = new Planet[num];

		double radius = in.readDouble();
		for (int i = 0; i < num; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}

		return planets;
	}

	// **Drawing the background */
	private static void drawBackground(double r) {
		StdDraw.setScale(-r, r);
		StdDraw.picture(0, 0, "images/starfield.jpg");
	}

	/** Drawing all of the planets */
	private static void drawAllPlanets(Planet[] planets, int total) {
		for (int i = 0; i < total; i++) {
			planets[i].draw();
		}
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);

		int total = planets.length;
		StdAudio.play("audio/2001.mid");

		drawBackground(radius);
		drawAllPlanets(planets, total);
		StdDraw.enableDoubleBuffering();
		int time = 0;
		for (time = 0; time < T; time += dt) {
			double xForces[] = new double[total];
			double yForces[] = new double[total];
			for (int i = 0; i < total; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < total; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			drawBackground(radius);
			drawAllPlanets(planets, total);
			StdDraw.show();
			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos,
					planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}

}
