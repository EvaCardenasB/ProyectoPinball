package nttdata.javat1.datebase;

import java.sql.SQLException;
import java.sql.Statement;

public class DDL {
	
	public static void createTables(Statement st_) throws SQLException {

		System.out.println("Creando tabla Players...");
		st_.executeUpdate("CREATE TABLE Players(idPlayer INT NOT NULL IDENTITY(1,1), " 
				+ "Name VARCHAR(50)," 
				+ "Password VARCHAR(50),"
				+ "CONSTRAINT PK_idPlayer PRIMARY KEY (idPlayer),"
				+ ")");

		System.out.println("Creando tabla Game...");
		st_.executeUpdate("CREATE TABLE Game(idGame INT NOT NULL IDENTITY(1,1), " 
				+ "Points int not null,"
				+ "Date DATE not null," 
				+ "idPlayer INT NOT NULL,"
				+ "CONSTRAINT PK_idGame PRIMARY KEY (idGame)," 
				+ "CONSTRAINT  FK_player FOREIGN KEY (idPlayer) REFERENCES Players(idPlayer),"
				+ ")");

	}
	public static void deleteTables(Statement st_) throws SQLException {

		
		st_.executeUpdate("DROP TABLE IF EXISTS Game");
		st_.executeUpdate("DROP TABLE IF EXISTS Players");
		

	}
	
}	
