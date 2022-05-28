package nttdata.javat1.game;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Game {
	public static int launchAndStart(Statement st_,ResultSet rs,Connection cn,String name) {
		//int nivelDeImpulsion= calcularRandom(0,table.length);
		
		int[] table = new int[20];//Array que va a contener las puntuaciones de la partida, dichas puntuaciones calculadas de forma aleatoria y alteradas en cada nueva ejecución del programa.
		
		//Rellenar la tabla con las puntuaciones
		for(int i=0; i < table.length; i++) {
			table[i]=Ball.calcularRandom(1, 100);
			
		}
	
		
		int position=0; //Variable paraguardar las posiciones en las que cae la bola
		int points=0;
		int lifeTimes=0;
		
		do {
			lifeTimes=lifeTimes+1;
			position=Ball.calcularRandom(0,table.length-1);
						
			if(position>=5 && position<=9) { //desde la posicion 5 y 9 son puntuaciones normales 
				points=points+table[position];					
			}
			else if(position>=10 && position<=15){ //desde la posicion 10 hasta la 15 se le restan puntos
				
				points=points+(table[position]-position);
				
				
			}
			
			while(position>15 && position <= table.length-1){// desde la posicion 15 hasta la ultima de la tabla son los rebotes
				
				points=points+table[position];
				position=Ball.calcularRandom(0,table.length-1);
			}
			
		}while(position >=4 && lifeTimes<10); //desde la posicion 0 a la 4 la bola da 0 puntos y se termina la partida
		
		System.out.println("La puntuación final es de "+points);
		
		
		return points;
	}
}
