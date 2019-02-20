import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	public static void main(String[] args){
		double totalTime = 2000000;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}
		
		String fname= "./data/planets.txt";
		Planet[] planets = readPlanets(fname);
		double radius = readRadius(fname);
	
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		//drawing the background
		
		for (int i = 0; i < planets.length; i++) {
			planets[i].draw();
		}
		//drawing all the planets
		
		for(double t = 0.0; t < totalTime; t += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			for (int i = 0; i < planets.length; i++) {
				planets[i].draw();
			}
			
			StdDraw.show(10);
		}
		
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
		}
	}
	
	public static double readRadius(String fname) {
		try {
	        Scanner scan = new Scanner(new File(fname));
	        scan.nextInt();
	        double radius = scan.nextDouble();
	        
	        scan.close();
	        return radius;   
	    } 
		catch (FileNotFoundException e) {
	        System.out.println("File is not found");
	        return 0;
		}
	}
	
	public static Planet[] readPlanets(String fname) {
		Scanner scan;
		try {
			scan = new Scanner (new File(fname));
			int num = scan.nextInt();
			scan.nextDouble();
			Planet[] ary = new Planet[num];
			
			for (int i = 0; i < num; i++){
				Planet p = new Planet(scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.next());
				ary[i] = p;
			}
			
			scan.close();
			return ary;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
