import java.math.*;

public class TestPlanet{
	private static void checkEquals(double actual, double expected, String label, double eps){
		if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)){
			System.out.println("PASS: "+ label + ": Expected " + expected + " and you gave " + actual);
		}
		else{
			System.out.println("FAIL: "+ label + ": Expected " + expected + " and you gave " + actual);			
		}
	}

	private static void printPairwiseForce(){
		System.out.println("The pairwise force between two planet is:");

		Planet p1 = new Planet(0.0, 0.0, 0.0, 0.0, 5.0, "jupiter.gif");
		Planet p2 = new Planet(3.0, 4.0, 0.0, 0.0, 5.0, "jupiter.gif");

		checkEquals(p1.calcForceExertedBy(p2), 6.67e-11, "calcPairwiseForce", 0.01);
	}

	public static void main(String[] args) {
		printPairwiseForce();
	}
}
