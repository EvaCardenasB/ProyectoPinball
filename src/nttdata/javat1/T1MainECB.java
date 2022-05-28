package nttdata.javat1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class T1MainECB {
	public static void main(String[] args) throws SQLException {
		
		// REALIZAR CONEXION CON LA BASE DE DATOS
		Connection cn = nttdata.javat1.datebase.TestConnection.conecctionDB();
		Statement st_ = cn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs= null;	
					
	
		// Llamada al menu para proceder al registro
			nttdata.javat1.datebase.Register.RegisterUser(st_, cn, rs);;
		
	
	}	
	
}
