
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
		//pokušaj povezivanja na bazu
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/rent?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			stmt = c.createStatement();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void dohvat(String upit){
		try{
			//String upit = "select * from automobil";
			rs = stmt.executeQuery(upit);
			while(rs.next()){
				String tip = rs.getString("tip");
				System.out.println(tip);
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public boolean login(String use,String pasw){
		int broj = 0;
		try{
			int keyUser = pasw.hashCode();
			String upit = "SELECT * FROM zaposlenik WHERE mail = ? AND pass = ?";
			PreparedStatement ps = c.prepareStatement(upit);
			ps.setString(1, use);
			ps.setInt(2, keyUser);
			//ps.setString(2, pasw);
			//String upit = "select * from zaposlenik WHERE FirstName='"+use;
			//rs = stmt.executeQuery(upit);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String mail = rs.getString("mail");
				//System.out.println(mail.equals(use));
				String pass = rs.getString("pass");
				//System.out.println(pass.hashCode());
				System.out.println(mail +" " + pass);
				//String p = "12345";
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

