public class NBody {
	public static double readRadius(String path){
		In in = new In(path);

		int firstItemInFile = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets (String path){
		In in = new In(path);
		int num = in.readInt();
		
		Planet[] planets = new Planet[num];

		double radius = in.readDouble();
		for(int i = 0; i < num; i++){
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

	
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);

		//**Drawing the background */
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for(int i = 0; i < planets.length; i++){
			planets[i].draw();
		}
	}
}
