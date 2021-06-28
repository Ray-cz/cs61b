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
}
