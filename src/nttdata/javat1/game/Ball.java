package nttdata.javat1.game;

import java.util.Scanner;

public class Ball {
	public static int calcularRandom(int menor, long intervalo) {
		int position;
		position = (int) (Math.floor(Math.random() * (intervalo - menor + 1)) + menor);
		return position;
	}
	
	public static boolean lauchBall() {
		boolean start=false;
		Scanner sc = new Scanner(System.in);
		String launch;
		System.out.println("Pres L/l to start");
		
		do {
			launch = sc.next();
			if (!launch.equalsIgnoreCase("l")) {
				System.out.println("Press L/l to start, please");
			}
			
		}while(!launch.equalsIgnoreCase("l"));
		return start;
	}
	
	
	
	
	
}
