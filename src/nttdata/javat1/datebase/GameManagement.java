package nttdata.javat1.datebase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class GameManagement {

	public static void MenuGame(String name, Statement st_, Connection cn, ResultSet rs) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean exit = false;

		do {
			menu();
			int option = sc.nextInt();
			switch (option) {
			case 1:
				boolean start = nttdata.javat1.game.Ball.lauchBall();
				if (start = true) {
					nttdata.javat1.datebase.Register.addGame(rs, st_, cn, name);
				}
				break;

			case 2:
				// Consultar mis partidas
				gamesPlayed(name, rs, st_);

				break;

			case 3:
				// Consultar records
				rankingPlayed(rs, st_);
				break;

			case 4:
				// Salir del juego --> vuelve apedir el registro del usuario
				exit = true;
				break;
			default:
				System.err.println("Introduce un numero del 1 al 3");
				break;
			}
		} while (exit == false);
	}

	public static void menu() {
		System.out.println("1. Start playing");
		System.out.println("2. Check games played");
		System.out.println("3. Consult records");
		System.out.println("4. Exit to the game");
		System.out.println();
	}

	public static void gamesPlayed(String name, ResultSet rs, Statement st_) {

		try {
			String consultaSQL = ("select g.idGame,g.Points,g.Date from Game g "
					+ "inner join Players p on g.idPlayer=p.idPlayer " + "where p.Name='" + name + "'");
			rs = st_.executeQuery(consultaSQL);
			System.out.println("Partida     Fecha        Points");
			while (rs.next()) {

				if (rs.getRow() > 0) {

					System.out.println(
							rs.getInt("idGame") + "          " + rs.getDate("Date") + "     " + rs.getInt("Points"));
				} else {

					System.out.println("No tiene ninguna partida");

				}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Error al buscar partidas");
		}

	}

	public static void rankingPlayed(ResultSet rs, Statement st_) {

		try {
			String consultaSQL = ("select p.Name,max(g.Points) as Points,max(g.Date) as Date from Game g "
					+ "inner join Players p on g.idPlayer=p.idPlayer " + "group by p.Name "
					+ "order by max(g.Points) DESC");

			rs = st_.executeQuery(consultaSQL);
			while (rs.next()) {
				if (rs.getRow() > 0) {
					System.out.println(
							rs.getString("Name") + "     " + rs.getInt("Points") + "      " + rs.getDate("Date"));
				} else {
					System.out.println("No hay ninguna partida");
				}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Error al buscar ranking");
		}

	}

}
