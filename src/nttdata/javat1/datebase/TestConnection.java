package nttdata.javat1.datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String login_ = "sa";
	private static final String password_ = "12345Ab##";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error al cargar el controlador");
		}
	}

	// METODO QUE CONTIENE LA CONFIGURACIÓN PARA REALIZAR LA CONEXION A LA BASE DE DATOS
	public static Connection conecctionDB() throws SQLException {

		String db_ = "PinballDB";
		String url_ = "jdbc:sqlserver://localhost:1433;databaseName = " + db_
				+ ";encrypt=false;trustServerCertificate=true";
		Connection connection_ = null;

		connection_ = DriverManager.getConnection(url_, login_, password_);
		System.out.println("Conexion a la base de datos " + db_ + " correcta");

		return connection_;
	}		
		
		
}
