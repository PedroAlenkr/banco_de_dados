package FBD_app.pacotes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class conexao {
	private String url;
	private String usuario;
	private String senha;
	private Connection con;
	
	conexao(){
		url= "jdbc:postgresql://localhost:5432/postgres";
		usuario= "postgres";
		senha= "SenP0st19";
		
		try {
			Class.forName("org.postgresql.Driver");
			con= DriverManager.getConnection(url,usuario,senha);
			System.out.println("conexao estabelecida");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int executaSQL(String sql) {
		try {
			Statement stm= con.createStatement();
			int res= stm.executeUpdate(sql);
			con.close();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ResultSet executasql(String sql) {
		try {
			Statement stm= con.createStatement();
			ResultSet res= stm.executeQuery(sql);
			con.close();			
			return res;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
