
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqliteDB {
	private Connection c;
	private Statement stmt;
	private ResultSet rs;
	
	public sqliteDB(){
		// povezivanje na bazu
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/rent?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			stmt = c.createStatement();
		}catch(Exception e){
			System.out.println(e);
		}
	}
  //login pomoću hash lozinke
	public boolean login(String use,String pasw){
		int broj = 0;
		try{
			int keyUser = pasw.hashCode();
			String upit = "SELECT * FROM zaposlenik WHERE mail = ? AND pass = ?";
			PreparedStatement ps = c.prepareStatement(upit);
			ps.setString(1, use);
			ps.setInt(2, keyUser);
			rs = ps.executeQuery();
			while(rs.next()){
				String mail = rs.getString("mail");
				String pass = rs.getString("pass");	
				broj = rs.getRow();
				
			}	
		}catch(Exception e){
			System.out.println(e);
		}
		if(broj == 1){
			return true;
		}
		return false;
	}
}

