package Azure.img.upload.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import Azure.img.upload.connection.sqldbConnection;

@Service
public class sqlService {
	
	public static void insert(String Link) {
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(sqldbConnection.DB_Connection_String);
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO img_links (link) VALUES (?);");
			preparedStatement.setString(1, Link);
			preparedStatement.executeUpdate();
			con.close();
		}
		catch(Exception e) {
				
			System.out.println("Error message:- "+ e);
		}
		
	}
	
	public static ArrayList<String> getlink(String key){
		ArrayList<String> list=new ArrayList<>();
		try
		{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(sqldbConnection.DB_Connection_String);
			Statement st = con.createStatement();
			String sql = "SELECT * FROM img_links WHERE link LIKE '%"+key+"%';";
		    ResultSet rs = st.executeQuery(sql);
		    while(rs.next()){
		    	list.add(rs.getString("link"));
		    }
			
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println("error : "+e);
		}
		return list;
	}
}
