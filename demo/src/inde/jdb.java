package inde;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class jdb {
 public static void main(String args[]) {
	 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 System.out.println("Class loaded");
		 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Anshul","root","6376");
				 System.out.println("Connection Done");
		PreparedStatement ps = conn.prepareStatement("insert into student (name,fee) value(?,?)");
		ps.setString(1,"jai");
		ps.setInt(2,88855);
		int i = ps.executeUpdate();
		System.out.println(i);
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
 }
}
