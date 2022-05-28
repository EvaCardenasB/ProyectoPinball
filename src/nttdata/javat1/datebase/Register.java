package nttdata.javat1.datebase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Register {

	public static String RegisterUser(Statement st_, Connection cn, ResultSet rs) throws SQLException {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		String name = null;
		String password = null;
		boolean exit=false;
		do {
			System.out.println("Welcome to Eva's pinball");
			menu();
			int option = sc.nextInt();
		switch (option) {
		case 1:
			// Entrar en el juego como un usuario ya registrado
			name = enterDataBase(rs, st_, cn, name, password);

			break;

		case 2:
			// Entrar en el juego como un nuevo usuario
			name = addDataBase(rs, st_, cn, name, password);
			break;

		case 3:
			// Entrar en el juego sin registrarse, no se guardaran sus partidas por lo que
			// no entrará en ranking ni podrá ver las partidas jugadas
			nttdata.javat1.datebase.GameManagement.MenuGame(null, st_, cn, rs);
			break;

		default:
			System.err.println("Introduce un numero del 1 al 3");
			exit=true;
			break;
		}
		}while(exit==false);
		return name;

	}

	public static void menu() {
		System.out.println();
		System.out.println("1. Sing up");
		System.out.println("2. Register");
		System.out.println("3. Enter without registering");

	}

	public static String registerUserName() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome, what's do your PlayerName? ");
		String name = sc.next();
		return name;

	}

	public static String registerUserPasswd() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Password: ");
		String password = sc.next();
		return password;

	}

	public static String enterDataBase(ResultSet rs, Statement st_, Connection cn, String name, String password)
			throws SQLException {

		name = registerUserName();
		try {
			String consultPlayer = "select Name, Password from Players where Name='" + name + "'";
			rs = st_.executeQuery(consultPlayer);
			rs.next();

			if (rs.getRow() > 0) {

				password = registerUserPasswd();

				if (rs.getString("Password").equals(password)) {
					System.out.println("Hello, " + rs.getString("Name"));
					nttdata.javat1.datebase.GameManagement.MenuGame(name, st_, cn, rs);

				} else {
					System.out.println("Contraseña invalida, prueba de nuevo");
				}

			} else {
				System.out.println("No existe ningun usuario con ese nombre, prueba a registrarse");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Error al conectar");
		}
		return name;

	}

	public static String addDataBase(ResultSet rs, Statement st_, Connection cn, String name, String password)
			throws SQLException {
		name = registerUserName();
		try {
			String consultPlayer = "select Name from Players where Name='" + name + "'";
			rs = st_.executeQuery(consultPlayer);
			rs.next();

			if (rs.getRow() > 0) {

				System.out.println("Ya existe un usuario con este nombre, prueba con otro");

			} else {
				password = registerUserPasswd();
				System.out.println("Bienvenid@, " + rs.getString("Name"));
				String sentenciasSQL = "insert into Players (Name,Password) values ('" + name + "','" + password + "')";
				st_.executeUpdate(sentenciasSQL);
				nttdata.javat1.datebase.GameManagement.MenuGame(name, st_, cn, rs);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Error al registrar");
		}

		return name;

	}
	public static void addGame(ResultSet rs, Statement st_, Connection cn, String name) {
		int puntos=nttdata.javat1.game.Game.launchAndStart(st_, rs, cn, name);
		try {
			String consultPlayer = "select idPlayer from Players where Name='" + name + "'";
			rs = st_.executeQuery(consultPlayer);
			rs.next();
			if (rs.getRow() > 0) {
		
				String sentenciasSQL = "insert into Game (Points,Date,idPlayer) values (" + puntos + ",GETDATE()," + rs.getInt("idPlayer") + ")";
				st_.executeUpdate(sentenciasSQL);	
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Error al registrar la partida");
		}
		
	}

}
